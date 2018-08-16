package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="area")
public class Area
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long areaId;
	
	@NotNull
	private String area;
	
	@NotNull
	private String description;
	
	@NotNull
	private String preview;
	
	public Area(){}
	
	public Area(String area ,String description, String preview)
	{
		this.area = area;
		this.description = description;
		this.preview = preview;
	}

	public long getAreaId()
	{
		return areaId;
	}

	public void setAreaId(long areaId)
	{
		this.areaId = areaId;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
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
