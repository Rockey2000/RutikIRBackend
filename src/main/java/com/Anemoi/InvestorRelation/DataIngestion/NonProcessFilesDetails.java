package com.Anemoi.InvestorRelation.DataIngestion;

public class NonProcessFilesDetails {

	private int npFileId;

	private String client;
	
	private String clientId;

	private String fileName;

	private String fileType;

	private byte[] FileData;
	
    private String hashCode;

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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
		return FileData;
	}

	public void setFileData(byte[] fileData) {
		FileData = fileData;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public NonProcessFilesDetails(int npFileId, String client, String clientId, String fileName, String fileType,
			byte[] fileData, String hashCode) {
		super();
		this.npFileId = npFileId;
		this.client = client;
		this.clientId = clientId;
		this.fileName = fileName;
		this.fileType = fileType;
		FileData = fileData;
		this.hashCode = hashCode;
	}

	public NonProcessFilesDetails() {
		super();
	}
    
    

}