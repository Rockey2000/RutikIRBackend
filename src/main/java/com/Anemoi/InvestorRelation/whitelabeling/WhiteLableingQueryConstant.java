package com.Anemoi.InvestorRelation.whitelabeling;

public class WhiteLableingQueryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";


	
	public static final String INSERT_INTO_WHITELABLEING_TABLE = "INSERT INTO #$DataBaseName#$.dbo.whitelablingtable values(?,?,?,?,?,?)";

	public static final String SELECT_WHITELABLEING_LIST="SELECT * FROM #$DataBaseName#$.dbo.whitelablingtable";
}
