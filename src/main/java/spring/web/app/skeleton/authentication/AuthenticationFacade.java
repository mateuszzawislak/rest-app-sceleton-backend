package spring.web.app.skeleton.authentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import spring.web.app.skeleton.user.dto.UserDto;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public UserDto getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
			return null;

		return (UserDto) authentication.getPrincipal();
	}
}
