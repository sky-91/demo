package com.example.demo.exception;

import com.example.demo.exception.enums.ErrorCode;
import com.example.demo.exception.enums.SystemErrorCode;
import org.springframework.http.HttpStatus;

/**
 * 资源未找到异常，包含路径、文件、数据等
 *
 * @author qsky on 2017/6/23.
 */
public class SourceNotFoundException extends GeneralException {

	private static final long serialVersionUID = 6543983972931343200L;

	public SourceNotFoundException() {
		super(ErrorCode.SYSTEM, SystemErrorCode.SOURCE_NOT_FOUND.getErrorCode(), HttpStatus.NOT_FOUND,
				null);
	}

	public SourceNotFoundException(Integer errorCode, Integer errorCodeOffset,
			Object... messageArgs) {
		super(errorCode, errorCodeOffset, HttpStatus.NOT_FOUND, null, messageArgs);
	}
}
