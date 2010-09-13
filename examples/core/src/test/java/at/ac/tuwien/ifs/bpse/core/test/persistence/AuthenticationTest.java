package at.ac.tuwien.ifs.bpse.core.test.persistence;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-beans.xml"})
public class AuthenticationTest {
	
	@Autowired
	private DaoAuthenticationProvider authprov;
	
	@Autowired
	private UserDetailsService uds;
	
	@Test
	public void createUserDetailsService() {
		UserDetails ud = uds.loadUserByUsername("user@qse");
		System.out.println(ud.getUsername());
	}
	
	@Test
	public void test() {
		//Md5PasswordEncoder penc = new Md5PasswordEncoder();	
		//System.out.println(penc.encodePassword("passwd", "enohpeleTnaCniT"));
		
		Authentication authentication = null;
		try {
		authentication = authprov.authenticate(new UsernamePasswordAuthenticationToken("user@qse", "user"));
		} catch (BadCredentialsException e) {
			UserDetails ud = (UserDetails) e.getExtraInformation();
			System.out.println(ud.getPassword());
		}
		//SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean authenticated = authentication.isAuthenticated();
	}

}
