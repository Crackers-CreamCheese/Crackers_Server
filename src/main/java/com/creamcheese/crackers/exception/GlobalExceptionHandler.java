package com.creamcheese.crackers.exception;

import com.creamcheese.crackers.exception.CustomException.WorkHistoryNotFoundException;
import com.creamcheese.crackers.exception.CustomException.WorkspaceNotFoundException;
import com.creamcheese.crackers.exception.CustomException.LoginIdDuplicateException;
import com.creamcheese.crackers.exception.CustomException.PasswordNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;
import java.time.DateTimeException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/*================== Basic Exception ==================*/
	@ExceptionHandler(RuntimeException.class)
	protected final ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.RUNTIME_EXCEPTION)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(DateTimeException.class)
	protected final ResponseEntity<ErrorResponse> handleDateTimeException(DateTimeException e) {
		final ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.code(ErrorCode.BAD_DATE_REQUEST)
				.message(e.getMessage())
				.build();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	/**
	 * 지원하지 않은 HTTP method 호출 할 경우 발생
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("handleHttpRequestMethodNotSupportedException", e);
		return ErrorResponse.toErrorResponseEntity(ErrorCode.METHOD_NOT_ALLOWED, e.getMessage());
	}

	// vaild 오류
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors()
				.forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}



	/*================== User Exception ==================*/
	@ExceptionHandler(LoginIdDuplicateException.class)
	protected final ResponseEntity<ErrorResponse> handleLoginIdDuplicateException(LoginIdDuplicateException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.LOGINID_DUPLICATE, e.getMessage());
	}
	// 존재하지 않는 유저
	@ExceptionHandler(AccountNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleUserNotFoundException(AccountNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.ACCOUNT_NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(PasswordNotMatchException.class)
	protected final ResponseEntity<ErrorResponse> handlePasswordNotMatchException(PasswordNotMatchException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.PASSWORD_NOT_MATCH, e.getMessage());
	}

	/*================== WorkSpace Exception ==================*/
	@ExceptionHandler(WorkspaceNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleWorkSpaceNotFoundException(WorkspaceNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.WORKSPACE_NOT_FOUND, e.getMessage());
	}

	/*================== WorkHistory Exception ==================*/
	@ExceptionHandler(WorkHistoryNotFoundException.class)
	protected final ResponseEntity<ErrorResponse> handleWorkHistoryNotFoundException(WorkHistoryNotFoundException e) {
		return ErrorResponse.toErrorResponseEntity(ErrorCode.WORKHISTORY_NOT_FOUND, e.getMessage());
	}
}
