package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.SharedVideos;

public interface SharedVideosRepo extends CrudRepository<SharedVideos, Long>
{
	public Iterable<SharedVideos> findBySharedTo(long sharedTo);
}
