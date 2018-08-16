package hackathon.sumitbt.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hackathon.sumitbt.repositories.RoleRepo;
import hackathon.sumitbt.repositories.UserRepo;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter
{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService()
	{
		return new UserDetailsService()
		{
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
			{
				hackathon.sumitbt.models.User user = userRepo.findByEmail(email);
				if(user != null)
				{
					System.out.println("**********************");
					System.out.println(email);
//					System.out.println(roleRepo.findOne(user.getRoleId()).getRole());
					System.out.println("**************s********");
					return new User(user.getEmail(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
				}
				else
				{
					throw new UsernameNotFoundException("could not find the user '"+ email + "'");
				}
			}
		};
	}
}