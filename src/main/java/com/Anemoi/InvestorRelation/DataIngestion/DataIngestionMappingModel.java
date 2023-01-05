package com.Anemoi.InvestorRelation.DataIngestion;

public class DataIngestionMappingModel {
	
	private String mapId;
	
	private String analyst;
	
	private String companyName;
	
	private String documentType;

	private String year;
	
	private String lineItem;
	
	private String quarter;

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public String getAnalyst() {
		return analyst;
	}

	public void setAnalyst(String analyst) {
		this.analyst = analyst;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLineItem() {
		return lineItem;
	}

	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public DataIngestionMappingModel(String mapId, String analyst, String companyName, String documentType, String year,
			String lineItem, String quarter) {
		super();
		this.mapId = mapId;
		this.analyst = analyst;
		this.companyName = companyName;
		this.documentType = documentType;
		this.year = year;
		this.lineItem = lineItem;
		this.quarter = quarter;
	}

	public DataIngestionMappingModel() {
		super();
	}
	

}