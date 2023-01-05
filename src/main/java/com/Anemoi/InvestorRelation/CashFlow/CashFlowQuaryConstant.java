package com.Anemoi.InvestorRelation.CashFlow;

public class CashFlowQuaryConstant {
		
	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
		
	    public static final String CASHID = "cashId";
		
		public static final String LINE_ITEM = "lineItem";
		
		public static final String ALTERNATIVE_NAME= "alternativeName";
		
		public static final String TYPE = "type";
		 
		  public static final String  INSERT_INTO_CASHFLOW = "INSERT INTO #$DataBaseName#$.dbo.cashflow values(?,?,?,?)";
			
			public static final String SELECT__CASHFLOW_BY_ID = "SELECT * FROM #$DataBaseName#$.dbo.cashflow where cashId=?";
			
			public static final String SELECT_CASHFLOW = "SELECT *FROM #$DataBaseName#$.dbo.cashflow";
			
			public static final String UPDATE_CASHFLOW ="UPDATE #$DataBaseName#$.dbo.cashflow SET lineItem=?, alternativeName=?, type=? WHERE cashId=?";
		 
			public static final String DELETE_CASHFLOW_BY_ID = "DELETE #$DataBaseName#$.dbo.cashflow WHERE cashId=?";
		

	}
