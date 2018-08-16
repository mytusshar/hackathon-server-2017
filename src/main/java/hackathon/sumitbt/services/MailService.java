package hackathon.sumitbt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import hackathon.sumitbt.models.User;
import hackathon.sumitbt.repositories.EmailVerificationTokenRepo;
import hackathon.sumitbt.repositories.PasswordResetTokenRepo;

@Service
public class MailService
{
	private JavaMailSender jms;
	
	@Autowired
	public MailService(JavaMailSender jms)
	{
		this.jms = jms;
	}
	
	@Autowired
	private EmailVerificationTokenRepo emailVerificationTokenRepo;
	
	@Autowired
	private PasswordResetTokenRepo passwordResetTokenRepo;
	
	public void sendEmailVerificationMail(User user) throws MailException
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("mytusshar@gmail.com");
		mail.setSubject("Email Verification");
		mail.setText("Click the below link to confirm your account with<b> BioTech App by Team ALPHAS </b>\n"+"http://192.168.43.213:8090/verify/"+emailVerificationTokenRepo.findByUserId(user.getUserId()).getToken());
		jms.send(mail);
	}
	
	public void sendPasswordResetMail(User user) throws MailException
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("mytusshar@gmail.com");
		mail.setSubject("Password Reset");
		mail.setText("Click the below link to reset Password\n"+"http://localhost:8090/reset/"+passwordResetTokenRepo.findByUserId(user.getUserId()).getToken());
		jms.send(mail);
	}
}
