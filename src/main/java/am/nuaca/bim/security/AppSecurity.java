package am.nuaca.bim.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurity extends WebSecurityConfigurerAdapter {

	private final AuthProvider authProvider;

	public AppSecurity(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http)
			throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/registration")
				.permitAll()
				.antMatchers("/login")
				.permitAll()
				.anyRequest()
				.authenticated();

		http.formLogin()
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successForwardUrl("/");

		http.logout().permitAll().logoutUrl("/logout").invalidateHttpSession(true);
	}
}
