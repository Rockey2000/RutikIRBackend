package com.Anemoi.InvestorRelation.BalanceSheet;

public class BalanceSheetEntity {
	
private String balanceid;
	
	private String lineItem;
	
	private String alternativeName;
	
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BalanceSheetEntity(String balanceid, String lineItem, String alternativeName, String type) {
		super();
		this.balanceid = balanceid;
		this.lineItem = lineItem;
		this.alternativeName = alternativeName;
		this.type = type;
	}

	public BalanceSheetEntity() {
		super();
	}

	@Override
	public String toString() {
		return "BalanceSheetEntity [balanceid=" + balanceid + ", lineItem=" + lineItem + ", alternativeName="
				+ alternativeName + ", type=" + type + "]";
	}
	
	
}