package com.example.demo.exception;


import org.springframework.http.HttpStatus;

/**
 * 异常基类
 *
 * @author qsky
 */
public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = 486121960824749388L;

	/**
	 * 错误码
	 */
	private final Integer errCode;

	/**
	 * 错误信息填充参数
	 */
	private final transient Object[] messageArgs;

	/**
	 * HTTP状态码
	 */
	private final HttpStatus status;

	/**
	 * erp异常构造函数
	 *
	 * @param errorCode 错误码
	 * @param errorCodeOffset 错误码偏移量
	 * @param status HTTP状态码
	 */
	protected GeneralException(Integer errorCode, Integer errorCodeOffset, Throwable cause,
			HttpStatus status) {
		this(errorCode, errorCodeOffset, status, cause);
	}

	/**
	 * erp异常构造函数
	 *
	 * @param errorCode 错误码
	 * @param errorCodeOffset 错误码偏移量
	 * @param status HTTP状态码
	 * @param messageArgs 错误信息填充参数
	 */
	protected GeneralException(Integer errorCode, Integer errorCodeOffset, HttpStatus status,
			Throwable cause, Object... messageArgs) {
		super(cause);
		this.errCode = errorCode + errorCodeOffset;
		this.status = status;
		this.messageArgs = messageArgs;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
