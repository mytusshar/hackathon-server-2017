package hackathon.sumitbt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.WatchLater;

public interface WatchLaterRepo extends CrudRepository<WatchLater, Long>
{
	public List<WatchLater> findByUserId(long userId);
}
