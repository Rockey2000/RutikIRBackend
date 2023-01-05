package com.Anemoi.InvestorRelation.IncomeStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;

import jakarta.inject.Singleton;

@Singleton
public class IncomeStatementDaoImpl implements IncomeStatementDao {
	private static final Logger LOGGER =LoggerFactory.getLogger(IncomeStatementDaoImpl.class);

	@Override
	public IncomeStatementEntity createIncomeStatement(IncomeStatementEntity incomestatement, String dataBaseName) throws IncomeStatementDaoException {
		Connection connection=null;
		PreparedStatement pstmt=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(IncomeStatementQuaryConstatnt.INSERT_INTO_INCOMESTATEMENT
					.replace(IncomeStatementQuaryConstatnt.DATA_BASE_PLACE_HOLDER,dataBaseName));
			String id = UUID.randomUUID().toString();
			
			incomestatement.setIncomeid(id);
			String id1 = incomestatement.getIncomeid();
			System.out.println(id+" "+incomestatement);
			pstmt.setString(1, id);
		
			pstmt.setString(2, incomestatement.getLineItem());
			pstmt.setString(3, incomestatement.getAlternativeName());
			pstmt.setString(4, incomestatement.getType());
			
			
			pstmt.executeUpdate();
			return incomestatement;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new IncomeStatementDaoException("unable to create income statement"+e.getMessage());
			
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		
	}

	@Override
	public IncomeStatementEntity getIncomeStatementById(String incomeid, String dataBaseName) throws IncomeStatementDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(IncomeStatementQuaryConstatnt.SELECT_INCOMESTATEMENT_BY_ID
					.replace(IncomeStatementQuaryConstatnt.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, incomeid);
			result = pstmt.executeQuery();
			while (result.next()) {
				IncomeStatementEntity incomestatementEntity = buildIncomeStatementDeatils(result);
				return incomestatementEntity;
			}
		} catch (Exception e) {
			LOGGER.error("incomestatement id not found" + e.getMessage());
			throw new IncomeStatementDaoException("unable to get income statement by id"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}

	private IncomeStatementEntity buildIncomeStatementDeatils(ResultSet result) throws SQLException {


		IncomeStatementEntity incomestatementEntity = new IncomeStatementEntity();
		incomestatementEntity.setIncomeid(result.getString(IncomeStatementQuaryConstatnt.INCOMEID));
		incomestatementEntity.setLineItem(result.getString(IncomeStatementQuaryConstatnt.LINEITEM));
		incomestatementEntity.setAlternativeName(result.getString(IncomeStatementQuaryConstatnt.ALTERNATIVENAME));
		incomestatementEntity.setType(result.getString(IncomeStatementQuaryConstatnt.TYPE));
		
		

		return incomestatementEntity;
	}
	
	@Override
	public List<IncomeStatementEntity> getAllIncomeStatementDetails(String dataBaseName) throws SQLException,IncomeStatementDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<IncomeStatementEntity> listofincomestatementDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					IncomeStatementQuaryConstatnt.SELECT_INCOMESTATEMENT.replace(IncomeStatementQuaryConstatnt.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				IncomeStatementEntity incomestatement = buildIncomeStatementDeatils(result);
				listofincomestatementDetails.add(incomestatement);
			}
			return listofincomestatementDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of incomestatemnt" + e.getMessage());
			e.printStackTrace();
			throw new IncomeStatementDaoException("unable to get income statement"+e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		
	}

	@Override
	public IncomeStatementEntity updateIncomeStatementDetails(IncomeStatementEntity incomestatement, String incomeid,
			String dataBaseName)  throws IncomeStatementDaoException{
	
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update incomestatement database name is ::" + dataBaseName + " incomeId is ::" + incomeid + " request role model is ::"
				+ incomestatement);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					IncomeStatementQuaryConstatnt.UPDATE_INCOMESTATEMENT.replace(IncomeStatementQuaryConstatnt.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, incomestatement.getLineItem());
			pstmt.setString(2, incomestatement.getAlternativeName());
			pstmt.setString(3, incomestatement.getType());
			
			
			pstmt.setString(4, incomeid);

			 pstmt.executeUpdate();
		

			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncomeStatementDaoException("unable to update" +e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return incomestatement;
	
		
	}

	@Override
	public String deleteIncomeStatement(String incomeid, String dataBaseName) throws IncomeStatementDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(IncomeStatementQuaryConstatnt.DELETE_INCOMESTATEMENT_BY_ID
					.replace(IncomeStatementQuaryConstatnt.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, incomeid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " income statement deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncomeStatementDaoException("unable to delete" +e.getMessage());
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}

	@Override
	public ArrayList<IncomeStatementEntity> addIncomeStatementObject(
			ArrayList<IncomeStatementEntity> incomeentity, String dataBaseName)
			throws IncomeStatementDaoException {
		Connection connection=null;
		PreparedStatement pstmt=null;
		
		String id=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			
			pstmt=connection.prepareStatement(IncomeStatementQuaryConstatnt.INSERT_INTO_INCOMESTATEMENT
					.replace(IncomeStatementQuaryConstatnt.DATA_BASE_PLACE_HOLDER,dataBaseName));
			Iterator it=incomeentity.iterator(); 
			while(it.hasNext())
			{
            	 IncomeStatementEntity entity=(IncomeStatementEntity) it.next();
			String incomeid = UUID.randomUUID().toString();
			id=incomeid;
			entity.setIncomeid(id);
			pstmt.setString(1, entity.getIncomeid());
			pstmt.setString(2,entity.getLineItem());
			pstmt.setString(3, entity.getAlternativeName());
			pstmt.setString(4, entity.getType());
			pstmt.executeUpdate();
              }

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return incomeentity;
		
	}

}
