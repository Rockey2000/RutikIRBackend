package com.Anemoi.InvestorRelation.DataIngestion;

public class DataIngestionQueryConstant {

	
public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
    public static final String INGESTIONID = "fileId";
	
	public static final String CLIENT = "client";
	
	public static final String DOCUMENTTYPE="documentType";
	
	public static final String ANALYST="analystName";
	
	public static final String REPORTFROM="reportFrom";
	
	public static final String REPORTTO="reportTo";
	
	public static final String FILENAME="fileName";
	
	public static final String FILETYPE ="fileType";
	
	
	 public static final String INSERT_INTO_DATA_INGESTION="INSERT INTO #$DataBaseName#$.dbo.dataIngestion values(?,?,?,?,?,?,?,?,?)";
	 
	 public static final String SELECT_DATAINGESTION_DETAILS="SELECT * FROM #$DataBaseName#$.dbo.dataIngestion";
	 
	 public static final String DELETE_DATAINGESTION_BYFILEID="DELETE #$DataBaseName#$.dbo.dataIngestion where fileId=?";
	 
	 public static final String SELECT_DATAINGESTION_BYFILEID="SELECT * FROM #$DataBaseName#$.dbo.dataIngestion where fileId=?";
	
	 
	 
	 // DataIngestion Table queries
	 
	 public static final String INSERT_INTO_DATAINGESTION_TABLE_DATA="INSERT INTO #$DataBaseName#$.dbo.dataIngestionaTabelData values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SELECT_TABLEID_BYFILEID="SELECT DISTINCT tableId,tableName from #$DataBaseName#$.dbo.dataIngestionaTabelData where fileId=?";
	
	public static final String SELECT_DATAINGESTION_TABLEDETAILS="SELECT * FROM #$DataBaseName#$.dbo.dataIngestionaTabelData  where tableId=?";

    public static final String UPDATE_DATAINGESTION_TABLEDETAILS="UPDATE #$DataBaseName#$.dbo.dataIngestionaTabelData  SET col1=?,col2=?,col3=?,col4=?,col5=?,col6=?,col7=?,col8=?,col9=?,col10=?,col11=?,col12=?,col13=?,col14=?,col15=?,col16=?,col17=?,col18=?,col19=?,col20=?,col21=?,col22=?,col23=?,col24=?,col25=? WHERE fieldId=?";

   
    //  DataIngestion Mapping table queries
   
    public static final String INSERT_INTO_DATAINGESTION_MAPPINGTABLE="INSERT INTO #$DataBaseName#$.dbo.dataIngestionMappingtable values(?,?,?,?,?,?,?,?,?,?)";
    
   public static final String SELECT_DATAINGESTION_MAPPING_TABLE="SELECT * FROM #$DataBaseName#$.dbo.dataIngestionMappingtable ";

   public static final String SELECT_DATAINGESTION_MAPPINGTABLE_BYMAPID="SELECT * FROM #$DataBaseName#$.dbo.dataIngestionMappingtable WHERE mapId=?";
}
