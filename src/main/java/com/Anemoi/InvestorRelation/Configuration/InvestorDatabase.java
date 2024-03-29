package com.Anemoi.InvestorRelation.Configuration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvestorDatabase extends InvestorDatabaseTables {

	public static String DB_CREATE = "CREATE DATABASE ";

	private static final Logger logger = LoggerFactory.getLogger(InvestorDatabase.class);

	private static boolean isDBExist(String databasename) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		ResultSet resultset = null;
		try {
			System.out.println("welcome " + databasename);
			connection = InvestorDatabaseUtill.getForDatabaseConnection();
			resultset = connection.getMetaData().getCatalogs();
			while (resultset.next()) {
				String currentDBName = resultset.getString(1);
				if (currentDBName.contentEquals(databasename)) {
					return true;
				}
			}
		} finally {
			InvestorDatabaseUtill.close(resultset, null, connection);
		}
		return false;
	}

	private static boolean isTableExist(String tableName) throws SQLException {
		Connection connection = null;
		ResultSet resultset = null;
		try {
			connection = InvestorDatabaseUtill.getConnection();

			resultset = connection.getMetaData().getTables(null, null, tableName, new String[] { "table" });
			while (resultset.next()) {
				String currentTableName = resultset.getString(3);
				if (currentTableName.contentEquals(tableName)) {
					logger.info("table are exists");
					return true;
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public static void createDataBases(String dataBaseName) {
		Connection connection = null;
		Statement statement = null;

		try {
			boolean isDbExist = isDBExist(dataBaseName);
			if (!isDbExist) {
				logger.info(dataBaseName + " database Not exits creating database ......");
				connection = InvestorDatabaseUtill.getForDatabaseConnection();

				statement = connection.createStatement();
				statement.executeUpdate(DB_CREATE + dataBaseName);
				logger.info("checking table are existing");

				  createTable(dataBaseName);
			
				logger.info("** create[ " + dataBaseName + "] database successfully... **");
			} else {
				logger.info("** create[ " + dataBaseName + "] database already exits... **");
				
				  createTable(dataBaseName);
			


			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			InvestorDatabaseUtill.close(statement, connection);
		}
	}



	private static void createTable(String dataBaseName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;

		connection = InvestorDatabaseUtill.getConnection();

		statement = connection.createStatement();
		 DatabaseMetaData metaData = connection.getMetaData();
         List<String> tables = Arrays.asList("rolemodel", "userTable", "balanceSheetform","incomestatement","cashflow","shareholderdataform", "shareholdercontact", "shareholdermeeting","financialRatio","analystLineItem"
        		, "clientLineItem","analystDetails", "analystContactDetails","nonprocessFileTable", "dataIngestion","forecast","tableList", "dataIngestionaTabelMetaData","dataIngestionMappingtable", "keywordlist", "reportTableHeader","meetingshedulartable","clientcodedetails","clientDetails","whitelablingtable");
         for (String tableName : tables) {
             ResultSet rs = metaData.getTables(dataBaseName, null, tableName, null);
             if (rs.next()) {
                 System.out.println("Table " + tableName + " already exists, skipping creation.");
             } else {
                 System.out.println("Creating table " + tableName + "...");
                 String sql = "";
                 switch (tableName) {
                 
                     case "rolemodel":
                    	 createRoleModelTable(statement,dataBaseName);
                    	 break;
                    	 
                     case "userTable":
                    	 createUserTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "balanceSheetform":
                    	 createBalanceSheetTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "incomestatement":
                    	 createincomestatementTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "cashflow":
                    	 createcashflowTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "shareholderdataform":
                    	 createshareholderdataformTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "shareholdercontact":
                    	 createshareholdercontactTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "shareholdermeeting":
                    	 createshareholdermeetingTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "financialRatio":
                    	 createfinancialRatioTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "analystLineItem":
                    	 createanalystLineItem(statement, dataBaseName);
                    	 break;
                    	 
                     case "clientLineItem":
                    	 createclientLineItem(statement, dataBaseName);
                    	 break;
                    	 
                     case "analystDetails":
                    	 createanalystDetails(statement, dataBaseName);
                    	 break;
                    	 
                     case "analystContactDetails":
                    	 createAnalystContactDetails(statement, dataBaseName);
                    	 break;
                    	 
                     case "nonprocessFileTable":
                    	 createNonprocessFilesTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "dataIngestion":
                    	 createDataIngestion(statement, dataBaseName);
                    	 break;
                    	 
                     case "forecast":
                    	 createForcastTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "tableList":
                    	 createTableList(statement, dataBaseName);
                    	 break;
                    	 
                     case "dataIngestionaTabelMetaData":
                    	 createDataIngestionTableMetaData(statement, dataBaseName);
                    	 break;
                    	 
                    	 
                     case "dataIngestionMappingtable":
                    	 createDataIngestionMappingTable(statement, dataBaseName);
                    	 break;
                    	 
                     case "keywordlist":
                    	 createKeywordList(statement, dataBaseName);
                    	 break;
                    	 
                     case "reportTableHeader":
                    	 createReportTableHeader(statement, dataBaseName);
                    	 break;
                    	 
                     case "meetingshedulartable":
                    	 createnewMeetingtable(statement, dataBaseName);
                    	 break;
                    	 
                     case "clientcodedetails":
                    	 createclientCodeDetails(statement, dataBaseName);
                    	 break;
                    	 
                     case "clientDetails":
                    	 createClientDetails(statement, dataBaseName);
                    	 break;
                    	 
                     case "whitelablingtable":
                    	 createWhiteLablingTable(statement,dataBaseName);
                    	 break;
                   
                    	 
                 }
                    	
                         System.out.println("Table " + tableName + " created successfully.");
             }
         }
	}




	private static void createAnalystContactDetails(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_ANALYST_CONTACTDETAILS.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("white lableling table  create successfully");

	}
	private static void createWhiteLablingTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_WHITELABLING.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("white lableling table  create successfully");

	}

	private static void createNonprocessFilesTable(Statement statement, String dataBaseName) throws SQLException {

		statement.executeUpdate(CREATE_NONPROCESS_FILETABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("non process files  table  create successfully");

	}

	private static void createclientCodeDetails(Statement statement, String dataBaseName) throws SQLException {

		statement.executeUpdate(CREATE_CLIENCODEDETAILS.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Static client project code and task code table  create successfully");

	}

	private static void createClientDetails(Statement statement, String dataBaseName) throws SQLException {

		statement.executeUpdate(CREATE_CLIENTDETAILS.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("client details table create successfully");
	}

	private static void createnewMeetingtable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_MEETINGSHEDULAR.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("new meeting table create successfully");

	}

	private static void createReportTableHeader(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_REPORTTABLEHEADER_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Report table header table create successfully");

	}

	private static void createclientLineItem(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_CLIENT_LINE_ITEM.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("client line item table create successfully");

	}

	private static void createForcastTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_FORCAST_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("forcast table create successfully");

	}

	private static void createKeywordList(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub

		statement.executeUpdate(CREATE_KEYWORDLIST.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("keyword table create successfully");

	}

	private static void createTableList(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_TABLE_LIST.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("file table list create successfully");

	}

	private static void createDataIngestionMappingTable(Statement statement, String dataBaseName) throws SQLException {
		statement.executeUpdate(CREATE_DATAINGESTION_MAPPING_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("data ingestion mapping table create successfully");

	}

	private static void createDataIngestionTableMetaData(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_DATAINGESTION_TABLEMETADATA.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("data ingestion table data create successfully");

	}

	private static void createDataIngestion(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_DATA_INGESTION.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("data ingestion  create successfully");

	}

	private static void createanalystDetails(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_ANALYST_DETAILS.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("analyst details table create successfully");
	}

	private static void createanalystLineItem(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_ANALYST_LINE_ITEM.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Analyst LineItem table create successfully");

	}

	private static void createfinancialRatioTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("welcome finacial ration table");
		statement.executeUpdate(CREATE_FINANCIAL_RATIO_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("shareHolder financial ratio table create successfully");
	}

	private static void createshareholdermeetingTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_SHAREHOLDER_MEETING_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("shareHolder meeting table create successfully");
	}

	private static void createshareholdercontactTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_SHAREHOLDERCONTACT_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("shareHolder contact table create successfully");
	}

	private static void createshareholderdataformTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_SHAREHOLDERDATAFORM_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));

		logger.info("shareHolder data table create successfully");
	}

	private static void createcashflowTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_CASHFLOW_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("cashFlow table create successfully");
	}

	private static void createincomestatementTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_INCOMESTATEMENT_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("income statement table create successfully");
	}

	private static void createBalanceSheetTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_BALANCESHEETFORM_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("balance sheet table create successfully");
	}

	private static void createUserTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(statement.toString());

//		String query = "CREATE TABLE InvestorDB.dbo.user1(id uniqueidentifier NOT NULL,firstName varchar(255) NOT NULL,lastName varchar(255) NOT NULL,email varchar(255) NOT NULL,mobileNumber varchar(255) NOT NULL,domain varchar(255) NOT NULL,assignedName varchar(255) NOT NULL,role varchar(255) NOT NULL,status varchar(255) NOT NULL,createdOn bigint NOT NULL,CONSTRAINT PK_id PRIMARY KEY CLUSTERED(id))";
//		statement.executeUpdate(query);

		statement.executeUpdate(CREATE_USER_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		// statement.ex(CREATE_USER_TABLE.replace(DATA_BASE_PLACE_HOLDER,
		// dataBaseName));
		logger.info("user table create successfully");
	}

	private static void createRoleModelTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("welcome tables");
		statement.executeUpdate(CREATE_ROLEMODEL_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("roleModel table create successfully");
	}
}