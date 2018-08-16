package hackathon.sumitbt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="email_verification_token")
public class EmailVerificationToken
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;
    
	@NotNull
    private String token;
	
	@NotNull
	@Column(unique=true)
	private long userId;
	
	@NotNull
	private boolean verified;
	
	public EmailVerificationToken()
	{}

	public EmailVerificationToken(String token, long userId)
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

	public boolean isVerified()
	{
		return verified;
	}

	public void setVerified(boolean verified)
	{
		this.verified = verified;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
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
