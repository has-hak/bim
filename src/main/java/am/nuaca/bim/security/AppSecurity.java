package am.nuaca.bim.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Ani Khachatryan on 15-Sep-21.
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
		http.cors()
				.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/api/registration")
				.permitAll()
				.antMatchers("/api/login")
				.permitAll()
				.antMatchers("/api/logout")
				.permitAll()
				.antMatchers("/api/languages/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.disable()
				.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(securityException401EntryPoint());

		http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

		http.httpBasic().disable();
	}

	@Bean
	public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
		RequestBodyReaderAuthenticationFilter authenticationFilter = new RequestBodyReaderAuthenticationFilter();
		authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationFilter;
	}

	@Bean
	public Http401AuthenticationEntryPoint securityException401EntryPoint() {
		return new Http401AuthenticationEntryPoint();
	}
}
