package com.Anemoi.InvestorRelation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class InvestorDatabase extends InvestorDatabaseTables
 {

	
	public static String DB_CREATE = "CREATE DATABASE ";

	private static final Logger logger = LoggerFactory.getLogger(InvestorDatabase.class);

	private static boolean isDBExist(String databasename) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		ResultSet resultset = null;
		try {
			System.out.println("welcome " + databasename);
			connection = InvestorDatabaseUtill.getConnection();
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
				connection = InvestorDatabaseUtill.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(DB_CREATE + dataBaseName);
				logger.info("checking table are existing");
				createTables(statement, dataBaseName);
				System.out.println("tables created");
				logger.info("** create[ " + dataBaseName + "] database successfully... **");
			} else {
				logger.info("** create[ " + dataBaseName + "] database already exits... **");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			InvestorDatabaseUtill.close(statement, connection);
		}
	}

	private static void createTables(Statement statement, String dataBaseName) throws SQLException {
  try {
		createRoleModelTable(statement, dataBaseName);
		createUserTable(statement, dataBaseName);
		createBalanceSheetTable(statement, dataBaseName);
		createincomestatementTable(statement, dataBaseName);
		createcashflowTable(statement, dataBaseName);
		createshareholderdataformTable(statement, dataBaseName);
		createshareholdercontactTable(statement, dataBaseName);
		createshareholdermeetingTable(statement, dataBaseName);
		createfinancialRatioTable(statement, dataBaseName);
		createanalystLineItem(statement,dataBaseName);
		createanalystDetails(statement,dataBaseName);
		createDataIngestion(statement,dataBaseName);
		createDataIngestionTableData(statement,dataBaseName);
		createDataIngestionMappingTable(statement,dataBaseName);
  }
  catch (Exception e) {
	// TODO: handle exception
	  e.printStackTrace();
}
	}

	private static void createDataIngestionMappingTable(Statement statement, String dataBaseName) throws SQLException {
		statement.executeUpdate(CREATE_DATAINGESTION_MAPPING_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("data ingestion mapping table create successfully");
		
	}

	private static void createDataIngestionTableData(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		statement.executeUpdate(CREATE_DATAINGESTION_TABLEDATA.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
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
		statement.executeUpdate(CREATE_ANALYST_LINE_ITEM.replace(DATA_BASE_PLACE_HOLDER,dataBaseName));
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
		//statement.ex(CREATE_USER_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("user table create successfully");
	}

	private static void createRoleModelTable(Statement statement, String dataBaseName) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("welcome tables");
		statement.executeUpdate(CREATE_ROLEMODEL_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("roleModel table create successfully");  
}
}