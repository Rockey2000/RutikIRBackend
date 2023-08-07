package com.Anemoi.InvestorRelation.IncomeStatement;

public class IncomeStatementEntity {

	private String incomeid;

	private String lineItem;

	private String alternativeName;

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

	public IncomeStatementEntity(String incomeid, String lineItem, String alternativeName) {
		super();
		this.incomeid = incomeid;
		this.lineItem = lineItem;
		this.alternativeName = alternativeName;
	}

	public IncomeStatementEntity() {
		super();
	}

}