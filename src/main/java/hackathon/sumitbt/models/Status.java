package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="status")
public class Status
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long statusId;
	
	@NotNull
	private String status;
	
	@NotNull
	private String description;
	
	public Status(){}
	
	public Status(String status, String description)
	{
		this.status = status;
		this.description = description;
	}
	

	public long getStatusId()
	{
		return statusId;
	}

	public void setStatusId(long statusId)
	{
		this.statusId = statusId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
}
