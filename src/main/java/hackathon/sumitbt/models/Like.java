package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="likes")
public class Like
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long likeId;
	
	@NotNull
	private long userId;
	
	@NotNull
	private long videoId;
	
	public Like(){}
	
	public Like(long videoId, long userId)
	{
		this.userId = userId;
		this.videoId = videoId;
	}

	public long getLikeId()
	{
		return likeId;
	}

	public void setLikeId(long likeId)
	{
		this.likeId = likeId;
	}

	public long getVideoId()
	{
		return videoId;
	}

	public void setVideoId(long videoId)
	{
		this.videoId = videoId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}
	
}
