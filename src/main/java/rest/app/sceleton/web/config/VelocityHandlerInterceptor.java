package rest.app.sceleton.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import rest.app.sceleton.authentication.IAuthenticationFacade;
import rest.app.sceleton.user.dto.UserDto;

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
