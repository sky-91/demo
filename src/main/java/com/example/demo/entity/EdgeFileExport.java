package com.example.demo.entity;

import com.example.demo.enums.ExportStatusEnum;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXPORT_RECORD")
public class EdgeFileExport {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "REQUEST_TIME")
	private Date requestTime;

	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "INPUT_NAME")
	private String inputName;

	@Column(name = "RELATIONS")
	private String relations;

	@Column(name = "DATA_DATE")
	private String dataDate;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_SIZE")
	private String fileSize;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "JSON_FILE")
	private String jsonFile;

	@Column(name = "AREAS")
	private String areas;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private ExportStatusEnum status;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "EXPORT_TYPE")
	private String exportType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getRelations() {
		return relations;
	}

	public void setRelations(String relations) {
		this.relations = relations;
	}

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getJsonFile() {
		return jsonFile;
	}

	public void setJsonFile(String jsonFile) {
		this.jsonFile = jsonFile;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public ExportStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ExportStatusEnum status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
}
