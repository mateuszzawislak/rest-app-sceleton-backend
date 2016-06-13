package spring.web.app.skeleton.exception;

import org.springframework.http.HttpStatus;

public class EntityNotExistsException extends WebAppException {

	private static final long serialVersionUID = -4856192749993778877L;

	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

	public EntityNotExistsException() {
		super(STATUS, null);
	}

	public EntityNotExistsException(String message) {
		super(message, STATUS, null);
	}
}
