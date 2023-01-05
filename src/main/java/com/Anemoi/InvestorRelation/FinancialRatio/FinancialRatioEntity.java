package com.Anemoi.InvestorRelation.FinancialRatio;

public class FinancialRatioEntity {
	
	private String financialid;
	
	private String lineItem;
	
	private String formulaType;
	
	private String formula;

	public String getFinancialid() {
		return financialid;
	}

	public void setFinancialid(String financialid) {
		this.financialid = financialid;
	}

	public String getLineItem() {
		return lineItem;
	}

	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}

	public String getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public FinancialRatioEntity(String financialid, String lineItem, String formulaType, String formula) {
		super();
		this.financialid = financialid;
		this.lineItem = lineItem;
		this.formulaType = formulaType;
		this.formula = formula;
	}

	public FinancialRatioEntity() {
		super();
	}
	
	
	
	

}
