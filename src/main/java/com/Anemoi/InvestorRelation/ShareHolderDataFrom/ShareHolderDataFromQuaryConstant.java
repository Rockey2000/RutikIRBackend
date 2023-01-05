package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

public class ShareHolderDataFromQuaryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
    public static final String SHAREID = "shareid";
	
	public static final String CLIENTID = "clientid";
	
	public static final String PORTFOLIOID= "portfolioid";
	
	public static final String FOLIO = "folio";
	
	public static final String SHAREHOLDERNAME = "shareholdername";
	
	public static final String HOLDINGVALUE  = "holdingvalue";
	
	public static final String HOLDINGPERCENTAGE  = "holdingpercentage";
	
	public static final String MINORCODE = "minorcode";
	
	public static final String DATE = "date";
	 
	  public static final String  INSERT_INTO_SHAREHOLDERDATAFORM = "INSERT INTO #$DataBaseName#$.dbo.shareholderdataform values(?,?,?,?,?,?,?,?,?)";
		
		public static final String SELECT__SHAREHOLDERDATAFORM_BY_ID = "SELECT * FROM #$DataBaseName#$.dbo.shareholderdataform where shareid=?";
		
		public static final String SELECT_SHAREHOLDERDATAFORM = "SELECT *FROM #$DataBaseName#$.dbo.shareholderdataform";
		
		public static final String UPDATE_SHAREHOLDERDATAFORM ="UPDATE #$DataBaseName#$.dbo.shareholderdataform SET clientid=?, portfolioid=?, folio=?,shareholdername=?,holdingvalue=?,holdingpercentage=?,minorcode=?,date=? WHERE shareid=?";
	 
		public static final String DELETE_SHAREHOLDERDATAFORM_BY_ID = "DELETE #$DataBaseName#$.dbo.shareholderdataform WHERE shareid=?";
	

	
}
