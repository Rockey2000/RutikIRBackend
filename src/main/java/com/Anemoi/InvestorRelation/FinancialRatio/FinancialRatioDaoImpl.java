package com.Anemoi.InvestorRelation.FinancialRatio;

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
public class FinancialRatioDaoImpl implements FinancialRatioDao {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(FinancialRatioDaoImpl.class);

	@Override
	public FinancialRatioEntity createNewFinancialRatio(FinancialRatioEntity financialratioEntity, String dataBaseName)  throws FinancialRatioDaoException{

		Connection connection=null;
		PreparedStatement pstmt=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(FinancialRatioQuaryConstant.INSERT_INTO_FINANCIALRATIO
					.replace(FinancialRatioQuaryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			String financialid = UUID.randomUUID().toString();
			
			financialratioEntity.setFinancialid(financialid);
			String id = financialratioEntity.getFinancialid();
			System.out.println(id+" "+financialratioEntity);
			pstmt.setString(1, id);
			pstmt.setString(2, financialratioEntity.getLineItem());
			pstmt.setString(3, financialratioEntity.getFormulaType());
			pstmt.setString(4, financialratioEntity.getFormula());	
			pstmt.executeUpdate();
			return financialratioEntity;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new FinancialRatioDaoException("unable to create finacial ratio "+e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		
	}

	@Override
	public FinancialRatioEntity getFianancialRatioById(String financialid, String dataBaseName) throws FinancialRatioDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(FinancialRatioQuaryConstant.SELECT__FINANCIALRATIO_BY_ID
					.replace(FinancialRatioQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, financialid);
			result = pstmt.executeQuery();
			while (result.next()) {
				FinancialRatioEntity financialratioEntity = buildfinancialratioDeatils(result);
				return financialratioEntity;
			}
		} catch (Exception e) {
			LOGGER.error("financial ratio Data not found" + e.getMessage());
			throw new FinancialRatioDaoException("unable to get finacial ratio entity"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}
	
	private FinancialRatioEntity buildfinancialratioDeatils(ResultSet result) throws SQLException {


		FinancialRatioEntity financialratioEntity = new FinancialRatioEntity();
		financialratioEntity.setFinancialid(result.getString(FinancialRatioQuaryConstant.FINANCIALID));
		financialratioEntity.setLineItem(result.getString(FinancialRatioQuaryConstant.LINEITEM));
		financialratioEntity.setFormulaType(result.getString(FinancialRatioQuaryConstant.FORMULATYPE));
		financialratioEntity.setFormula(result.getString(FinancialRatioQuaryConstant.FORMULA));

		return financialratioEntity;
	}

	@Override
	public List<FinancialRatioEntity> getAllFinancialRatioDetails(String dataBaseName) throws FinancialRatioDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<FinancialRatioEntity> listOffinancialratioDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					FinancialRatioQuaryConstant.SELECT_FINANCIALRATIO.replace(FinancialRatioQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				FinancialRatioEntity financialratio = buildfinancialratioDeatils(result);
				listOffinancialratioDetails.add(financialratio);
			}
			return listOffinancialratioDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of fiancial Ratio" + e.getMessage());
			e.printStackTrace();
			throw new FinancialRatioDaoException("unable to get finacial ratio "+e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		

	}

	@Override
	public FinancialRatioEntity updateFinancialRatioDetails(FinancialRatioEntity financialratioEntity,
			String financialid, String dataBaseName) throws FinancialRatioDaoException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update financial ratio database name is ::" + dataBaseName + " financialId is ::" + financialid + " request financial ratio is ::"
				+ financialratioEntity);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					FinancialRatioQuaryConstant.UPDATE_FINANCIALRATIO.replace(FinancialRatioQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, financialratioEntity.getLineItem());
			pstmt.setString(2, financialratioEntity.getFormulaType());
			pstmt.setString(3, financialratioEntity.getFormula());
			
			
			pstmt.setString(4, financialid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " financial ratio updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new FinancialRatioDaoException("can not update "+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return financialratioEntity;
	}

	@Override
	public String deleteFinancialRatio(String financialid, String dataBaseName) throws FinancialRatioDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(FinancialRatioQuaryConstant.DELETE_FINANCIALRATIO_BY_ID
					.replace(FinancialRatioQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, financialid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " financial ratio deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;

	}


}
