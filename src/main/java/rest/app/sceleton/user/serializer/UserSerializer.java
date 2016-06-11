package rest.app.sceleton.user.serializer;

import rest.app.sceleton.user.dto.UserDto;
import rest.app.sceleton.user.model.User;

public class UserSerializer {

	public static UserDto toDto(User user) {
		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setEmail(user.getEmail());

		return dto;
	}

}
