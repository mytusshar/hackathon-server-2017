package hackathon.sumitbt.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="video")
public class Video
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long videoId;
	
	@NotNull
	@Column(unique=true)
	private String url;
	
	@NotNull
	private String preview;
	
	@NotNull
	private String title;
	
	@NotNull
	private String length;
	
	@NotNull
	private long views;
	
	@NotNull
	private long likes;
	
	@NotNull
	private String description;
	
	@NotNull
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@NotNull
	private long fieldId;
	
	@NotNull
	private long keyId;
	
	@NotNull
	private String type;
	
	public Video(){}
	
	public Video(String url, String preview, String title, String description, String type)
	{
		this.url = url;
		this.preview = preview;
		this.title = title;
		this.description = description;
		this.type = type;
	}

	
	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getPreview()
	{
		return preview;
	}

	public void setPreview(String preview)
	{
		this.preview = preview;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getViews()
	{
		return views;
	}

	public void setViews(long views)
	{
		this.views = views;
	}

	public long getLikes()
	{
		return likes;
	}

	public void setLikes(long likes)
	{
		this.likes = likes;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public long getVideoId()
	{
		return videoId;
	}

	public void setVideoId(long videoId)
	{
		this.videoId = videoId;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}

	public long getKeyId()
	{
		return keyId;
	}

	public void setKeyId(long keyId)
	{
		this.keyId = keyId;
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