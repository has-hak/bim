package am.nuaca.bim.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import am.nuaca.bim.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public RequestBodyReaderAuthenticationFilter() {
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		LoginRequest loginRequest;
		try {
			String requestBody = IOUtils.toString(request.getReader());
			loginRequest = objectMapper.readValue(requestBody, LoginRequest.class);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername(), loginRequest.getPassword());

			// Allow subclasses to set the "details" property
			setDetails(request, token);
			return this.getAuthenticationManager().authenticate(token);
		}
		catch (IOException e) {
			throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
		}

		SecurityContextHolder.getContext().setAuthentication(authResult);
		if (this.eventPublisher != null) {
			this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											  AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(400);
		response.getWriter().println(failed.getMessage());
	}
}
