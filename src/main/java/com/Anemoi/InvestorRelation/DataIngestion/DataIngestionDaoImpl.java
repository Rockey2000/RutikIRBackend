package com.Anemoi.InvestorRelation.DataIngestion;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.CashFlow.CashFlowDaoException;
import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;
import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;
import com.azure.ai.formrecognizer.DocumentAnalysisClient;
import com.azure.ai.formrecognizer.DocumentAnalysisClientBuilder;
import com.azure.ai.formrecognizer.models.AnalyzeResult;
import com.azure.ai.formrecognizer.models.DocumentOperationResult;
import com.azure.ai.formrecognizer.models.DocumentTable;
import com.azure.ai.formrecognizer.models.DocumentTableCell;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.SyncPoller;

import jakarta.inject.Singleton;

@Singleton
public class DataIngestionDaoImpl implements DataIngestionDao {
	
	String tb="table_";
	private static int tableid=1;
  
    private static int fileId=1;
	private static final Logger LOGGER = LoggerFactory.getLogger(DataIngestionDaoImpl.class);
	HSSFWorkbook workbook = new HSSFWorkbook();

	@Override
	public DataIngestionModel saveDataIngestionDetails(DataIngestionModel dataIngestionModel, String dataBaseName) throws DataIngestionDaoException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement psta = null;
		Date date = new Date();
		System.out.println("database name" + dataBaseName);
		try {
			System.out.println("check 1");
			System.out.println("file name" +dataIngestionModel.getFileName());
			System.out.println("check 1");

			connection = InvestorDatabaseUtill.getConnection();
			System.out.println("check 1");

			psta = connection.prepareStatement(DataIngestionQueryConstant.INSERT_INTO_DATA_INGESTION
					.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			
//			String fileId = UUID.randomUUID().toString();
			dataIngestionModel.setFileId(fileId);
			psta.setInt(1, dataIngestionModel.getFileId());
			psta.setString(2, dataIngestionModel.getClient());
			psta.setString(3, dataIngestionModel.getDocumentType());
			psta.setString(4, dataIngestionModel.getAnalystName());
			psta.setDate(5, dataIngestionModel.getReportFrom());
			psta.setDate(6, dataIngestionModel.getReportTo());
			psta.setString(7, dataIngestionModel.getFileName());
			psta.setString(8, dataIngestionModel.getFileType());
			psta.setBytes(9, dataIngestionModel.getFileData());
			psta.executeUpdate();
			fileId++;
			return dataIngestionModel;
		

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new DataIngestionDaoException("unable to create data Ingestion model"+e.getMessage());
		} finally {
			LOGGER.info("closing the connection");
			InvestorDatabaseUtill.close(psta, connection);

		}
	
	}

	

