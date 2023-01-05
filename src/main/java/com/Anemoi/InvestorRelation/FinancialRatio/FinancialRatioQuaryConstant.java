package com.Anemoi.InvestorRelation.FinancialRatio;

public class FinancialRatioQuaryConstant {
	
	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
    public static final String FINANCIALID = "financialid";
	
	public static final String LINEITEM = "lineItem";
	
	public static final String FORMULATYPE= "formulaType";
	
	public static final String FORMULA = "formula";
	 
	  public static final String  INSERT_INTO_FINANCIALRATIO = "INSERT INTO #$DataBaseName#$.dbo.financialRatio values(?,?,?,?)";
		
		public static final String SELECT__FINANCIALRATIO_BY_ID = "SELECT * FROM #$DataBaseName#$.dbo.financialRatio where financialid=?";
		
		public static final String SELECT_FINANCIALRATIO = "SELECT *FROM #$DataBaseName#$.dbo.financialRatio";
		
		public static final String UPDATE_FINANCIALRATIO ="UPDATE #$DataBaseName#$.dbo.financialRatio SET lineItem=?, formulaType=?, formula=? WHERE financialid=?";
	 
		public static final String DELETE_FINANCIALRATIO_BY_ID = "DELETE #$DataBaseName#$.dbo.financialRatio WHERE financialid=?";
	

}
