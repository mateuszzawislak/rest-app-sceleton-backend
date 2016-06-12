package spring.web.app.skeleton.exception;

import org.springframework.http.HttpStatus;

public class IllegalInputException extends WebAppException {

	private static final long serialVersionUID = -5815485207599866871L;

	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

	public IllegalInputException() {
		super(STATUS, null);
	}

	public IllegalInputException(String message) {
		super(message, STATUS, null);
	}

}
