package com.Anemoi.InvestorRelation.DataIngestion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class DataIngestionModel {

	private long fileId;

	private int npFileId;

	private String client;

	private String documentType;

	private String analystName;

	private String reportType;

	private Date reportDate;

	private String peerName;

	private String currency;

	private String units;

	private String fileName;

	private String fileType;

	private byte[] fileData;

	private String denomination;

	private String pagenumbers;

	private String status;

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public int getNpFileId() {
		return npFileId;
	}

	public void setNpFileId(int npFileId) {
		this.npFileId = npFileId;
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

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getPeerName() {
		return peerName;
	}

	public void setPeerName(String peerName) {
		this.peerName = peerName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
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

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getPagenumbers() {
		return pagenumbers;
	}

	public void setPagenumbers(String pagenumbers) {
		this.pagenumbers = pagenumbers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DataIngestionModel(long fileId, int npFileId, String client, String documentType, String analystName,
			String reportType, Date reportDate, String peerName, String currency, String units, String fileName,
			String fileType, byte[] fileData, String denomination, String pagenumbers, String status) {
		super();
		this.fileId = fileId;
		this.npFileId = npFileId;
		this.client = client;
		this.documentType = documentType;
		this.analystName = analystName;
		this.reportType = reportType;
		this.reportDate = reportDate;
		this.peerName = peerName;
		this.currency = currency;
		this.units = units;

		this.fileName = fileName;
		this.fileType = fileType;
		this.fileData = fileData;
		this.denomination = denomination;
		this.pagenumbers = pagenumbers;
		this.status = status;
	}

	public DataIngestionModel() {
		super();
	}

}