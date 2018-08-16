package hackathon.sumitbt.repositories;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.Category;

public interface CategoryRepo extends CrudRepository<Category, Long>
{

}
