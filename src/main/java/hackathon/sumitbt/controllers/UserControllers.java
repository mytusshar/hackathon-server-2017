package hackathon.sumitbt.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import hackathon.sumitbt.models.EmailVerificationToken;
import hackathon.sumitbt.models.Like;
import hackathon.sumitbt.models.PasswordResetToken;
import hackathon.sumitbt.models.User;
import hackathon.sumitbt.models.Video;
import hackathon.sumitbt.models.WatchLater;
import hackathon.sumitbt.other.Response;
import hackathon.sumitbt.repositories.EmailVerificationTokenRepo;
import hackathon.sumitbt.repositories.LikeRepo;
import hackathon.sumitbt.repositories.PasswordResetTokenRepo;
import hackathon.sumitbt.repositories.UserRepo;
import hackathon.sumitbt.repositories.VideoRepo;
import hackathon.sumitbt.repositories.WatchLaterRepo;
import hackathon.sumitbt.services.MailService;

@RestController
public class UserControllers
{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LikeRepo likeRepo;
	
	@Autowired
	private VideoRepo videoRepo;
	
	@Autowired
	private WatchLaterRepo watchLaterRepo;
	
	@Autowired
	private EmailVerificationTokenRepo emailVerificationTokenRepo;
	
	@Autowired
	private PasswordResetTokenRepo passwordResetTokenRepo;
	
	@Autowired
	private MailService ms;
	
	@ResponseBody
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public Response signup(@RequestBody User user)
	{
		Response response = new Response();
		user.setRoleId(1);
		user.setStatusId(2);
		user.setSignupDt(new Date());
		try
		{
			User u = userRepo.save(user);
			EmailVerificationToken ect = new EmailVerificationToken(UUID.randomUUID().toString(), u.getUserId() );
			ect.setVerified(false);
			emailVerificationTokenRepo.save(ect);
			ms.sendEmailVerificationMail(u);
		}catch(Exception e)
		{
			response.setSuccess(false);
			response.setMessage(e.toString());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/reset",method=RequestMethod.POST)
	public String reset(@RequestBody User user)
	{
		User u = userRepo.findByEmail(user.getEmail());
		PasswordResetToken prt = new PasswordResetToken(UUID.randomUUID().toString(), u.getUserId());
		prt.setReset(false);
		passwordResetTokenRepo.save(prt);
		ms.sendPasswordResetMail(u);
		return "Check your mail "+u.getEmail()+" to reset password";
	}
	
	@ResponseBody
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public User show()
	{
		System.out.println("********** inside /user GET *********");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("********** inside auth ********* " + auth);
		User user = userRepo.findByEmail(auth.getName());
//		System.out.println("********** inside /user GET *********");
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/likes",method=RequestMethod.GET)
	public Iterable<Video> likes()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Iterator<Like> l = likeRepo.findByUserId(user.getUserId()).iterator();
		List<Video> result = new ArrayList<Video>();
		while(l.hasNext())
		{
			result.add(videoRepo.findOne(l.next().getVideoId()));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/like/{video_id}",method=RequestMethod.GET)
	public Response like(@PathVariable String video_id)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Response response = new Response();
		Like l = new Like();
		long userId = user.getUserId();
		long videoId = (long)Integer.parseInt(video_id);
		l.setUserId(userId);
		l.setVideoId(videoId);
		Iterator<Like> li = likeRepo.findAll().iterator();
		while(li.hasNext())
		{
			Like temp = li.next();
			if(l.getUserId()==temp.getUserId())
			{
				if(l.getVideoId()==temp.getVideoId())
				{
					likeRepo.delete(temp);
					Video v = videoRepo.findOne(videoId);
					long pre = v.getLikes();
					v.setLikes(pre-1);
					videoRepo.save(v);
					return response;
				}
			}
		}
		likeRepo.save(l);
		Video v = videoRepo.findOne(videoId);
		long pre = v.getLikes();
		v.setLikes(pre+1);
		videoRepo.save(v);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/isliked/{video_id}",method=RequestMethod.GET)
	public Response isliked(@PathVariable String video_id)
	{
		long videoId = (long)Integer.parseInt(video_id);
		Response r = new Response();
		r.setSuccess(false);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Iterator<Like> i = likeRepo.findByUserId(user.getUserId()).iterator();
		while(i.hasNext())
		{
			Like l = i.next();
			if(l.getVideoId()==videoId)
			{
				r.setSuccess(true);
				break;
			}
		}
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/later",method=RequestMethod.GET)
	public Iterable<Video> watchlater()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Iterator<WatchLater> l = watchLaterRepo.findByUserId(user.getUserId()).iterator();
		List<Video> result = new ArrayList<Video>();
		while(l.hasNext())
		{
			result.add(videoRepo.findOne(l.next().getVideoId()));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/later/{video_id}",method=RequestMethod.GET)
	public Response addlater(@PathVariable String video_id)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Response response = new Response();
		WatchLater l = new WatchLater();
		long userId = user.getUserId();
		long videoId = (long)Integer.parseInt(video_id);
		l.setUserId(userId);
		l.setVideoId(videoId);
		Iterator<WatchLater> li = watchLaterRepo.findAll().iterator();
		while(li.hasNext())
		{
			WatchLater temp = li.next();
			if(l.getUserId()==temp.getUserId())
			{
				if(l.getVideoId()==temp.getVideoId())
				{
					watchLaterRepo.delete(temp);
					return response;
				}
			}
		}
		watchLaterRepo.save(l);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/later/clear",method=RequestMethod.GET)
	public Response clearlater()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByEmail(auth.getName());
		Response response = new Response();
		Iterator<WatchLater> i = watchLaterRepo.findByUserId(user.getUserId()).iterator();
		while(i.hasNext())
		{
			watchLaterRepo.delete(i.next());
		}
		return response;
	}
}
