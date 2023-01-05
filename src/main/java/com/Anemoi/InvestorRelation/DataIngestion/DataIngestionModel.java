package com.Anemoi.InvestorRelation.DataIngestion;

import java.sql.Date;

public class DataIngestionModel {
	
	private int fileId;
	
	private String client;
	
	private String documentType;
	
	private String analystName;
	
	private Date reportFrom;
	
	private Date reportTo;
	
	private String fileName;
	
	private String fileType;
	
	private byte[] fileData;

	public DataIngestionModel(int fileId, String client, String documentType, String analystName, Date reportFrom,
			Date reportTo, String fileName, String fileType, byte[] fileData) {
		super();
		this.fileId = fileId;
		this.client = client;
		this.documentType = documentType;
		this.analystName = analystName;
		this.reportFrom = reportFrom;
		this.reportTo = reportTo;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileData = fileData;
	}

	public DataIngestionModel() {
		super();
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getAnalystName() {
		return analystName;
	}

	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}

	public Date getReportFrom() {
		return reportFrom;
	}

	public void setReportFrom(Date reportFrom) {
		this.reportFrom = reportFrom;
	}

	public Date getReportTo() {
		return reportTo;
	}

	public void setReportTo(Date reportTo) {
		this.reportTo = reportTo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	
}