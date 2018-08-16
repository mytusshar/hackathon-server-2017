package hackathon.sumitbt.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
 
	@Override
	public void configure(WebSecurity web) throws Exception
	{
	    web.ignoring()
	    	.antMatchers(HttpMethod.POST,"/user")
	    	.antMatchers(HttpMethod.POST, "/reset/{token}")
	    	.antMatchers(HttpMethod.GET, "/verify/{token}")
	    	.antMatchers(HttpMethod.GET, "/login")
	    	.antMatchers(HttpMethod.POST, "/reset");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
    		.anyRequest().fullyAuthenticated()
    		.and().httpBasic().and().
    		csrf().disable();
	}
}