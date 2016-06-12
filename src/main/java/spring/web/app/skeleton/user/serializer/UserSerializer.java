package spring.web.app.skeleton.user.serializer;

import spring.web.app.skeleton.user.dto.UserDto;
import spring.web.app.skeleton.user.model.User;

public class UserSerializer {

	public static UserDto toDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setEmail(user.getEmail());

		return dto;
	}

}