	@Override
	public String saveDataIngestionInDataBase(DataIngestionModel dataIngestion, String dataBaseName) throws SQLException, IOException, ClassNotFoundException,DataIngestionDaoException {
		// TODO Auto-generated method stub
		XSSFRow row;
		String extPattern = "(?<!^)[.]" + (".*");

		final String endpoint = "https://secondformrecognizer001.cognitiveservices.azure.com/";
		final String key = "40e2e4174044476596572ce562612d28";
		DocumentAnalysisClient client = new DocumentAnalysisClientBuilder().credential(new AzureKeyCredential(key))
				.endpoint(endpoint).buildClient();

		//	String modelId = "54a23747-5d44-475d-a3d1-e7b18f047a90";
		String modelId = "prebuilt-document";
		//String FileName = "C:\\Users\\DELL\\Desktop\\IR DOC\\infosys.pdf";
//		String FileName = "C:\\Users\\DELL\\Desktop\\IR DOC\\all documents for OCR\\Splitted Doc\\infosis.pdf";
	//	String FileName = "C:\\Users\\DELL\\Desktop\\IR DOC\\all documents for OCR\\Sample Documents\\Analyst Data\\Infosys\\Emkay\\Q1 results.pdf";
         String fileName=dataIngestion.getFileName();
		File document = new File(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook();

		String replacedFileName = document.getName().replaceAll(extPattern, "");
		System.out.println(replacedFileName + " replaced name");

		byte[] fileContent = dataIngestion.getFileData();

		try (InputStream targetStream = new ByteArrayInputStream(fileContent)) {

			SyncPoller<DocumentOperationResult, AnalyzeResult> analyzeDocumentPoller = client
					.beginAnalyzeDocument(modelId, targetStream, (fileContent.length));

			AnalyzeResult analyzeResult = analyzeDocumentPoller.getFinalResult();
			String content=	analyzeResult.getContent().toString();
			ArrayList<String> list=new ArrayList<>();
			list.add(content);
			//System.out.println(list);
			//for (int p = 0; p <list.size(); p++) {
				
			//String a=list.get(p);
			//System.out.println(a);
			//if(a.contains("Key Financials (Consolidated) Income Statement"))
			//{
			int incomeSheetCount=1;
			int balanceSheetCount=1;
			int cashFlowCount=1;
			int sheetcount=1;
			List<DocumentTable> tables = analyzeResult.getTables();      
			for (int i = 0; i < tables.size(); i++) {

				DocumentTable documentTable = tables.get(i);

				System.out.printf("Table %d has %d rows and %d columns.%n", i, documentTable.getRowCount(),
						documentTable.getColumnCount());

				XSSFSheet spreadsheet = workbook.createSheet("sheet");
				XSSFRow newrow = spreadsheet.createRow(0);

				int count = 0;
				int col = 0;

				for (DocumentTableCell documentTableCell : documentTable.getCells()) {
					int rowIndex = documentTableCell.getRowIndex();

					if (count < rowIndex) {
						count++;
						System.out.println(count);
						newrow = spreadsheet.createRow(count);
						col = 0;
					}
					XSSFCell newcell1 = newrow.createCell(col++);
					String keywordContain = documentTableCell.getContent().toString();
					if (keywordContain.equals(ReadPropertiesFile.readKeywordProperties("101"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("102"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("103"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("104"))) {
						//						incomeStatementCount++;
						workbook.setSheetName(i, ReadPropertiesFile.readKeywordProperties("100"));

					}

					else if (keywordContain.equals(ReadPropertiesFile.readKeywordProperties("201"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("202"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("203"))) {
						//						balanceSheetCount++;
						workbook.setSheetName(i, ReadPropertiesFile.readKeywordProperties("200"));
					}

					else if (keywordContain.equals(ReadPropertiesFile.readKeywordProperties("301"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("302"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("303"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("304"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("305"))
							|| keywordContain.equals(ReadPropertiesFile.readKeywordProperties("306"))) {
						//						cashFlowCount++;
						workbook.setSheetName(i, ReadPropertiesFile.readKeywordProperties("300"));


					}

					newcell1.setCellValue(documentTableCell.getContent().toString());

			
				}
				
				for(int c=0; c<sheetcount; c++) {
					if(workbook.getSheetName(i).equals(ReadPropertiesFile.readKeywordProperties("100"))) {

						workbook.setSheetName(i, ReadPropertiesFile.readKeywordProperties("100")+" "+incomeSheetCount++);

					}
					if(workbook.getSheetName(i).equals(ReadPropertiesFile.readKeywordProperties("200"))) {

						workbook.setSheetName(i, ReadPropertiesFile.readKeywordProperties("200")+" "+balanceSheetCount++);

					}
					if(workbook.getSheetName(i).equals(ReadPropertiesFile.readKeywordProperties("300"))) {

						workbook.setSheetName(i, ReadPropertiesFile.readKeywordProperties("300")+" "+cashFlowCount++);

					}
					if(workbook.getSheetName(i).equals("sheet")) {

						workbook.setSheetName(i,"sheet "+sheetcount++);

					}
			}
				System.out.println("=======================Created a new sheet===========================");
			}
		

			//			FileOutputStream out = new FileOutputStream(
			//					new File("C:\\Users\\DELL\\Desktop\\IR DOC" + replacedFileName + "testeng.xlsx"));
			//			workbook.write(out);
			//			out.close();
			System.out.printf("Successfully created %d sheets", tables.size());	
			//}
			//}
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String connectionUrl = "jdbc:sqlserver://localhost;user=sa;password=Amol@12345";
//			Connection conn = DriverManager.getConnection(connectionUrl);
			Connection con=InvestorDatabaseUtill.getConnection();
			DataFormatter formatter = new DataFormatter();
			//			FileInputStream inputStream = new FileInputStream("C:\\Users\\DELL\\Desktop\\IR DOC" + replacedFileName + "testeng.xlsx");
			//			 workbook = new XSSFWorkbook(inputStream);
		
			
	
			int num=1;

			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				XSSFSheet    sheet =workbook.getSheetAt(i);

				Iterator<Row> rowIterator = sheet.iterator();
				// int id1=1;

				while (rowIterator.hasNext()) {


					Row row1 = rowIterator.next();


					Iterator<Cell> cellIterator = row1.cellIterator();

					// while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					String cellValue = formatter.formatCellValue(cell);
					String value1 = formatter.formatCellValue(row1.getCell(0)); 
					String value2 = formatter.formatCellValue(row1.getCell(1));
					String value3 = formatter.formatCellValue(row1.getCell(2));
					String value4 = formatter.formatCellValue(row1.getCell(3));
					String value5 = formatter.formatCellValue(row1.getCell(4));
					String value6 = formatter.formatCellValue(row1.getCell(5));
					String value7 = formatter.formatCellValue(row1.getCell(6));
					String value8 = formatter.formatCellValue(row1.getCell(7));
					String value9 = formatter.formatCellValue(row1.getCell(8));
					String value10 = formatter.formatCellValue(row1.getCell(9));
					String value11= formatter.formatCellValue(row1.getCell(10));
					String value12= formatter.formatCellValue(row1.getCell(11));
					String value13 = formatter.formatCellValue(row1.getCell(12));
					String value14 = formatter.formatCellValue(row1.getCell(13));
					String value15 = formatter.formatCellValue(row1.getCell(14));
					String value16 = formatter.formatCellValue(row1.getCell(15));
					String value17 = formatter.formatCellValue(row1.getCell(16));
					String value18 = formatter.formatCellValue(row1.getCell(17));
					String value19 = formatter.formatCellValue(row1.getCell(18));
					String value20 = formatter.formatCellValue(row1.getCell(19));
					String value21 = formatter.formatCellValue(row1.getCell(20));
					String value22 = formatter.formatCellValue(row1.getCell(21));
					String value23 = formatter.formatCellValue(row1.getCell(22));
					String value24 = formatter.formatCellValue(row1.getCell(23));
					String value25 = formatter.formatCellValue(row1.getCell(24));
			       
					String  Table_name= workbook.getSheetName(i);
					int fileId=dataIngestion.getFileId();
					//String sql = "INSERT INTO InvestorDB.dbo.IR_Data_Ingection VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement statement = con.prepareStatement(DataIngestionQueryConstant.INSERT_INTO_DATAINGESTION_TABLE_DATA.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));	
					statement.setInt(1, fileId); 
					statement.setString(2, tb+tableid);
					statement.setString(3,Table_name);
					statement.setString(4,value1);
					statement.setString(5, value2);
					statement.setString(6, value3);
					statement.setString(7, value4);
					statement.setString(8, value5);
					statement.setString(9, value6);
					statement.setString(10, value7);
					statement.setString(11, value8);
					statement.setString(12, value9);
					statement.setString(13, value10);
					statement.setString(14, value11);
					statement.setString(15, value12);
					statement.setString(16, value13);
					statement.setString(17, value14);
					statement.setString(18, value15);
					statement.setString(19, value16);
					statement.setString(20, value17);
					statement.setString(21, value18);
					statement.setString(22, value19);
					statement.setString(23, value20);
					statement.setString(24, value21);
					statement.setString(25, value22);
					statement.setString(26, value23);
					statement.setString(27, value24);
					statement.setString(28, value25);
					statement.executeUpdate();	
					
					
				}		
			  
				tableid++;
			}				 				 
			con.close();
			System.out.println("Data Store in DataBase sucessfully......................................");
		}
		return "Data Store in Database Suceesfully.........";
	}




	@Override
	public ArrayList<DataIngestionTableModel> getTableIdByFileId(String fileId, String dataBaseName) throws DataIngestionDaoException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<DataIngestionTableModel> list=new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt=connection.prepareStatement(DataIngestionQueryConstant.SELECT_TABLEID_BYFILEID.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, fileId);
			result=pstmt.executeQuery();
			
			while(result.next())
			{
				DataIngestionTableModel model=new DataIngestionTableModel();
				model.setTableId(result.getString("tableId"));
				model.setTableName(result.getString("tableName"));
				list.add(model);
			}
			return list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataIngestionDaoException("unable to get"+e.getMessage());
			
		}
	
		}



	@Override
	public ArrayList<DataIngestionTableModel> getTableIngestionTableData(String tableId, String dataBaseName) throws DataIngestionDaoException{
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<DataIngestionTableModel> list=new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt=connection.prepareStatement(DataIngestionQueryConstant.SELECT_DATAINGESTION_TABLEDETAILS.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
		    pstmt.setString(1, tableId);
		     result=pstmt.executeQuery();
		     while(result.next())
		     {
		    	 DataIngestionTableModel modeldata=buildData(result);
		    	 list.add(modeldata);
		     }
		     return list;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataIngestionDaoException("unable to get"+e.getMessage());
		}
	
	}



	private DataIngestionTableModel buildData(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		DataIngestionTableModel modeldata=new DataIngestionTableModel();
		modeldata.setFieldId(result.getLong("fieldId"));
		//modeldata.setTableId(result.getInt("tableId"));
		//modeldata.setTableName(result.getString("tableName"));
		modeldata.setCol1(result.getString("col1"));
		modeldata.setCol2(result.getString("col2"));
		modeldata.setCol3(result.getString("col3"));
		modeldata.setCol4(result.getString("col4"));
		modeldata.setCol5(result.getString("col5"));
		modeldata.setCol6(result.getString("col6"));
		modeldata.setCol7(result.getString("col7"));
		modeldata.setCol8(result.getString("col8"));
		modeldata.setCol9(result.getString("col9"));
		modeldata.setCol10(result.getString("col10"));
		modeldata.setCol11(result.getString("col11"));
		modeldata.setCol12(result.getString("col12"));
		modeldata.setCol13(result.getString("col13"));
		modeldata.setCol14(result.getString("col14"));
		modeldata.setCol15(result.getString("col15"));
		modeldata.setCol16(result.getString("col16"));
		modeldata.setCol17(result.getString("col17"));
		modeldata.setCol18(result.getString("col18"));
		modeldata.setCol19(result.getString("col19"));
		modeldata.setCol20(result.getString("col21"));
		modeldata.setCol21(result.getString("col21"));
		modeldata.setCol22(result.getString("col22"));
		modeldata.setCol23(result.getString("col23"));
		modeldata.setCol24(result.getString("col24"));
		modeldata.setCol25(result.getString("col25"));
		return modeldata;
		
	}



	@Override
	public ArrayList<DataIngestionModel> getDataIngestionDetails(String dataBaseName) throws DataIngestionDaoException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement psta = null;
		ResultSet rs=null;
		ArrayList<DataIngestionModel> list=new ArrayList<>();
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			psta=connection.prepareStatement(DataIngestionQueryConstant.SELECT_DATAINGESTION_DETAILS.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
	       rs=psta.executeQuery();
	       while(rs.next()) {
	       DataIngestionModel model=buildDetails(rs);
	       list.add(model);
	       }
	       return list;
		}
	
	
		catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionDaoException("unable to get" +e.getMessage());
		}
		 finally {
				LOGGER.debug("closing the connections");
				InvestorDatabaseUtill.close(rs, psta, connection);
			}
	}



	private DataIngestionModel buildDetails(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		DataIngestionModel model=new DataIngestionModel();
		model.setFileId(rs.getInt("fileid"));
		model.setClient(rs.getString("client"));
		model.setDocumentType(rs.getString("documentType"));
		model.setAnalystName(rs.getString("analystName"));
	   model.setReportFrom(rs.getDate("reportFrom"));
	   model.setReportTo(rs.getDate("reportTo"));
	   model.setFileName(rs.getString("fileName"));
	   model.setFileType(rs.getString("fileType"));
	   model.setFileData(rs.getBytes("fileData"));
	   return model;
	}



//	@Override
//	public String deleteDataIngestionDetailsById(String fileId, String dataBaseName) throws DataIngestionDaoException {
//		// TODO Auto-generated method stub
//	    Connection con=null;
//	    PreparedStatement psta=null;
//	    try
//	    {
//	    	con=InvestorDatabaseUtill.getConnection();
//	    	psta=con.prepareStatement(DataIngestionQueryConstant.DELETE_DATAINGESTION_BYFILEID.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
//	     psta.setString(1, fileId);
//	     psta.executeUpdate();
//	    
//	    }
//	    catch (Exception e) {
//			// TODO: handle exception
//	    	throw new DataIngestionDaoException("unable to delete" +e.getMessage());
//		}
//	    
//	    finally {
//			LOGGER.debug("closing the connections");
//			InvestorDatabaseUtill.close( psta, con);
//		}
//		return null;
//	}



	@Override
	public DataIngestionModel getdataIngetionByfileId(String fileId, String dataBaseName) throws DataIngestionDaoException{
		Connection connection = null;
		PreparedStatement psta = null;
		ResultSet rs=null;
	
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			psta=connection.prepareStatement(DataIngestionQueryConstant.SELECT_DATAINGESTION_BYFILEID.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
	       psta.setString(1, fileId);
			rs=psta.executeQuery();
	       while(rs.next()) {
	       DataIngestionModel model=buildDetails(rs);
	      return model;
	       }
	 
		}
	
	
		catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionDaoException("unable to get" +e.getMessage());
		}
		 finally {
				LOGGER.debug("closing the connections");
				InvestorDatabaseUtill.close(rs, psta, connection);
			}
		return null;
	}

	@Override
	public ArrayList<DataIngestionTableModel> updatedataIngestionTableData(ArrayList<DataIngestionTableModel> dataIngestionTableData,
			String tableId, String dataBaseName) throws DataIngestionDaoException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement pstmt=null;
		try {
       connection=InvestorDatabaseUtill.getConnection();
  	    pstmt=connection.prepareStatement(DataIngestionQueryConstant.UPDATE_DATAINGESTION_TABLEDETAILS.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
   	    Iterator it=dataIngestionTableData.iterator();
   	    while(it.hasNext())
   	    {
   	    	DataIngestionTableModel model=(DataIngestionTableModel) it.next();
   	    	pstmt.setString(1, model.getTableName());
 		   pstmt.setString(2, model.getCol1());
 		   pstmt.setString(3, model.getCol2());
 		   pstmt.setString(4, model.getCol3());
 		   pstmt.setString(5, model.getCol4());
 		   pstmt.setString(6, model.getCol5());
 		   pstmt.setString(7, model.getCol6());
 		   pstmt.setString(8, model.getCol7());
 		   pstmt.setString(9, model.getCol8());
 		   pstmt.setString(10, model.getCol9());
 		   pstmt.setString(11, model.getCol10());
 		   pstmt.setString(12, model.getCol11());
 		   pstmt.setString(13, model.getCol12());
 		   pstmt.setString(14, model.getCol13());
 		   pstmt.setString(15, model.getCol14());
 		   pstmt.setString(16, model.getCol15());
 		   pstmt.setString(17, model.getCol16());
 		   pstmt.setString(18, model.getCol17());
 		   pstmt.setString(19,model.getCol8());
 		   pstmt.setString(20, model.getCol19());
 		   pstmt.setString(21, model.getCol20());
 		   pstmt.setString(22, model.getCol21());
 		   pstmt.setString(23, model.getCol22());
 		   pstmt.setString(24, model.getCol23());
 		   pstmt.setString(25, model.getCol24());
 		   pstmt.setString(26, model.getCol25());
 		  // pstmt.seti(27,model.getFieldId() );
 		   pstmt.setLong(27, model.getFieldId());
   	    
 		   pstmt.executeUpdate();
   	    }
   	    return dataIngestionTableData;
       
		}catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionDaoException("unable to update" +e.getMessage());
		}
		finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		
	
	}



	@Override
	public DataIngestionMappingModel addDataIngestionMappingTableData(
			DataIngestionMappingModel dataIngestionMappingTable, String dataBaseName) throws DataIngestionDaoException {
		Connection connection = null;
		PreparedStatement psta = null;
		Date date = new Date();
		System.out.println("database name" + dataBaseName);
		try {
			connection = InvestorDatabaseUtill.getConnection();
			psta = connection.prepareStatement(DataIngestionQueryConstant.INSERT_INTO_DATAINGESTION_MAPPINGTABLE
					.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			
			String mapId = UUID.randomUUID().toString();
			dataIngestionMappingTable.setMapId(mapId);
			psta.setString(1, dataIngestionMappingTable.getMapId());
			psta.setLong(2, dataIngestionMappingTable.getFieldId());
			psta.setString(3, dataIngestionMappingTable.getAnalyst());
			psta.setString(4, dataIngestionMappingTable.getCompanyName());
			psta.setString(5, dataIngestionMappingTable.getDocumentType());
			psta.setString(6, dataIngestionMappingTable.getYear());
			psta.setString(7, dataIngestionMappingTable.getLineItemName());
			psta.setString(8, dataIngestionMappingTable.getQuarter());
			psta.setString(9, dataIngestionMappingTable.getType());
			psta.setString(10, dataIngestionMappingTable.getValue());
			psta.executeUpdate();
			return dataIngestionMappingTable;

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new DataIngestionDaoException("unable to create data Ingestion mapping table"+e.getMessage());
		} finally {
			LOGGER.info("closing the connection");
			InvestorDatabaseUtill.close(psta, connection);

		}
	}



	@Override
	public ArrayList<DataIngestionMappingModel> getDataIngestionMappingTableDetails(String dataBaseName)
			throws DataIngestionDaoException {
		Connection connection = null;
		PreparedStatement psta = null;
		ResultSet rs=null;
		ArrayList<DataIngestionMappingModel> list=new ArrayList<>();
		try
		{
			connection = InvestorDatabaseUtill.getConnection();
			psta=connection.prepareStatement(DataIngestionQueryConstant.SELECT_DATAINGESTION_MAPPING_TABLE.replace(DataIngestionQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
		    rs=psta.executeQuery();
		    while(rs.next())
		    {
		    	DataIngestionMappingModel model=new DataIngestionMappingModel();
		    	model.setMapId(rs.getString("mapId"));
		    	model.setFieldId(rs.getLong("FieldId"));
		    	model.setAnalyst(rs.getString("analyst"));
		    	model.setCompanyName(rs.getString("companyName"));
		    	model.setDocumentType(rs.getString("documentType"));
		    	model.setYear(rs.getString("year"));
		    	model.setLineItemName(rs.getString("lineItemName"));
		    	model.setQuarter(rs.getString("quarter"));
		    	model.setYear(rs.getString("type"));
		    	model.setValue(rs.getString("value"));
		    	list.add(model);
		    }
		    return list;
		}
		catch (Exception e) 
		{
			throw new DataIngestionDaoException("unable to create data Ingestion mapping table"+e.getMessage());
		} finally {
			LOGGER.info("closing the connection");
			InvestorDatabaseUtill.close(psta, connection);

		}
		}
	}








		
	




