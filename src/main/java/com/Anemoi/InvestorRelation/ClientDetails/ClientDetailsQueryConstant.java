package com.Anemoi.InvestorRelation.ClientDetails;

public class ClientDetailsQueryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	public static final String INSERT_INTO_CLIENTDETAILS = "INSERT INTO #$DataBaseName#$.dbo.clientDetails values(?,?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_CLIENTDETAILS = "SELECT * FROM #$DataBaseName#$.dbo.clientDetails";

	public static final String SELECT_CLIENTDETAILS_BYCLIENTID = "SELECT * FROM #$DataBaseName#$.dbo.clientDetails where clientId=?";

	public static final String SELECT_CLIENTDETAILS_BYCLIENTNAME = "SELECT * FROM #$DataBaseName#$.dbo.clientDetails where clientName=?";

	public static final String INSERT_CLIENTDETAILS_BYPROJECTCODE = "INSERT INTO #$DataBaseName#$.dbo.clientcodedetails values(?,?,?,?)";

	public static final String SELECT_CLIENTDETAILS_BYPROJECTCODE = "SELECT * FROM #$DataBaseName#$.dbo.clientcodedetails WHERE projectCode=? and taskCode=?";

	public static final String UPDATE_CLIENTDETAILS = "UPDATE #$DataBaseName#$.dbo.clientDetails SET emailDomain=?,emailId=?,industry=?,suggestedPeers=?,assignAA=? where clientId=?";

	public static final String DELETE_CLIENTDETAILS = "DELETE #$DataBaseName#$.dbo.clientDetails where clientId=?";

	public static final String SELECT_CLIENTNAME= "SELECT  clientName FROM  #$DataBaseName#$.dbo.clientDetails";

	public static final String SELECT_INDUSRY_FORUPDATE= "SELECT  industry FROM  #$DataBaseName#$.dbo.clientDetails where clientName=?  AND clientId <> ?";

}
