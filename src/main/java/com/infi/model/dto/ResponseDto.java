package com.infi.model.dto;

public class ResponseDto<T> {

	/**
	 * 表示服务器没报错
	 */
	private boolean success;

	private boolean operationSuccess;

	public boolean isOperationSuccess() {
		return operationSuccess;
	}

	public void setOperationSuccess(boolean operationSuccess) {
		this.operationSuccess = operationSuccess;
	}

	private T responseModel;

	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getResponseModel() {
		return responseModel;
	}

	public void setResponseModel(T responseModel) {
		this.responseModel = responseModel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 表示纯获取数据成功，客户端不会多弹框提示
	 * @param t
	 * @return
	 */
	public static <T> ResponseDto<T> DataSuccess(T t) {
		ResponseDto<T> result = new ResponseDto<T>();
		result.setSuccess(true);
		result.setResponseModel(t);
		return result;
	}

	public static <T> ResponseDto<T> Error(String message) {
		ResponseDto<T> result = ResponseDto.DataSuccess(null);
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}

	/**
	 * 只是表示操作成功，客户端进行了一项操作，如：启用，禁用，删除，添加保存，编辑保存等，这种
	 * 情况下，客户端会默认弹出提示框提示操作成功
	 */
	@SuppressWarnings("rawtypes")
	public static ResponseDto OperationSuccess(boolean success) {
		ResponseDto result = new ResponseDto();
		result.setSuccess(true);//表示这次请求没有报错
		result.setOperationSuccess(success);//这次操作是否成功
		return result;
	}
}
