package eminimki.com.JTS;

import eminimki.com.JTS.Common.Utilities.Exceptions.BusinessException;
import eminimki.com.JTS.Common.Utilities.Exceptions.ProblemDetails;
import eminimki.com.JTS.Common.Utilities.Exceptions.ValidationProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.NoSuchElementException;

@SpringBootApplication
@RestControllerAdvice
public class JtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtsApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException){
		ProblemDetails problemDetails = new ProblemDetails(businessException.getMessage());
		return problemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();

		validationProblemDetails.setMessage("Validation.Exception");
		validationProblemDetails.setValidationErrors(new HashMap<String,String>());

		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return validationProblemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(NoSuchElementException noSuchElementException){
		ProblemDetails problemDetails = new ProblemDetails(noSuchElementException.getMessage());
		return problemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
		ProblemDetails problemDetails = new ProblemDetails(httpRequestMethodNotSupportedException.getMessage());
		return problemDetails;
	}






}
