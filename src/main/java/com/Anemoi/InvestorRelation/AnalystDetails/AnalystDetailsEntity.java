package com.Anemoi.InvestorRelation.AnalystDetails;

public class AnalystDetailsEntity {
	
	private String analystId;
	
	private String analystName;
	
	private String pocName;
	
	private String pocEmailId;
	
	private long createdOn;

	public String getAnalystId() {
		return analystId;
	}

	public void setAnalystId(String analystId) {
		this.analystId = analystId;
	}

	public String getAnalystName() {
		return analystName;
	}

	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getPocEmailId() {
		return pocEmailId;
	}

	public void setPocEmailId(String pocEmailId) {
		this.pocEmailId = pocEmailId;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public AnalystDetailsEntity(String analystId, String analystName, String pocName, String pocEmailId,
			long createdOn) {
		super();
		this.analystId = analystId;
		this.analystName = analystName;
		this.pocName = pocName;
		this.pocEmailId = pocEmailId;
		this.createdOn = createdOn;
	}

	public AnalystDetailsEntity() {
		super();
	}
	
	

}
