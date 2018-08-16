package hackathon.sumitbt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackathon.sumitbt.other.Response;

@RestController
public class AuthenticationControllers
{
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public Response login()
	{
		System.out.println("here");
		Response response = new Response();
		return response;
	}
}
