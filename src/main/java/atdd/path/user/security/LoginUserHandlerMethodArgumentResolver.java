package atdd.path.user.security;

import atdd.path.application.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private UserService userService;

	public LoginUserHandlerMethodArgumentResolver(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
								  final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
		String email = (String) webRequest.getAttribute("email", SCOPE_REQUEST);
		return userService.findMemberByEmail(email);
	}
}
