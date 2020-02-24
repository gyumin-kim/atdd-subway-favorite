package atdd.path.web;

import atdd.path.user.security.LoginUserHandlerMethodArgumentResolver;
import atdd.path.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private LoginInterceptor loginInterceptor;
	private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

	public WebMvcConfig(final LoginInterceptor loginInterceptor,
						final LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver) {
		this.loginInterceptor = loginInterceptor;
		this.loginUserHandlerMethodArgumentResolver = loginUserHandlerMethodArgumentResolver;
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns(List.of("/users/me", "/favorites/**"));
	}

	@Override
	public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(loginUserHandlerMethodArgumentResolver);
	}
}
