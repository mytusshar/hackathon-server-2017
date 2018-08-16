package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.EmailVerificationToken;

public interface EmailVerificationTokenRepo extends CrudRepository<EmailVerificationToken , Long>
{
	public EmailVerificationToken findByToken(String token);
	
	public EmailVerificationToken findByUserId(long id);
}
