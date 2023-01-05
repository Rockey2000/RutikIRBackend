package com.Anemoi.InvestorRelation.Configuration;

public class InvestorDatabaseTables {
	
public static final String DATA_BASE_PLACE_HOLDER="#$dataBaseName#$";
	
	public static final String CREATE_ROLEMODEL_TABLE="CREATE TABLE #$dataBaseName#$.dbo.rolemodel(id uniqueidentifier NOT NULL,roleName varchar(255) unique,description varchar(255) NOT NULL,status varchar(255) NOT NULL,dashboardAccess varchar(550) NOT NULL,lastEdit bigint NOT NUll,createdOn bigint NOT NULL,CONSTRAINT PK_roleName PRIMARY KEY CLUSTERED(roleName))";

	public static final String CREATE_USER_TABLE="CREATE TABLE #$dataBaseName#$.dbo.user1(userid uniqueidentifier NOT NULL,firstName varchar(255) NOT NULL,lastName varchar(255) NOT NULL,email varchar(255) unique,mobileNumber varchar(255) unique,domain varchar(255) NOT NULL,assignedName varchar(255) NOT NULL,roleName varchar(255) NOT NULL foreign key references #$dataBaseName#$.dbo.rolemodel(roleName) on update cascade ,userStatus varchar(20),password varchar(200) ,editedOn bigint NOT NULL,createdOn bigint NOT NULL,CONSTRAINT userid PRIMARY KEY CLUSTERED(userid))";

    public static final String CREATE_BALANCESHEETFORM_TABLE="CREATE TABLE #$dataBaseName#$.dbo.balanceSheetform(balanceid uniqueidentifier NOT NULL,lineItem varchar(255) unique,alternativeName varchar(255) unique,type varchar(255) NOT NULL,CONSTRAINT balanceid PRIMARY KEY CLUSTERED(balanceid))";

    public static final String CREATE_INCOMESTATEMENT_TABLE="CREATE TABLE #$dataBaseName#$.dbo.incomestatement(incomeid uniqueidentifier NOT NULL,lineItem varchar(255) unique,alternativeName varchar(255) unique,type varchar(255) NOT NULL,CONSTRAINT incomeid PRIMARY KEY CLUSTERED(incomeid))";
    
    public static final String CREATE_CASHFLOW_TABLE="CREATE TABLE #$dataBaseName#$.dbo.cashflow(cashId uniqueidentifier NOT NULL,lineItem varchar(255) unique,alternativeName varchar(255) unique,type varchar(255) NOT NULL,CONSTRAINT cashid PRIMARY KEY CLUSTERED(cashid))";
   
    public static final String CREATE_SHAREHOLDERDATAFORM_TABLE="CREATE TABLE #$dataBaseName#$.dbo.shareholderdataform(shareId uniqueidentifier NOT NULL,clientId varchar(255) NOT NULL,portfolioId varchar(255) NOT NULL,folio varchar(255) NOT NULL,shareholderName varchar(255) NOT NULL,holdingValue varchar(255) NOT NULL,holdingPercentage varchar(255) NOT NULL,minorCode varchar(255) NOT NULL,date varchar(255) NOT NULL,CONSTRAINT shareid PRIMARY KEY CLUSTERED(shareid))";
   
    public static final String CREATE_SHAREHOLDERCONTACT_TABLE="CREATE TABLE #$dataBaseName#$.dbo.shareholdercontact(contactid uniqueidentifier NOT NULL,name varchar(255) NOT NULL,poc varchar(255) NOT NULL,email varchar(255) unique,minorcode varchar(255) NOT NULL,address varchar(255) NOT NULL,contact varchar(255) unique,CONSTRAINT contactid PRIMARY KEY CLUSTERED(contactid))";

