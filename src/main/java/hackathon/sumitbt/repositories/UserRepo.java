package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.User;

public interface UserRepo extends CrudRepository<User, Long>
{
	public User findByEmail(String email);
}
