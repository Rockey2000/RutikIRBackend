package com.Anemoi.InvestorRelation.whitelabeling;

public class WhiteLableingEntity {
	
	private String whitelableId;
	
	private String clientName;
  private String filePath;
	private String cssFileName;
	private String cssFileType;
	private byte[] cssFileData;
	public String getWhitelableId() {
		return whitelableId;
	}
	public void setWhitelableId(String whitelableId) {
		this.whitelableId = whitelableId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCssFileName() {
		return cssFileName;
	}
	public void setCssFileName(String cssFileName) {
		this.cssFileName = cssFileName;
	}
	public String getCssFileType() {
		return cssFileType;
	}
	public void setCssFileType(String cssFileType) {
		this.cssFileType = cssFileType;
	}
	public byte[] getCssFileData() {
		return cssFileData;
	}
	public void setCssFileData(byte[] cssFileData) {
		this.cssFileData = cssFileData;
	}
	public WhiteLableingEntity(String whitelableId, String clientName, String filePath, String cssFileName,
			String cssFileType, byte[] cssFileData) {
		super();
		this.whitelableId = whitelableId;
		this.clientName = clientName;
		this.filePath = filePath;
		this.cssFileName = cssFileName;
		this.cssFileType = cssFileType;
		this.cssFileData = cssFileData;
	}
	public WhiteLableingEntity() {
		super();
	}
	
	
}