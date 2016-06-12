package spring.web.app.skeleton.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import spring.web.app.skeleton.exception.NoPermissionException;
import spring.web.app.skeleton.user.model.User;
import spring.web.app.skeleton.user.serializer.UserSerializer;
import spring.web.app.skeleton.user.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();

		User user = null;
		try {
			user = userService.authenticateByEmailAndPassword(email, password);
		} catch (NoPermissionException e) {
			throw new BadCredentialsException("invalid.email.or.password");
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream().map(
				role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(UserSerializer.toDto(user), password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
