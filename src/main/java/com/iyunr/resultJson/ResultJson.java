package com.iyunr.resultJson;

import com.iyunr.exceptionStatus.ResultStatusEnum;

/**
 * 
    * @ClassName: Result  
    * @Description: TODO 用于封装返回结果的json实体
    * @author Administrator  
    * @date 2017年10月30日  
    *
 */
public class ResultJson {
	private Meta meta;
	private Object object;
	
	/**
	 * @Description: TODO 成功的方法封装 
	 * @Title: success 
	 * @param @param object 提示语
	 * @param @param define 用于去枚举类中查询的提示语
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	public ResultJson success(Object object, String define){
		this.meta = new Meta(ResultStatusEnum.getCode(define), ResultStatusEnum.getMsg(define));
		this.object = object;
		return this;
	}
	
	public ResultJson failure(String define){
		this.meta = new Meta(ResultStatusEnum.getCode(define), ResultStatusEnum.getMsg(define));
		return this;
	}
	
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
}

class Meta{
	private int status;
	private String message;
	
	public Meta(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
