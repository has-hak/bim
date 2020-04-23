package am.nuaca.bim.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/api/registration")
				.permitAll()
				.antMatchers("/api/login")
				.permitAll()
				.anyRequest()
				.authenticated();

		http.formLogin()
				.loginProcessingUrl("/api/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(new SavedRequestAwareAuthenticationSuccessHandler());

		http.logout().permitAll().logoutUrl("/logout").invalidateHttpSession(true);
	}
}
