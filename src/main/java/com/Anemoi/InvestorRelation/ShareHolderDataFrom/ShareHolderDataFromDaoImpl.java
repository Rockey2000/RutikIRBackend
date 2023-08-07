package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.AnalystLineItem.AnalystLineItemDaoException;
import com.Anemoi.InvestorRelation.AnalystLineItem.AnalystLineItemQueryConstant;
import com.Anemoi.InvestorRelation.AnalystLineItem.AnalystLineItemServiceException;
import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;
import com.Anemoi.InvestorRelation.DataIngestion.DataIngestionDaoException;
import com.Anemoi.InvestorRelation.DataIngestion.DataIngestionQueryConstant;

import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

@Singleton

public class ShareHolderDataFromDaoImpl implements ShareHoderDataFromDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShareHolderDataFromDaoImpl.class);

	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";

	private static String DATABASENAME = "databaseName";

	@SuppressWarnings("resource")
	@Override
	public ShareHolderDataFromEntity createNewShareHolderDataForm(ShareHolderDataFromEntity shareholderdataform,
			String dataBaseName) throws ShareHolderDataFormDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<String> datelist=new ArrayList<>();

		try {
			connection = InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			String inputDate=shareholderdataform.getDate();
			  DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		        Date date = inputFormat.parse(inputDate);
	            String formattedDate = outputFormat.format(date);
	            pstmt=connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT_DATE.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
//	            pstmt.setString(1, shareholderdataform.getClientName());
	            pstmt.setString(1,shareholderdataform.getClientName());
				  rs=pstmt.executeQuery();
				  while(rs.next())
				  {
					  datelist.add(rs.getString("date"));
				  }
					boolean isMatched = datelist.stream().anyMatch(formattedDate::equalsIgnoreCase);
//					System.out.println("value" + analystlineItemList);
					if (isMatched == false && !datelist.contains(formattedDate)) 
					{
			pstmt = connection.prepareStatement(ShareHolderDataFromQuaryConstant.INSERT_INTO_SHAREHOLDERDATAFORM
					.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			String shareid = UUID.randomUUID().toString();

			shareholderdataform.setShareId(shareid);
			String id = shareholderdataform.getShareId();
			System.out.println(id + " " + shareholderdataform);
			pstmt.setString(1, shareid);
			pstmt.setString(2,shareholderdataform.getClientName());
			pstmt.setString(3, shareholderdataform.getPortfolioId());
			pstmt.setString(4, shareholderdataform.getFolio());
			pstmt.setString(5, shareholderdataform.getShareholderName());
			pstmt.setString(6, shareholderdataform.getHoldingValue());
			pstmt.setString(7, shareholderdataform.getHoldingPercentage());
			pstmt.setString(8, shareholderdataform.getHoldingCost());
			pstmt.setString(9, shareholderdataform.getMinorCode());
			pstmt.setString(10, shareholderdataform.getFundGroup());
			pstmt.setString(11,formattedDate);
			pstmt.executeUpdate();
		
					}
					else
					{
					throw new ShareHolderDataFormDaoException("Client already exists on "+ " " +formattedDate);
					}
					return shareholderdataform;
		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new ShareHolderDataFormDaoException( e.getMessage());

		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}

	}

	@Override
	public ShareHolderDataFromEntity getShareHolderDataFormById(String shareid, String dataBaseName)
			throws ShareHolderDataFormDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT__SHAREHOLDERDATAFORM_BY_ID
					.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, shareid);
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderDataFromEntity shareholderEntity = buildshareholderdataformDeatils(result);
				return shareholderEntity;
			}
		} catch (Exception e) {
			LOGGER.error("share holder data form not found" + e.getMessage());
			throw new ShareHolderDataFormDaoException("unable to get share holder data" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}

	private ShareHolderDataFromEntity buildshareholderdataformDeatils(ResultSet result) throws SQLException {

		ShareHolderDataFromEntity shareholderdataformEntity = new ShareHolderDataFromEntity();
		shareholderdataformEntity.setShareId(result.getString(ShareHolderDataFromQuaryConstant.SHAREID));
		shareholderdataformEntity.setClientName(result.getString(ShareHolderDataFromQuaryConstant.CLIENTNAME));
		shareholderdataformEntity.setPortfolioId(result.getString(ShareHolderDataFromQuaryConstant.PORTFOLIOID));
		shareholderdataformEntity.setFolio(result.getString(ShareHolderDataFromQuaryConstant.FOLIO));
		shareholderdataformEntity.setShareholderName(result.getString(ShareHolderDataFromQuaryConstant.SHAREHOLDERNAME));
		shareholderdataformEntity.setHoldingValue(result.getString(ShareHolderDataFromQuaryConstant.HOLDINGVALUE));
		shareholderdataformEntity.setHoldingPercentage(result.getString(ShareHolderDataFromQuaryConstant.HOLDINGPERCENTAGE));
		shareholderdataformEntity.setHoldingCost(result.getString(ShareHolderDataFromQuaryConstant.HOLDINGCOST));
		shareholderdataformEntity.setMinorCode(result.getString(ShareHolderDataFromQuaryConstant.MINORCODE));
		shareholderdataformEntity.setFundGroup(result.getString(ShareHolderDataFromQuaryConstant.FUNDGROUP));
		shareholderdataformEntity.setDate(result.getString(ShareHolderDataFromQuaryConstant.DATE));

		return shareholderdataformEntity;
	}

	@Override
	public List<ShareHolderDataFromEntity> getAllShareHolderDataFormDetails(String dataBaseName)
			throws SQLException, ShareHolderDataFormDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<ShareHolderDataFromEntity> listofshareholderdataformDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT_SHAREHOLDERDATAFORM
					.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderDataFromEntity cashflow = buildshareholderdataformDeatils(result);
				listofshareholderdataformDetails.add(cashflow);
			}
			return listofshareholderdataformDetails;
		} catch (Exception e) {
			LOGGER.error("unable to get list of share holder data form" + e.getMessage());
			e.printStackTrace();
			throw new ShareHolderDataFormDaoException("unable to get share holder data" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}

	}

	@Override
	public ShareHolderDataFromEntity updateShareHolderDAtaFormDetails(ShareHolderDataFromEntity shareholderdataform,
			String shareid, String dataBaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update shareholder dataform database name is ::" + dataBaseName + " cashId is ::" + shareid
				+ " request cash flow is ::" + shareholderdataform);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderDataFromQuaryConstant.UPDATE_SHAREHOLDERDATAFORM
					.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, shareholderdataform.getClientName());
			pstmt.setString(2, shareholderdataform.getPortfolioId());
			pstmt.setString(3, shareholderdataform.getFolio());
			pstmt.setString(4, shareholderdataform.getShareholderName());
			pstmt.setString(5, shareholderdataform.getHoldingValue());
			pstmt.setString(6, shareholderdataform.getHoldingPercentage());
			pstmt.setString(7, shareholderdataform.getHoldingCost());
			pstmt.setString(8, shareholderdataform.getMinorCode());
			pstmt.setString(9, shareholderdataform.getFundGroup());
			pstmt.setString(10, shareholderdataform.getDate());

			pstmt.setString(11, shareid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " shareholder datform updated successfully");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return shareholderdataform;
	}

	@Override
	public String deleteShareHolderDataForm(String shareid, String dataBaseName) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderDataFromQuaryConstant.DELETE_SHAREHOLDERDATAFORM_BY_ID
					.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, shareid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " shareholder dataform deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	@Override
	public ArrayList<ShareHolderDataFromEntity> uploadShareHolderDataExcelSheet(CompletedFileUpload file, String dataBaseName)
			throws ShareHolderDataFormDaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs=null;
		List<String> datelist=new ArrayList<>();
		boolean allExistInDatabase = true;
		ArrayList<ShareHolderDataFromEntity> list=new ArrayList<>();
		try
		{
			DataFormatter formatter = new DataFormatter();
			byte[] fileContent = file.getBytes();

			ByteArrayInputStream targetStream = new ByteArrayInputStream(fileContent);

			XSSFWorkbook workbook = new XSSFWorkbook(targetStream);

			XSSFSheet sheet = workbook.getSheetAt(0);

			int numRows = sheet.getLastRowNum() + 1;
			if (numRows <= 1) {
				throw new Exception("Excel sheet is empty");
			}
			
			int rownum1 = 0;

			for (Row row1 : sheet) {

				if (rownum1 == 0) {

					rownum1++;
					continue;
				}

				String clientName = (formatter.formatCellValue(row1.getCell(0)).trim());
				if (clientName.isEmpty()) {

					throw new ShareHolderDataFormDaoException("values Should not be empty or null ");

				}
				connection = InvestorDatabaseUtill.getConnection();

				statement = connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT_CLIENTNAME
						.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
				rs = statement.executeQuery();
				List<String> analystnameList = new ArrayList<>();
				while (rs.next()) {
					analystnameList.add(rs.getString("clientName"));
				}
				boolean isMatched = analystnameList.stream().anyMatch(clientName::equalsIgnoreCase);
				if (isMatched == false && !analystnameList.contains(clientName))
				 {
					throw new ShareHolderDataFormDaoException(clientName+","+"not present in client details table");
				}
			}

			int rownum2 = 0;

			for (Row row1 : sheet) {

				if (rownum2 == 0) {

					rownum2++;
					continue;
				}

				String shareholderName = (formatter.formatCellValue(row1.getCell(3)).trim());
				
	
				statement = connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT_SHAREHOLDERNAME
						.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
				rs = statement.executeQuery();
				List<String> shareholdernamelist = new ArrayList<>();
				while (rs.next()) {
					shareholdernamelist.add(rs.getString("name"));
				}
				boolean isMatched = shareholdernamelist.stream().anyMatch(shareholderName::equalsIgnoreCase);
//				System.out.println("value" + analystlineItemList);
				if (isMatched == false && !shareholdernamelist.contains(shareholderName))
				 {
//						System.out.println(Analystname+ "this analystname not present in analyst details table");
					throw new ShareHolderDataFormDaoException(shareholderName+","+"not present in shareholder contact  table");
				}
			}


			int rownum = 0;

			for (Row row1 : sheet) {
				if (rownum == 0) {
					rownum++;
					continue;
				}

				String clientName = formatter.formatCellValue(row1.getCell(0));
				String PortfolioId = formatter.formatCellValue(row1.getCell(1));
				String Folio = formatter.formatCellValue(row1.getCell(2));
				String ShareholderName = formatter.formatCellValue(row1.getCell(3));
				String HoldingValue = formatter.formatCellValue(row1.getCell(4));
				String HoldingPercentage = formatter.formatCellValue(row1.getCell(5));
				String holdingCost=formatter.formatCellValue(row1.getCell(6));
				String MinorCode = formatter.formatCellValue(row1.getCell(7));
				String fundgroup=formatter.formatCellValue(row1.getCell(8));
				String date = formatter.formatCellValue(row1.getCell(9));

				applyValidation(clientName, PortfolioId, Folio, ShareholderName, HoldingValue, HoldingPercentage,MinorCode, date,holdingCost,fundgroup);

			  statement=connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT_DATE.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			  statement.setString(1, clientName);
			  rs=statement.executeQuery();
			  while(rs.next())
			  {
				  datelist.add(rs.getString("date"));
			  }
			  statement=connection.prepareStatement(ShareHolderDataFromQuaryConstant.SELECT_DATE.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			  statement.setString(1, clientName);
			  rs=statement.executeQuery();
			  if(rs.next()==false)
			  { statement = connection.prepareStatement(ShareHolderDataFromQuaryConstant.INSERT_INTO_SHAREHOLDERDATAFORM
						.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
				String shareid = UUID.randomUUID().toString();
				statement.setString(1, shareid);
				statement.setString(2, clientName);
				statement.setString(3, PortfolioId);
				statement.setString(4, Folio);
				statement.setString(5, ShareholderName);
				statement.setString(6, HoldingValue);
				statement.setString(7, HoldingPercentage);
				statement.setString(8, holdingCost);
				statement.setString(9, MinorCode);
				statement.setString(10, fundgroup);
				statement.setString(11, date);
				statement.executeUpdate();
				rownum++;
				allExistInDatabase = false;
			  }
			  else {
				  
				boolean isMatched = datelist.stream().anyMatch(date::equalsIgnoreCase);
//				System.out.println("value" + analystlineItemList);
				if (isMatched == false && !datelist.contains(date)) {
				statement = connection.prepareStatement(ShareHolderDataFromQuaryConstant.INSERT_INTO_SHAREHOLDERDATAFORM
						.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
				String shareid = UUID.randomUUID().toString();
				statement.setString(1, shareid);
				statement.setString(2, clientName);
				statement.setString(3, PortfolioId);
				statement.setString(4, Folio);
				statement.setString(5, ShareholderName);
				statement.setString(6, HoldingValue);
				statement.setString(7, HoldingPercentage);
				statement.setString(8, holdingCost);
				statement.setString(9, MinorCode);
				statement.setString(10, fundgroup);
				statement.setString(11, date);
				statement.executeUpdate();
				rownum++;
				allExistInDatabase = false;
				}
				else
				{
					System.out.println("cannot insert duplicate date for client");
					ShareHolderDataFromEntity entity=new ShareHolderDataFromEntity();
					entity.setClientName(clientName);
					entity.setDate(date);
					list.add(entity);
//					allExistInDatabase = true;
				}
				
			}
			}

			return list;
		} catch (Exception e) {
			throw new ShareHolderDataFormDaoException(e.getMessage());
		}
		
	}

	
	

	private void applyValidation(String clientName, String portfolioId, String folio, String shareholderName,
			String holdingValue, String holdingPercentage, String minorCode, String date,String holdingCost,String fundgroup) throws Exception {
		// TODO Auto-generated method stub
		Pattern pattern;
		if (clientName == null || clientName.isEmpty() || portfolioId == null || portfolioId.isEmpty() || folio == null
				|| folio.isEmpty() || shareholderName == null || shareholderName.isEmpty() || holdingValue == null
				|| holdingValue.isEmpty() || holdingPercentage == null || holdingPercentage.isEmpty()
				|| minorCode == null || minorCode.isEmpty() || date == null || date.isEmpty() || holdingCost==null || holdingCost.isEmpty() ||  fundgroup==null || fundgroup.isEmpty()) {
			throw new Exception("value should not be null or empty");
		}
		
	}

}
