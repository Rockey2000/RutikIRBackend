package com.Anemoi.InvestorRelation.AnalystDetails;

public class AnalystDetailsQueryConstant {
	
	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
	public static final String ANALYSTID ="analystId";
	
	public static final String ANALYSTNAME="analystName";
	
	public static final String POCNAME="pocName";
	
	public static final String POCEMAILID="pocEmailId";
	
	public static final String CREATEDON="createdOn";
	
	public static final String INSERT_INTO_ANALYSTDETAILS="INSERT INTO #$DataBaseName#$.dbo.analystDetails values(?,?,?,?,?)";
	
	public static final String SELECT_ANALYSTDETAILS_BY_ID="SELECT * FROM #$DataBaseName#$.dbo.analystDetails where analystId=?";
	
	public static final String SELECT_ANALYSTDETAILS="SELECT * FROM #$DataBaseName#$.dbo.analystDetails";
	
  public static final String DELETE_ANALYSTDETAILS_BY_ID="DELETE #$DataBaseName#$.dbo.analystDetails where analystId=?";
	
	public static final String UPDATE_ANALYSTDETAILS="UPDATE #$DataBaseName#$.dbo.analystDetails SET analystName=?,pocName=?,pocEmailId=? where analystId=?";
}
