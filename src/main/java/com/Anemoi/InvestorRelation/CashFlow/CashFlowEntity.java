package com.Anemoi.InvestorRelation.CashFlow;

public class CashFlowEntity {

	private String cashId;

	private String lineItem;

	private String alternativeName;

	private String type;

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
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

	public CashFlowEntity(String cashId, String lineItem, String alternativeName, String type) {
		super();
		this.cashId = cashId;
		this.lineItem = lineItem;
		this.alternativeName = alternativeName;
		this.type = type;
	}

	public CashFlowEntity() {
		super();
	}

}
