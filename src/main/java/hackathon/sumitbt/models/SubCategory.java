package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sub_category")
public class SubCategory
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long subCategoryId;
	
	@NotNull
	private String subCategory;
	
	@NotNull
	private long categoryId;
	
	@NotNull
	private String description;
	
	@NotNull
	private String preview;
	
	public SubCategory(){}
	
	public SubCategory(String subCategory, long categoryId, String description, String preview)
	{
		this.categoryId = categoryId;
		this.subCategory = subCategory;
		this.description = description;
		this.preview = preview;
	}

	public long getSubCategoryId()
	{
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId)
	{
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategory()
	{
		return subCategory;
	}

	public void setSubCategory(String subCategory)
	{
		this.subCategory = subCategory;
	}

	public long getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(long categoryId)
	{
		this.categoryId = categoryId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPreview()
	{
		return preview;
	}

	public void setPreview(String preview)
	{
		this.preview = preview;
	}
	
}