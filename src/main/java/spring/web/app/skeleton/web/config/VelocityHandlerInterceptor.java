package spring.web.app.skeleton.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.web.app.skeleton.authentication.IAuthenticationFacade;
import spring.web.app.skeleton.user.dto.UserDto;

public class VelocityHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	IAuthenticationFacade authenticationFacade;

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			modelAndView.getModelMap().addAttribute("STATIC_ATTRIBUTE", "exampleValue");

			UserDto user = authenticationFacade.getUser();
			if (user != null)
				modelAndView.getModelMap().addAttribute("USER", user);
		}
	}

}
