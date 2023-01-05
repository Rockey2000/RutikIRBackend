package com.Anemoi.InvestorRelation.BalanceSheet;

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
import com.Anemoi.InvestorRelation.IncomeStatement.IncomeStatementQuaryConstatnt;
import com.Anemoi.InvestorRelation.UserModel.UserEntity;
import com.Anemoi.InvestorRelation.UserModel.UserQueryConstant;

import jakarta.inject.Singleton;

@Singleton
public class BalanceSheetDaoImpl implements BalanceSheetDao
{

	private static final Logger LOGGER = LoggerFactory.getLogger(BalanceSheetDaoImpl.class);
	@Override
	
	
	public BalanceSheetEntity createNewBalanceSheetForm(BalanceSheetEntity balanceEntity, String dataBaseName)  throws BalanceSheetDaoException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();

			LOGGER.debug("inserting the data");
			pstmt = connection.prepareStatement(BalanceSheetQueryConstant.INSERT_INTO_BALANCESHEET_FORM
					.replace(BalanceSheetQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			String balanceId = UUID.randomUUID().toString();
			balanceEntity.setBalanceid(balanceId);
			String id = balanceEntity.getBalanceid();
			System.out.println(id+" "+balanceEntity);
			pstmt.setString(1, id);
			pstmt.setString(2, balanceEntity.getLineItem());
			pstmt.setString(3, balanceEntity.getAlternativeName());
			pstmt.setString(4, balanceEntity.getType());	
			pstmt.executeUpdate();
			return balanceEntity;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new BalanceSheetDaoException("unbale to create balance sheet"+e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
	

		
	}

	
	@Override
	public BalanceSheetEntity getBalanceById(String balanceid, String dataBaseName) throws BalanceSheetDaoException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(BalanceSheetQueryConstant.SELECT_BALANCESHEET_FORM_BY_ID
					.replace(BalanceSheetQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, balanceid);
			result = pstmt.executeQuery();
			while (result.next()) {
				BalanceSheetEntity balanceEntity = buildBalanceDeatils(result);
				return balanceEntity;
			}
		} catch (Exception e) {
			LOGGER.error("Balance Sheet Data not found" + e.getMessage());
			throw new BalanceSheetDaoException("unable to get balance sheet by id"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}

	private BalanceSheetEntity buildBalanceDeatils(ResultSet result) throws SQLException {


		BalanceSheetEntity balanceEntity = new BalanceSheetEntity();
		balanceEntity.setBalanceid(result.getString(BalanceSheetQueryConstant.BALANCEID));
        balanceEntity.setLineItem(result.getString(BalanceSheetQueryConstant.LINE_ITEM));
        balanceEntity.setAlternativeName(result.getString(BalanceSheetQueryConstant.ALTERNATIVE_NAME));
        balanceEntity.setType(result.getString(BalanceSheetQueryConstant.TYPE));

		return balanceEntity;
	}


	@Override
	public List<BalanceSheetEntity> getAllBalanceSheetDetails(String dataBaseName) throws BalanceSheetDaoException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<BalanceSheetEntity> listOfbalanceSheetDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					BalanceSheetQueryConstant.SELECT_BALANCESHEET_FORM.replace(BalanceSheetQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				BalanceSheetEntity balanceSheet = buildBalanceDeatils(result);
				listOfbalanceSheetDetails.add(balanceSheet);
			}
			return listOfbalanceSheetDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of balance Sheet" + e.getMessage());
			e.printStackTrace();
			throw new BalanceSheetDaoException("unable to balance sheet details"+e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		

	}

	@Override
	public BalanceSheetEntity updateBalanceSheetDetails(BalanceSheetEntity balanceEntity, String balanceid,
			String dataBaseName) throws BalanceSheetDaoException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update balance sheet database name is ::" + dataBaseName + " incomeId is ::" + balanceid + " request balance sheet is ::"
				+ balanceEntity);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					BalanceSheetQueryConstant.UPDATE_BALANCESHEET_FORM.replace(BalanceSheetQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, balanceEntity.getLineItem());
			pstmt.setString(2, balanceEntity.getAlternativeName());
			pstmt.setString(3, balanceEntity.getType());
			
			
			pstmt.setString(4, balanceid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " balance sheet updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BalanceSheetDaoException("cannot update "+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return balanceEntity;
	}

	@Override
	public String deleteBalanceSheet(String balanceid, String dataBaseName) throws BalanceSheetDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(BalanceSheetQueryConstant.DELETE_BALANCESHEET_FORM_BY_ID
					.replace(BalanceSheetQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, balanceid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " balance sheet deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BalanceSheetDaoException("unable to delete "+e.getMessage());
			
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}


	@Override
	public ArrayList<BalanceSheetEntity> addObjectBalanceSheetEntity(ArrayList<BalanceSheetEntity> balanceSheetEntity,
			String dataBaseName) throws BalanceSheetDaoException {

		Connection connection=null;
		PreparedStatement pstmt=null;
		String id=null;
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(BalanceSheetQueryConstant.INSERT_INTO_BALANCESHEET_FORM
					.replace(BalanceSheetQueryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			Iterator it=balanceSheetEntity.iterator();
			while(it.hasNext())
			{
				BalanceSheetEntity entity=(BalanceSheetEntity) it.next();
				String bid=UUID.randomUUID().toString();
				id=bid;
				entity.setBalanceid(id);
				pstmt.setString(1,entity.getBalanceid());
				pstmt.setString(2,entity.getLineItem());
				pstmt.setString(3, entity.getAlternativeName());
				pstmt.setString(4,entity.getType());
				pstmt.executeUpdate();
				
			}

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BalanceSheetDaoException("cannot get  "+e.getMessage());
		
		
	}finally {
		LOGGER.info("closing the connections");
		InvestorDatabaseUtill.close(pstmt, connection);
	}
		return balanceSheetEntity;

}
}