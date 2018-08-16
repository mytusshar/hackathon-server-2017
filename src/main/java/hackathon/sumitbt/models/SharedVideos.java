package hackathon.sumitbt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="shared_videos")
public class SharedVideos
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long shareId;
	
	@NotNull
	private long sharedBy;
	
	@NotNull
	private long sharedTo;
	
	@NotNull
	private long videoId;
	
	public SharedVideos(){}
	
	public SharedVideos(long sharedBy, long sharedTo, long videoId)
	{
		this.sharedBy = sharedBy;
		this.sharedTo = sharedTo;
		this.videoId = videoId;
	}

	public long getShareId()
	{
		return shareId;
	}

	public void setShareId(long shareId)
	{
		this.shareId = shareId;
	}

	public long getSharedBy()
	{
		return sharedBy;
	}

	public void setSharedBy(long sharedBy)
	{
		this.sharedBy = sharedBy;
	}

	public long getSharedTo()
	{
		return sharedTo;
	}

	public void setSharedTo(long sharedTo)
	{
		this.sharedTo = sharedTo;
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