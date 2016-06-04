package rest.app.sceleton.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import rest.app.sceleton.exception.dto.ExceptionDto;

@ControllerAdvice
public class ExceptionMapper {

	private static final Logger logger = Logger.getLogger(ExceptionMapper.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDto> toResponse(Exception exception) throws MethodArgumentNotValidException {
		if (exception instanceof MethodArgumentNotValidException) {
			return new ResponseEntity<ExceptionDto>(serializeException((MethodArgumentNotValidException) exception),
					HttpStatus.BAD_REQUEST);
		} else if (exception instanceof HttpMessageNotReadableException) {
			return new ResponseEntity<ExceptionDto>(serializeException((HttpMessageNotReadableException) exception),
					HttpStatus.BAD_REQUEST);
		}

		logger.error("Server error: {}", exception);

		return new ResponseEntity<ExceptionDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ExceptionDto serializeException(HttpMessageNotReadableException exception) {
		ExceptionDto exceptionDto = new ExceptionDto();

		exceptionDto.setCode(HttpStatus.BAD_REQUEST.value());
		exceptionDto.addMessage("invalid.request.json");

		return exceptionDto;
	}

	private ExceptionDto serializeException(MethodArgumentNotValidException exception) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setCode(HttpStatus.BAD_REQUEST.value());

		Errors errors = exception.getBindingResult();
		for (ObjectError error : errors.getAllErrors())
			exceptionDto.addMessage(error.getDefaultMessage());

		return exceptionDto;
	}

}
