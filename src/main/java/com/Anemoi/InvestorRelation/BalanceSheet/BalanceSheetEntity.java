package com.Anemoi.InvestorRelation.BalanceSheet;

public class BalanceSheetEntity {

	private String balanceid;

	private String lineItem;

	private String alternativeName;

	public String getBalanceid() {
		return balanceid;
	}

	public void setBalanceid(String balanceid) {
		this.balanceid = balanceid;
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

	public BalanceSheetEntity(String balanceid, String lineItem, String alternativeName) {
		super();
		this.balanceid = balanceid;
		this.lineItem = lineItem;
		this.alternativeName = alternativeName;
	}

	public BalanceSheetEntity() {
		super();
	}

}