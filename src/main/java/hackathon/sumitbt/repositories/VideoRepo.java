package hackathon.sumitbt.repositories;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.Video;

public interface VideoRepo extends CrudRepository<Video, Long>
{
	public Collection<Video> findByType(String type);
}
