package com.Anemoi.InvestorRelation.AnalystDetails;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.BalanceSheet.BalanceSheetDaoImpl;

import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;

import jakarta.inject.Singleton;

@Singleton
public class AnalystDetailsDaoImpl implements AnalystDetailsDao
{

	private static final Logger LOGGER = LoggerFactory.getLogger(BalanceSheetDaoImpl.class);
	
	


	@Override
	public AnalystDetailsEntity createAnalystDetails(AnalystDetailsEntity analystDetails, String dataBaseName) throws AnalystDetailsDaoException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();

			LOGGER.debug("inserting the data");
			pstmt = connection.prepareStatement(AnalystDetailsQueryConstant.INSERT_INTO_ANALYSTDETAILS
					.replace(AnalystDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			String analystId =UUID.randomUUID().toString();
			String substring = analystId.substring(0,8).toString();
		

		  System.out.println(substring);
			analystDetails.setAnalystId(substring);
			String id = analystDetails.getAnalystId();
			Date d=new Date();
			pstmt.setString(1, id);
			pstmt.setString(2, analystDetails.getAnalystName());
			pstmt.setString(3, analystDetails.getPocName());
			pstmt.setString(4, analystDetails.getPocEmailId());	
			pstmt.setLong(5,d.getTime());
			pstmt.executeUpdate();
			return analystDetails;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new AnalystDetailsDaoException("unable to create analyst details" +e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
	

		
	}
	@Override
	public AnalystDetailsEntity getAnalystDetailsById(String analystId, String dataBaseName) throws AnalystDetailsDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(AnalystDetailsQueryConstant.SELECT_ANALYSTDETAILS_BY_ID
					.replace(AnalystDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, analystId);
			result = pstmt.executeQuery();
			while (result.next()) {
				AnalystDetailsEntity analystEntity = buildAnalystDeatils(result);
				return analystEntity;
			}
		} catch (Exception e) {
			LOGGER.error("Analyst Details Data not found" + e.getMessage());
			e.printStackTrace();
			throw new AnalystDetailsDaoException("unable to get analyst details by id"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
		
	}

	private AnalystDetailsEntity buildAnalystDeatils(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		
		AnalystDetailsEntity analystentity=new AnalystDetailsEntity();
		analystentity.setAnalystId(result.getString(AnalystDetailsQueryConstant.ANALYSTID));
		analystentity.setAnalystName(result.getString(AnalystDetailsQueryConstant.ANALYSTNAME));
		analystentity.setPocName(result.getString(AnalystDetailsQueryConstant.POCNAME));
		analystentity.setPocEmailId(result.getString(AnalystDetailsQueryConstant.POCEMAILID));
		analystentity.setCreatedOn(result.getLong(AnalystDetailsQueryConstant.CREATEDON));
		
		return analystentity;
	}
	@Override
	public ArrayList<AnalystDetailsEntity> getAllAnalystDetails(String dataBaseName) throws AnalystDetailsDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<AnalystDetailsEntity> listAnalystDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					AnalystDetailsQueryConstant.SELECT_ANALYSTDETAILS.replace(AnalystDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				AnalystDetailsEntity analystEntity = buildAnalystDeatils(result);
				listAnalystDetails.add(analystEntity);
			}
			return listAnalystDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of balance Sheet" + e.getMessage());
			e.printStackTrace();
			throw new AnalystDetailsDaoException("unable to get analyst details "+e.getMessage());
			

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
	

	}


	@Override
	public AnalystDetailsEntity updateAnalystDetails(AnalystDetailsEntity analystDetails, String analystId,
			String dataBaseName) throws AnalystDetailsDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in AnalystDetails database name is ::" + dataBaseName + " AnalystId is ::" + analystId + " request AnalystDetails is ::"
				+ analystDetails);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					AnalystDetailsQueryConstant.UPDATE_ANALYSTDETAILS.replace(AnalystDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, analystDetails.getAnalystName());
			pstmt.setString(2, analystDetails.getPocName());
			pstmt.setString(3, analystDetails.getPocEmailId());
			
			
			pstmt.setString(4, analystId);

			pstmt.executeUpdate();

			return analystDetails;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AnalystDetailsDaoException("unable to update analyst details"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
	
	}


	@Override
	public String deleteAnalystDetails(String analystId, String dataBaseName) throws AnalystDetailsDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(AnalystDetailsQueryConstant.DELETE_ANALYSTDETAILS_BY_ID
					.replace(AnalystDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, analystId);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " analystDetails deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AnalystDetailsDaoException("unable to delete analyst details by id"+e.getMessage());
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}
	
	
}
