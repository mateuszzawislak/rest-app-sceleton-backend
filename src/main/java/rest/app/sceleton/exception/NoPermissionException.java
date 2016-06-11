package rest.app.sceleton.exception;

import org.springframework.http.HttpStatus;

public class NoPermissionException extends WebAppException {

	private static final long serialVersionUID = -4856192749993778877L;

	private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;

	public NoPermissionException() {
		super(STATUS, null);
	}

	public NoPermissionException(String message) {
		super(message, STATUS, null);
	}
}
