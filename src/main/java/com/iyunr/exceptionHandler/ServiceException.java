package com.iyunr.exceptionHandler;

/**
    * @ClassName: ServiceException  
    * @Description: TODO 自定义异常类
    * @author Administrator  
    * @date 2017年10月30日  
    *
 */
public class ServiceException extends RuntimeException{
	  
	    /**  
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	    */  
	    
	private static final long serialVersionUID = 1L;

	public ServiceException(Object obj) {
		super(obj.toString());
	}
	
}
