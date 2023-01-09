package com.Anemoi.InvestorRelation.DataIngestion;

public class DataIngestionMappingModel {
	
	private String mapId;
	
	private long fieldId;
	
	private String analyst;
	
	private String companyName;
	
	private String documentType;

	private String year;
	
	private String lineItemName;
	
	private String quarter;
	
	private String type;
	
	private String value;

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
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

	public String getLineItemName() {
		return lineItemName;
	}

	public void setLineItemName(String lineItemName) {
		this.lineItemName = lineItemName;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DataIngestionMappingModel(String mapId, long fieldId, String analyst, String companyName,
			String documentType, String year, String lineItemName, String quarter, String type, String value) {
		super();
		this.mapId = mapId;
		this.fieldId = fieldId;
		this.analyst = analyst;
		this.companyName = companyName;
		this.documentType = documentType;
		this.year = year;
		this.lineItemName = lineItemName;
		this.quarter = quarter;
		this.type = type;
		this.value = value;
	}

	public DataIngestionMappingModel() {
		super();
	}

	@Override
	public String toString() {
		return "DataIngestionMappingModel [mapId=" + mapId + ", fieldId=" + fieldId + ", analyst=" + analyst
				+ ", companyName=" + companyName + ", documentType=" + documentType + ", year=" + year
				+ ", lineItemName=" + lineItemName + ", quarter=" + quarter + ", type=" + type + ", value=" + value
				+ "]";
	}
	
	
	}