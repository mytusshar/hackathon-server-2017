package hackathon.sumitbt.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import hackathon.sumitbt.models.SubCategory;

public interface SubCategoryRepo extends CrudRepository<SubCategory, Long>
{
	public Collection<SubCategory> findByCategoryId(long categoryId);
}
