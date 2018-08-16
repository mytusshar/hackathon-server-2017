package hackathon.sumitbt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import hackathon.sumitbt.models.EmailVerificationToken;
import hackathon.sumitbt.models.PasswordResetToken;
import hackathon.sumitbt.models.User;
import hackathon.sumitbt.repositories.EmailVerificationTokenRepo;
import hackathon.sumitbt.repositories.PasswordResetTokenRepo;
import hackathon.sumitbt.repositories.UserRepo;

@RestController
public class TokenControllers
{
	@Autowired
	private EmailVerificationTokenRepo emailVerificationTokenRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordResetTokenRepo passwordResetTokenRepo;
	
	@RequestMapping(method=RequestMethod.GET, value="/verify/{token}")
	public String getAll(@PathVariable String token)
	{
		EmailVerificationToken evt = emailVerificationTokenRepo.findByToken(token);
		if(evt==null)
		{
			return "Wrong URL!";
		}
		else
		{
			User user = userRepo.findOne(evt.getUserId());
			user.setStatusId(1);
			userRepo.save(user);
			emailVerificationTokenRepo.delete(evt);
			return "Success";
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/reset/{token}")
	public String setpassword(@PathVariable String token,@RequestBody User user)
	{
		String password = user.getPassword();
		PasswordResetToken prt = passwordResetTokenRepo.findByToken(token);
		User u = userRepo.findOne(prt.getUserId());
		u.setPassword(password);
		if(u.getStatusId()==2)
			u.setStatusId(1);
		userRepo.save(u);
		passwordResetTokenRepo.delete(prt);
		return "Success";
	}
	
}
