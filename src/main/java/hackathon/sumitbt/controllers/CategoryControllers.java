package hackathon.sumitbt.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import hackathon.sumitbt.models.Area;
import hackathon.sumitbt.models.Category;
import hackathon.sumitbt.models.SubCategory;
import hackathon.sumitbt.models.Video;
import hackathon.sumitbt.other.Value;
import hackathon.sumitbt.repositories.AreaRepo;
import hackathon.sumitbt.repositories.CategoryRepo;
import hackathon.sumitbt.repositories.SubCategoryRepo;
import hackathon.sumitbt.repositories.VideoRepo;

@RestController
public class CategoryControllers
{
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	
	@Autowired
	private VideoRepo videoRepo;
	
	@Autowired
	private AreaRepo areaRepo;
	
	@ResponseBody
	@RequestMapping(value="/category",method=RequestMethod.GET)
	public Iterable<Category> categories()
	{
		return categoryRepo.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="/sub/{category_id}",method=RequestMethod.GET)
	public Iterable<SubCategory> subCategories(@PathVariable String category_id)
	{
		return subCategoryRepo.findByCategoryId(Integer.parseInt(category_id));
	}
	
	@ResponseBody
	@RequestMapping(value="/subvideos/{sub_id}",method=RequestMethod.GET)
	public Iterable<Video> videos(@PathVariable String sub_id)
	{
		Iterator<Video> i = videoRepo.findByType("c").iterator();
		ArrayList<Video> result = new ArrayList<Video>();
		while(i.hasNext())
		{
			Video temp = i.next();
			if(temp.getFieldId()==Integer.parseInt(sub_id))
				result.add(temp);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/area",method=RequestMethod.GET)
	public Iterable<Area> areas()
	{
		return areaRepo.findAll();
	}
	
	@ResponseBody
	@RequestMapping(value="/areavideos/{area_id}",method=RequestMethod.GET)
	public Iterable<Video> area_videos(@PathVariable String area_id)
	{
		Iterator<Video> i = videoRepo.findByType("a").iterator();
		ArrayList<Video> result = new ArrayList<Video>();
		while(i.hasNext())
		{
			Video temp = i.next();
			if(temp.getFieldId()==Integer.parseInt(area_id))
				result.add(temp);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/noofsub/{cat_id}",method=RequestMethod.GET)
	public Value noofsub(@PathVariable String cat_id)
	{
		Value v = new Value();
		Iterator<SubCategory> sc = subCategoryRepo.findByCategoryId(Integer.parseInt(cat_id)).iterator();
		long count =0;
		while(sc.hasNext())
		{
			sc.next();
			count++;
		}
		v.setValue(count);
		return v;
	}
	
	@ResponseBody
	@RequestMapping(value="/noofvid/{sub_id}",method=RequestMethod.GET)
	public Value noofvid(@PathVariable String sub_id)
	{
		Value r = new Value();
		Iterator<Video> v = videoRepo.findByType("c").iterator();
		long count = 0;
		while(v.hasNext())
		{
			if(v.next().getFieldId()==Integer.parseInt(sub_id))
				count++;
		}
		r.setValue(count);
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/noofvidar/{area_id}",method=RequestMethod.GET)
	public Value noofvidar(@PathVariable String area_id)
	{
		Value r = new Value();
		Iterator<Video> v = videoRepo.findByType("a").iterator();
		long count = 0;
		while(v.hasNext())
		{
			Video temp = v.next();
			if(temp.getFieldId()==Integer.parseInt(area_id))
				count++;
		}
		r.setValue(count);
		return r;
	}
}
