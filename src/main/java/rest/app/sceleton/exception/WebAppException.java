package rest.app.sceleton.exception;

import org.springframework.http.HttpStatus;

public class WebAppException extends RuntimeException {
	
	private static final long serialVersionUID = -4399308767504080298L;

	private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	private final HttpStatus status;
	private final Integer code;

	public WebAppException() {
		this(STATUS, STATUS.value());
	}

	protected WebAppException(HttpStatus status, Integer code) {
		this(null, status, code);
	}

	protected WebAppException(String message, HttpStatus status, Integer code) {
		super(message);
		this.status = status;
		this.code = code;
	}

	public final Integer getCode() {
		return code;
	}

	public final HttpStatus getStatus() {
		return status;
	}

}
