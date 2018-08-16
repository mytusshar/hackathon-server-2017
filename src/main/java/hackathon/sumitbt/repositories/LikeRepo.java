package hackathon.sumitbt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.Like;

public interface LikeRepo extends CrudRepository<Like, Long>
{
	public List<Like> findByUserId(long userId); 
}
