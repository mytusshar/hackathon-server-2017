package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="watchlater")
public class WatchLater
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long watchlaterId;
	
	@NotNull
	private long userId;
	
	@NotNull
	private long videoId;
	
	public WatchLater(){}
	
	public WatchLater(long userId, long videoId)
	{
		this.userId = userId;
		this.videoId = videoId;
	}

	public long getWatchlaterId()
	{
		return watchlaterId;
	}

	public void setWatchlaterId(long watchlaterId)
	{
		this.watchlaterId = watchlaterId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public long getVideoId()
	{
		return videoId;
	}

	public void setVideoId(long videoId)
	{
		this.videoId = videoId;
	}
	
}