    public static final String CREATE_SHAREHOLDER_MEETING_TABLE="CREATE TABLE #$dataBaseName#$.dbo.shareholdermeeting(holderid uniqueidentifier NOT NULL,date varchar(255) NOT NULL,startTime varchar(255) NOT NULL,endTime varchar(255) NOT NULL,organisation varchar(255) NOT NULL,stakeholderType varchar(255) NOT NULL,meetingType varchar(255) NOT NULL,subject varchar(255) NOT NULL,broker varchar(255) NOT NULL,location varchar(255) NOT NULL,status varchar(255) NOT NULL,comments varchar(255) NOT NULL,participants varchar(255) NOT NULL,feedback varchar(255) NOT NULL,CONSTRAINT holderid PRIMARY KEY CLUSTERED(holderid))";

    public static final String CREATE_FINANCIAL_RATIO_TABLE="CREATE TABLE #$dataBaseName#$.dbo.financialRatio(financialid uniqueidentifier NOT NULL,lineItem varchar(255) unique,formulaType varchar(255) NOT NULL,formula varchar(255) unique NOT NULL,CONSTRAINT financialid PRIMARY KEY CLUSTERED(financialid))";

    public static final String CREATE_ANALYST_LINE_ITEM="CREATE TABLE #$dataBaseName#$.dbo.analystLineItem(analystLineId uniqueidentifier NOT NULL,analystName varchar(255) NOT NULL,lineItemName varchar(255),analystLineItemName varchar(255) unique NOT NULL,analystTableHeaderName varchar(255) NOT NULL,masterTableSource varchar(255) NOT NULL,createdOn bigint NOT NULL,CONSTRAINT PK_analystlineId PRIMARY KEY CLUSTERED(analystlineId))";

    public static final String CREATE_ANALYST_DETAILS="CREATE TABLE  #$dataBaseName#$.dbo.analystDetails(analystId varchar(100) unique NOT NULL,analystName varchar(255) unique NOT NULL,pocName varchar(255) NOT NULL,pocEmailId varchar(255) unique NOT NULL,createdOn bigint NOT NULL,CONSTRAINT PK_analystId PRIMARY KEY CLUSTERED(analystId))";

    public static final String CREATE_DATA_INGESTION="CREATE TABLE #$dataBaseName#$.dbo.dataIngestion(fileId bigint,client varchar(255) NOT NULL,documentType varchar(255) NOT NULL,analystName varchar(255),reportFrom date NOT NULL,reportTo date NOT NULL,fileName varchar(255) NOT NULL,fileType varchar(255) NOT NULL,fileData VARBINARY(MAX),CONSTRAINT PK_fileId PRIMARY KEY CLUSTERED(fileId))";

    public static final String CREATE_DATAINGESTION_TABLEDATA="CREATE TABLE #$dataBaseName#$.dbo.dataIngestionaTabelData(fieldId bigint IDENTITY,fileId bigint NOT NULL foreign key references #$dataBaseName#$.dbo.dataIngestion(fileId),tableId varchar(255) NOT NULL,tableName varchar(255) NOT NULL,col1 varchar(MAX) ,col2 varchar(MAX),col3 varchar(MAX),col4 varchar(MAX),col5 varchar(MAX),col6 varchar(MAX),col7 varchar(MAX),col8 varchar(MAX),col9 varchar(MAX),col10 varchar(MAX),col11 varchar(MAX),col12 varchar(MAX),col13 varchar(MAX),col14 varchar(MAX),col15 varchar(MAX),col16 varchar(MAX),col17 varchar(MAX),col18 varchar(MAX),col19 varchar(MAX),col20 varchar(MAX),col21 varchar(MAX),col22 varchar(MAX),col23 varchar(MAX),col24 varchar(MAX),col25 varchar(MAX),CONSTRAINT PK_fieldId PRIMARY KEY CLUSTERED(fieldId))";

    public static final String CREATE_DATAINGESTION_MAPPING_TABLE="CREATE TABLE #$dataBaseName#$.dbo.dataIngestionMappingtable(mapId uniqueidentifier NOT NULL,analyst varchar(255) NOT NULL,companyName varchar(255) NOT NULL,documentType varchar(255) NOT NULL,year varchar(255) NOT NULL,lineItem varchar(255) NOT NULL,quarter varchar(255) NOT NULL,CONSTRAINT mapId PRIMARY KEY CLUSTERED(mapId))";

}
