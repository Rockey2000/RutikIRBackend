package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;

import jakarta.inject.Singleton;

@Singleton

public class ShareHolderDataFromDaoImpl implements ShareHoderDataFromDao {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(ShareHolderDataFromDaoImpl.class);

	@Override
	public ShareHolderDataFromEntity createNewShareHolderDataForm(ShareHolderDataFromEntity shareholderdataform, String dataBaseName)  throws ShareHolderDataFormDaoException{
		Connection connection=null;
		PreparedStatement pstmt=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(ShareHolderDataFromQuaryConstant.INSERT_INTO_SHAREHOLDERDATAFORM
					.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			String shareid = UUID.randomUUID().toString();
			
			shareholderdataform.setShareId(shareid);
			String id = shareholderdataform.getShareId();
			System.out.println(id+" "+shareholderdataform);
			pstmt.setString(1, shareid);
			pstmt.setString(2, shareholderdataform.getClientId());
			pstmt.setString(3, shareholderdataform.getPortfolioId());
			pstmt.setString(4, shareholderdataform.getFolio());
			pstmt.setString(5, shareholderdataform.getShareholderName());
			pstmt.setString(6, shareholderdataform.getHoldingValue());
			pstmt.setString(7, shareholderdataform.getHoldingPercentage());
			pstmt.setString(8, shareholderdataform.getMinorCode());
			pstmt.setString(9, shareholderdataform.getDate());
			pstmt.executeUpdate();
			return shareholderdataform;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new ShareHolderDataFormDaoException("unable to create share holder data"+e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		
	}

	@Override
	public ShareHolderDataFromEntity getShareHolderDataFormById(String shareid, String dataBaseName) throws ShareHolderDataFormDaoException {
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
			throw new ShareHolderDataFormDaoException("unable to get share holder data"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}
	
	private ShareHolderDataFromEntity buildshareholderdataformDeatils(ResultSet result) throws SQLException {


		ShareHolderDataFromEntity shareholderdataformEntity = new ShareHolderDataFromEntity();
		shareholderdataformEntity.setShareId(result.getString(ShareHolderDataFromQuaryConstant.SHAREID));
		shareholderdataformEntity.setClientId(result.getString(ShareHolderDataFromQuaryConstant.CLIENTID));
		shareholderdataformEntity.setPortfolioId(result.getString(ShareHolderDataFromQuaryConstant.PORTFOLIOID));
		shareholderdataformEntity.setFolio(result.getString(ShareHolderDataFromQuaryConstant.FOLIO));
		shareholderdataformEntity.setShareholderName(result.getString(ShareHolderDataFromQuaryConstant.SHAREHOLDERNAME));
		shareholderdataformEntity.setHoldingValue(result.getString(ShareHolderDataFromQuaryConstant.HOLDINGVALUE));
		shareholderdataformEntity.setHoldingPercentage(result.getString(ShareHolderDataFromQuaryConstant.HOLDINGPERCENTAGE));
		shareholderdataformEntity.setMinorCode(result.getString(ShareHolderDataFromQuaryConstant.MINORCODE));
		shareholderdataformEntity.setDate(result.getString(ShareHolderDataFromQuaryConstant.DATE));

		return shareholderdataformEntity;
	}

	@Override
	public List<ShareHolderDataFromEntity> getAllShareHolderDataFormDetails(String dataBaseName) throws SQLException,ShareHolderDataFormDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<ShareHolderDataFromEntity> listofshareholderdataformDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					ShareHolderDataFromQuaryConstant.SELECT_SHAREHOLDERDATAFORM.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderDataFromEntity cashflow = buildshareholderdataformDeatils(result);
				listofshareholderdataformDetails.add(cashflow);
			}
			return listofshareholderdataformDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of share holder data form" + e.getMessage());
			e.printStackTrace();
			throw new ShareHolderDataFormDaoException("unable to get share holder data"+e.getMessage());

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
		LOGGER.info(".in update shareholder dataform database name is ::" + dataBaseName + " cashId is ::" + shareid + " request cash flow is ::"
				+ shareholderdataform);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					ShareHolderDataFromQuaryConstant.UPDATE_SHAREHOLDERDATAFORM.replace(ShareHolderDataFromQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, shareholderdataform.getClientId());
			pstmt.setString(2, shareholderdataform.getPortfolioId());
			pstmt.setString(3, shareholderdataform.getFolio());
			pstmt.setString(4, shareholderdataform.getShareholderName());
			pstmt.setString(5, shareholderdataform.getHoldingValue());
			pstmt.setString(6, shareholderdataform.getHoldingPercentage());
			pstmt.setString(7, shareholderdataform.getMinorCode());
			pstmt.setString(8, shareholderdataform.getDate());
			
			
			pstmt.setString(9, shareid);

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
	public String deleteShareHolderDataForm(String shareid, String dataBaseName)  throws SQLException{
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

}
