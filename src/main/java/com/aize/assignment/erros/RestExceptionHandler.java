package com.aize.assignment.erros;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handle(NotFoundException e) {
		return generateResponseEntity("Recourse Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ApiErrorResponse> handle(Exception e) {
		return generateResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidRequestException.class)
	protected ResponseEntity<ApiErrorResponse> handle(InvalidRequestException e) {
		return generateResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordNotFoundExcption.class)
	protected ResponseEntity<ApiErrorResponse> handle(RecordNotFoundExcption e) {
		return generateResponseEntity("Record Not Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedRequestException.class)
	protected ResponseEntity<ApiErrorResponse> handle(UnauthorizedRequestException e) {
		return generateResponseEntity("Unauthorized Request", HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = CartEmptyException.class)
	public ResponseEntity exception(CartEmptyException exception) {
		return new ResponseEntity<>("Cart Empty", HttpStatus.NO_CONTENT);
	}
	private ResponseEntity generateResponseEntity(String message, HttpStatus httpStatus) {
		ApiErrorResponse error = new ApiErrorResponse();
		error.setError(message);

		return new ResponseEntity(error, httpStatus);
	}



	public class ApiErrorResponse {
		private String error;
		private Integer errorCode;

		public ApiErrorResponse() {
			super();
		}

		public String getError() {
			return error;
		}

		public void setError(String message) {
			this.error = message;
		}

		public void setErrorCode(Integer errorCode) {
			this.errorCode = errorCode;
		}

		public Integer getErrorCode() {
			return errorCode;
		}
	}
}