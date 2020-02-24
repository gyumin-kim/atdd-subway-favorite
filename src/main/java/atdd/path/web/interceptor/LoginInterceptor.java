package atdd.path.web.interceptor;

import atdd.path.application.JwtTokenProvider;
import atdd.path.application.exception.InvalidJwtAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	private JwtTokenProvider jwtTokenProvider;

	public LoginInterceptor(final JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
		String token = jwtTokenProvider.resolveToken(request);
		if (token == null || !jwtTokenProvider.validateToken(token)) {
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
		}
		String email = jwtTokenProvider.getUserEmail(token);
		request.setAttribute("email", email);
		return true;
	}
}
