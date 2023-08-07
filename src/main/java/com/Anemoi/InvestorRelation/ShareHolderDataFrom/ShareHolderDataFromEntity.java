package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

public class ShareHolderDataFromEntity {

	private String shareId;

	private String clientName;

	private String portfolioId;

	private String folio;

	private String shareholderName;

	private String holdingValue;

	private String holdingPercentage;
	
	private String holdingCost;

	private String minorCode;
	
	private String fundGroup;

	private String date;

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getShareholderName() {
		return shareholderName;
	}

	public void setShareholderName(String shareholderName) {
		this.shareholderName = shareholderName;
	}

	public String getHoldingValue() {
		return holdingValue;
	}

	public void setHoldingValue(String holdingValue) {
		this.holdingValue = holdingValue;
	}

	public String getHoldingPercentage() {
		return holdingPercentage;
	}

	public void setHoldingPercentage(String holdingPercentage) {
		this.holdingPercentage = holdingPercentage;
	}

	public String getHoldingCost() {
		return holdingCost;
	}

	public void setHoldingCost(String holdingCost) {
		this.holdingCost = holdingCost;
	}

	public String getMinorCode() {
		return minorCode;
	}

	public void setMinorCode(String minorCode) {
		this.minorCode = minorCode;
	}

	public String getFundGroup() {
		return fundGroup;
	}

	public void setFundGroup(String fundGroup) {
		this.fundGroup = fundGroup;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ShareHolderDataFromEntity(String shareId, String clientName, String portfolioId, String folio,
			String shareholderName, String holdingValue, String holdingPercentage, String holdingCost, String minorCode,
			String fundGroup, String date) {
		super();
		this.shareId = shareId;
		this.clientName = clientName;
		this.portfolioId = portfolioId;
		this.folio = folio;
		this.shareholderName = shareholderName;
		this.holdingValue = holdingValue;
		this.holdingPercentage = holdingPercentage;
		this.holdingCost = holdingCost;
		this.minorCode = minorCode;
		this.fundGroup = fundGroup;
		this.date = date;
	}

	public ShareHolderDataFromEntity() {
		super();
	}
	
	

}