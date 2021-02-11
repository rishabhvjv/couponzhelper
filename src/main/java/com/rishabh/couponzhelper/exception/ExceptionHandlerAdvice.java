package com.rishabh.couponzhelper.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody CustomExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {
		
		CustomExceptionResponse error = new CustomExceptionResponse();
		error.setErrorCode("500");
		error.setErrorMessage("Error- "+exception.getMessage());
		error.setRequestPath(request.getRequestURI());
		exception.printStackTrace();
		return error;
	}
}
