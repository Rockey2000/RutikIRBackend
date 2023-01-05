package com.Anemoi.InvestorRelation.AnalystLineItem;

public class AnalystLineItemQueryConstant {
	
public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
    public static final String ANALYSTLINEID = "analystLineId";
	
	public static final String ANALYSTNAME = "analystName";
	
	public static final String LINEITEMNAME="lineItemName";
	
	public static final String ANALYSTLINEITEMNAME="analystLineItemName";
	
	public static final String ANALYSTTABLEHEADERNAME="analystTableHeaderName";
	
	public static final String MASTERTABLESOURCE="masterTableSource";
	
   public static final String CREATEDON="createdOn";
   
   
   public static final String INSERT_INTO_ANALYSTLINE_ITEM="INSERT INTO #$DataBaseName#$.dbo.analystLineItem values(?,?,?,?,?,?,?)";
  
   public static final String SELECT_ANALYSTLINEITEM_BY_ID="SELECT * FROM #$DataBaseName#$.dbo.analystLineItem where analystLineId=?";
   
   public static final String SELECT_ANALYSTLINEITEM="SELECT * FROM #$DataBaseName#$.dbo.analystLineItem";
   
   public static final String SELECT_ANALYSTLINEITEM_BY_ANALYSTNAME="SELECT * FROM #$DataBaseName#$.dbo.analystLineItem where analystName=? and masterTableSource=?";
   
   public static final String UPDATE_ANALYSTLINEITEM="UPDATE #$DataBaseName#$.dbo.analystLineItem SET lineItemName=? where analystName=? and analystLineItemName=?";
}
