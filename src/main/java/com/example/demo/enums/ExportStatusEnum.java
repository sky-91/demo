package com.example.demo.enums;

/**
 * @author qsky on 2018/3/27
 */
public enum ExportStatusEnum {

	/**
	 * 进行中
	 */
	IN_PROGRESS("IN_PROGRESS", "进行中"),

	/**
	 * 已完成
	 */
	COMPLETED("COMPLETED", "已完成"),

	/**
	 * 错误
	 */
	ERROR("ERROR", "错误");

	private String name;

	private String zhLabel;

	public String getName() {
		return name;
	}

	public String getZhLabel() {
		return zhLabel;
	}

	ExportStatusEnum(String name, String zhLabel) {
		this.name = name;
		this.zhLabel = zhLabel;
	}

	public String getLabel(String name) {
		for (ExportStatusEnum status : ExportStatusEnum.values()) {
			if (status.name.equals(name)) {
				return status.zhLabel;
			}
		}
		return null;
	}
}
