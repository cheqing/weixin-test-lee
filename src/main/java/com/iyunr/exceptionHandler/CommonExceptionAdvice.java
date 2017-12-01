package com.iyunr.exceptionHandler;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.iyunr.resultJson.ResultJson;

/**
 * 
    * @ClassName: CommonExceptionAdvice  
    * @Description: TODO 全局异常处理  
    * @author Administrator  
    * @date 2017年10月30日  
    *
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice{
	private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	/**
	 * 
	 * @Title: handleHttpMessageNotReadableException  
	 * @Description: TODO 400 - Bad Request
	 * @param @param e
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResultJson handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		logger.error("参数解析失败！", e);
		return new ResultJson().failure("required_parameter_is_not_present"); 
	}
	
	  /**
	   * 400 - Bad Request
	   * 当请求条件的参数不满足实体类的注解限制条件是将会出发该异常
	   */
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResultJson handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
	    logger.error("参数验证失败", e);
	    BindingResult result = e.getBindingResult();
	    FieldError error = result.getFieldError();
	    String field = error.getField();
	    String code = error.getDefaultMessage();
	    String message = String.format("%s:%s", field, code);
	    return new ResultJson().failure(message);
	  }

	  /**
	   * 400 - Bad Request
	   */
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(BindException.class)
	  public ResultJson handleBindException(BindException e) {
	    logger.error("参数绑定失败", e);
	    BindingResult result = e.getBindingResult();
	    FieldError error = result.getFieldError();
	    String field = error.getField();
	    String code = error.getDefaultMessage();
	    String message = String.format("%s:%s", field, code);
	    return new ResultJson().failure(message);
	  }

	  /**
	   * 400 - Bad Request
	   */
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(ConstraintViolationException.class)
	  public ResultJson handleServiceException(ConstraintViolationException e) {
	    logger.error("参数验证失败", e);
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    ConstraintViolation<?> violation = violations.iterator().next();
	    String message = violation.getMessage();
	    return new ResultJson().failure("parameter:" + message);
	  }

	  /**
	   * 400 - Bad Request
	   */
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler(ValidationException.class)
	  public ResultJson handleValidationException(ValidationException e) {
	    logger.error("参数验证失败", e);
	    return new ResultJson().failure("validation_exception");
	  }

	  /**
	   * 405 - Method Not Allowed
	   */
	  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	  public ResultJson handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
	    logger.error("不支持当前请求方法", e);
	    return new ResultJson().failure("request_method_not_supported");
	  }

	  /**
	   * 415 - Unsupported Media Type
	   */
	  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	  public ResultJson handleHttpMediaTypeNotSupportedException(Exception e) {
	    logger.error("不支持当前媒体类型", e);
	    return new ResultJson().failure("content_type_not_supported");
	  }

	  /**
	   * 自定义异常处理
	   * 500 - Internal Server Error
	   */
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(ServiceException.class)
	  public ResultJson handleServiceException(ServiceException e) {
	    logger.error("业务逻辑异常【自定义异常处理】", e);
	    return new ResultJson().failure(e.getMessage());
	  }

	  /**
	   * 500 - Internal Server Error
	   */
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(Exception.class)
	  public ResultJson handleException(Exception e) {
		  if(e instanceof NoHandlerFoundException){//有其他的异常可以继续添加
			  logger.error("NOT_FOUND-404异常", e);
			  return new ResultJson().failure("NOT_FOUND");
		  }else if (e instanceof ArithmeticException){
			  logger.error("BY_ZERO-除零异常", e);
			  return new ResultJson().failure("BY_ZERO");
		  }else if(e instanceof NullPointerException){
			  logger.error("NULLPOINTEXCEPTION-空指针异常", e);
			  return new ResultJson().failure("NULLPOINTEXCEPTION");
		  }else{
			  logger.error("通用异常", e);
			  return new ResultJson().failure("通用异常：" + e.getMessage());
		  }
	  }

	  /**
	   * 操作数据库出现异常:名称重复，外键关联
	   */
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler(DataIntegrityViolationException.class)
	  public ResultJson handleException(DataIntegrityViolationException e) {
	    logger.error("操作数据库出现异常:", e);
	    return new ResultJson().failure("操作数据库出现异常：字段重复、有外键关联等");
	  }
}
