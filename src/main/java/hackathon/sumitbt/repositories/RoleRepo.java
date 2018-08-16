package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.Role;

public interface RoleRepo extends CrudRepository<Role, Long>
{
	
}
