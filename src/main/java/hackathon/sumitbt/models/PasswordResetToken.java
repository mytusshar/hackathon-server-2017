package hackathon.sumitbt.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="password_reset_token")
public class PasswordResetToken
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;
    
	@NotNull
    private String token;
	
	@NotNull
	private long userId;
	
	@NotNull
	private boolean reset;
	
	public PasswordResetToken()
	{}

	public PasswordResetToken(String token, long userId)
	{
		this.token = token;
		this.userId = userId;
	}

	public long getTokenId()
	{
		return tokenId;
	}

	public void setTokenId(long tokenId)
	{
		this.tokenId = tokenId;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public boolean isReset()
	{
		return reset;
	}

	public void setReset(boolean reset)
	{
		this.reset = reset;
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