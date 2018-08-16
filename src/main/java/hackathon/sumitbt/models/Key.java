package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="query")
public class Key
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long keyId;
	
	@NotNull
	private String searchKey;
	
	@NotNull
	private long fieldId;
	
	@NotNull
	private String scrapped;
	
	@NotNull
	private String type;
	
	public Key(){}
	
	public Key(String searchKey)
	{
		this.searchKey = searchKey;
	}
	
	public String getSearchKey()
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}

	public long getKeyId()
	{
		return keyId;
	}

	public void setKeyId(long keyId)
	{
		this.keyId = keyId;
	}

	public String getScrapped()
	{
		return scrapped;
	}

	public void setScrapped(String scrapped)
	{
		this.scrapped = scrapped;
	}

	public long getFieldId()
	{
		return fieldId;
	}

	public void setFieldId(long fieldId)
	{
		this.fieldId = fieldId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
	
}