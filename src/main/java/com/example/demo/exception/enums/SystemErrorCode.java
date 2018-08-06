package com.example.demo.exception.enums;

/**
 * @author qsky
 */
public enum SystemErrorCode {

	/**
	 * 注入方法参数错误
	 */
	METHOD_PARAMETER(3),

	/**
	 * 成员变量解析错误
	 */
	FIELD_ACCESS(4),

	/**
	 * model buildEsJson 错误
	 */
	ES_JSON_ERROR(12),

	/**
	 * 方法入参验证
	 */
	METHOD_ARGUMENT(400),

	/**
	 * 权限验证
	 */
	PERMISSION(403),

	/**
	 * 服务器上的资源没找到
	 */
	SOURCE_NOT_FOUND(404),

	/**
	 * 服务器内部代码错误
	 */
	INTERNAL_SERVER_ERROR(500),

	/**
	 * json解析
	 */
	JSON_PARSE(510),

	/**
	 * 重复录入
	 */
	DUPLICATE_KEY(62);

	private Integer errorCode;

	SystemErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
