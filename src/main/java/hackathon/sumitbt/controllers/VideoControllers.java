package hackathon.sumitbt.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import hackathon.sumitbt.models.Key;
import hackathon.sumitbt.models.SharedVideos;
import hackathon.sumitbt.models.User;
import hackathon.sumitbt.models.Video;
import hackathon.sumitbt.other.Response;
import hackathon.sumitbt.repositories.KeyRepo;
import hackathon.sumitbt.repositories.SharedVideosRepo;
import hackathon.sumitbt.repositories.UserRepo;
import hackathon.sumitbt.repositories.VideoRepo;

@RestController
public class VideoControllers
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private VideoRepo videoRepo;
	
	@Autowired
	private KeyRepo keyRepo;
	
	@Autowired
	private SharedVideosRepo sharedVideosRepo;
	
	@ResponseBody
	@RequestMapping(value="/view/{video_id}",method=RequestMethod.GET)
	public Response addview(@PathVariable String video_id)
	{
		Video v = videoRepo.findOne((long)Integer.parseInt(video_id));
		long i = v.getViews()+1;
		v.setViews(i);
		videoRepo.save(v);
		Response response = new Response();
		response.setMessage(v.getTitle());
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/video",method=RequestMethod.POST)
	public Iterable<Video> search(@RequestBody Key key)
	{
		Iterator<Video> all = null;
		
		if(key.getType().equals("g"))
			all = videoRepo.findAll().iterator();
		else
		{
			Iterator<Video> i = videoRepo.findByType(key.getType()).iterator();
			ArrayList<Video> result = new ArrayList<Video>();
			while(i.hasNext())
			{
				Video temp = i.next();
				if(temp.getFieldId()==key.getFieldId())
					result.add(temp);
			}
			all = result.iterator();
		}
		
		ArrayList<Video> result = new ArrayList<Video>();
		String searchKey = key.getSearchKey();
		Key q = new Key();
		q.setSearchKey(searchKey.replaceAll(" ","+"));
		q.setType(key.getType());
		q.setFieldId(key.getFieldId());
		q.setScrapped("False");
		keyRepo.save(q);
		String[] splited = key.getSearchKey().split("\\s+");
		while(all.hasNext())
		{
			Video v = all.next();
			String title = v.getTitle();
			String description = v.getDescription();
			String source = title.toLowerCase()+description.toLowerCase();
			String[] base = source.split("\\s+");
			boolean found=false;
			for(int i=0;i<splited.length&&!found;i++)
			{
				for(int j=0;j<base.length;j++)
				{
					if(base[j].equals(splited[i]))
					{
						result.add(v);
						found=true;
						break;
					}
				}
			}
		}
		Iterator<Video> videos = result.iterator();
		int l=0;
		while(videos.hasNext())
		{
			videos.next();
			l++;
		}
		long[][] a = new long[l][2];
		int s=0;
		videos = result.iterator();
		while(videos.hasNext())
		{
			Video v = videos.next();
			a[s][0] = v.getViews();
			a[s][1] = v.getVideoId();
			s++;
		}
		for(int i=0;i<l;i++)
	    {
			for(int j=0;j<l-1-i;j++)
			{
				if(a[j][0]<a[j+1][0])
				{
					long k=a[j][0];
					long p=a[j][1];
					a[j][0]=a[j+1][0];
					a[j][1]=a[j+1][1];
					a[j+1][0]=k;
					a[j+1][1]=p;
				}
			}
	    }
		ArrayList<Video> result2 = new ArrayList<Video>();
		for(int i=0;i<l;i++)
		{
			result2.add(videoRepo.findOne(a[i][1]));
		}
		return result2;
	}
	
	@ResponseBody
	@RequestMapping(value="/video/{video_id}",method=RequestMethod.GET)
	public Video details(@PathVariable String video_id)
	{
		Video v = videoRepo.findOne((long)Integer.parseInt(video_id));
		return v;
	}
	
	@ResponseBody
	@RequestMapping(value="/share/{video_id}",method=RequestMethod.POST)
	public Response share(@RequestBody User user, @PathVariable String video_id)
	{
		Response response = new Response();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User sharedBy = userRepo.findByEmail(auth.getName());
		User sharedTo = userRepo.findByEmail(user.getEmail());
		if(sharedTo==null)
		{
			response.setSuccess(false);
			return response;
		}
		else
		{
			Iterator<SharedVideos> i = sharedVideosRepo.findAll().iterator();
			while(i.hasNext())
			{
				SharedVideos temp = i.next();
				if(temp.getSharedBy()==sharedBy.getUserId() && temp.getSharedTo()==sharedTo.getUserId() && temp.getVideoId()==Integer.parseInt(video_id))
				{
					response.setSuccess(false);
					return response;
				}
			}
			SharedVideos sv = new SharedVideos();
			sv.setSharedBy(sharedBy.getUserId());
			sv.setSharedTo(sharedTo.getUserId());
			sv.setVideoId(Integer.parseInt(video_id));
			sharedVideosRepo.save(sv);
			return response;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/share",method=RequestMethod.GET)
	public Iterable<Video> sharedWithMe()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Iterator<SharedVideos> i = sharedVideosRepo.findBySharedTo(user.getUserId()).iterator();
		ArrayList<Video> result = new ArrayList<Video>();
		while(i.hasNext())
		{
			SharedVideos temp = i.next();
			if(temp.getSharedTo()==user.getUserId())
			{
				Video v = videoRepo.findOne(temp.getVideoId());
				v.setPreview(userRepo.findOne(temp.getSharedBy()).getUserName());
				result.add(v);
			}
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/year/{year}",method=RequestMethod.GET)
	public Iterable<Video> filter(@PathVariable String year)
	{
		String start = "01-01-"+year;
		String end = "31-12-"+year;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date startDt = null;
		Date endDt = null;
		try
		{
			startDt = dateFormat.parse(start);
			endDt = dateFormat.parse(end);
		}catch(Exception e)
		{
			
		}
		Iterator<Video> all = videoRepo.findAll().iterator();
		ArrayList<Video> result = new ArrayList<Video>();
		while(all.hasNext())
		{
			Video v = all.next();
			if(v.getDate().before(endDt)&&v.getDate().after(startDt))
				result.add(v);
		}
		return result;
	}
	
}
