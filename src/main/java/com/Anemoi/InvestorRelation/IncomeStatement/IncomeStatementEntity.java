package com.Anemoi.InvestorRelation.IncomeStatement;

public class IncomeStatementEntity {
	
	private String incomeid;
	
	private String lineItem;
	
	private String alternativeName;
	
	private String type;

	public String getIncomeid() {
		return incomeid;
	}

	public void setIncomeid(String incomeid) {
		this.incomeid = incomeid;
	}

	public String getLineItem() {
		return lineItem;
	}

	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}

	public String getAlternativeName() {
		return alternativeName;
	}

	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "IncomeStatementEntity [incomeid=" + incomeid + ", lineItem=" + lineItem + ", alternativeName="
				+ alternativeName + ", type=" + type + "]";
	}

	public IncomeStatementEntity(String incomeid, String lineItem, String alternativeName, String type) {
		super();
		this.incomeid = incomeid;
		this.lineItem = lineItem;
		this.alternativeName = alternativeName;
		this.type = type;
	}

	public IncomeStatementEntity() {
		super();
	}
	
	

}
