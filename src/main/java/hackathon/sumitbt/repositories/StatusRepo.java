package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.Status;

public interface StatusRepo extends CrudRepository<Status, Long>
{
	
}