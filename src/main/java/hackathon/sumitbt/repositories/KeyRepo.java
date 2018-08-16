package hackathon.sumitbt.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.Key;

public interface KeyRepo extends CrudRepository<Key, Long>
{
	public Collection<Key> findByType(String type);
}
