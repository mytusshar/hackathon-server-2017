package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.PasswordResetToken;

public interface PasswordResetTokenRepo extends CrudRepository<PasswordResetToken, Long>
{
	public PasswordResetToken findByToken(String token);
	
	public PasswordResetToken findByUserId(long userId);
}