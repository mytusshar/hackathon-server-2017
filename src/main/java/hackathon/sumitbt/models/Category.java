package hackathon.sumitbt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="category")
public class Category
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryId;
	
	@NotNull
	@Column(unique=true)
	private String category;
	
	@NotNull
	private String description;
	
	@NotNull
	private String preview;
	
	public Category(){}
	
	public Category(String category ,String description, String preview)
	{
		this.category = category;
		this.description = description;
		this.preview = preview;
	}

	public long getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(long categoryId)
	{
		this.categoryId = categoryId;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
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