package com.Anemoi.InvestorRelation.CashFlow;

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

import com.Anemoi.InvestorRelation.BalanceSheet.BalanceSheetEntity;
import com.Anemoi.InvestorRelation.BalanceSheet.BalanceSheetQueryConstant;
import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;
import com.Anemoi.InvestorRelation.UserModel.UserQueryConstant;

import jakarta.inject.Singleton;

@Singleton
public class CashFlowDaoImpl implements CashFlowDao {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(CashFlowDaoImpl.class);

	
	
	@Override
	public CashFlowEntity createNewCashFlow(CashFlowEntity CashFlowEntity, String dataBaseName)  throws CashFlowDaoException{
		
		Connection connection=null;
		PreparedStatement pstmt=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(CashFlowQuaryConstant.INSERT_INTO_CASHFLOW
					.replace(CashFlowQuaryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			String cashid = UUID.randomUUID().toString();
			
			CashFlowEntity.setCashId(cashid);
			String id = CashFlowEntity.getCashId();
			System.out.println(id+" "+CashFlowEntity);
			pstmt.setString(1, id);
			pstmt.setString(2, CashFlowEntity.getLineItem());
			pstmt.setString(3, CashFlowEntity.getAlternativeName());
			pstmt.setString(4, CashFlowEntity.getType());	
			pstmt.executeUpdate();
			return CashFlowEntity;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new CashFlowDaoException("unable to create cash flow"+e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
	
	}



	@Override
	public CashFlowEntity getCashFlowById(String cashid, String dataBaseName) throws CashFlowDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(CashFlowQuaryConstant.SELECT__CASHFLOW_BY_ID
					.replace(CashFlowQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, cashid);
			result = pstmt.executeQuery();
			while (result.next()) {
				CashFlowEntity cashFlowEntity = buildCashFlowDeatils(result);
				return cashFlowEntity;
			}
		} catch (Exception e) {
			LOGGER.error("Cash Flow Data not found" + e.getMessage());
			throw new CashFlowDaoException("unable to get cash flow by id"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}
	
	private CashFlowEntity buildCashFlowDeatils(ResultSet result) throws SQLException {


		CashFlowEntity cashFlowEntity = new CashFlowEntity();
		cashFlowEntity.setCashId(result.getString(CashFlowQuaryConstant.CASHID));
		cashFlowEntity.setLineItem(result.getString(CashFlowQuaryConstant.LINE_ITEM));
		cashFlowEntity.setAlternativeName(result.getString(CashFlowQuaryConstant.ALTERNATIVE_NAME));
		cashFlowEntity.setType(result.getString(CashFlowQuaryConstant.TYPE));

		return cashFlowEntity;
	}



	@Override
	public List<CashFlowEntity> getAllCashFlowDetails(String dataBaseName)  throws SQLException,CashFlowDaoException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<CashFlowEntity> listOfcashFlowDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					CashFlowQuaryConstant.SELECT_CASHFLOW.replace(CashFlowQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				CashFlowEntity balanceSheet = buildCashFlowDeatils(result);
				listOfcashFlowDetails.add(balanceSheet);
			}
			return listOfcashFlowDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of cash flow" + e.getMessage());
			e.printStackTrace();
			throw new CashFlowDaoException("unable to get cash flow list"+e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		

	}



	@Override
	public CashFlowEntity updateCashFlowDetails(CashFlowEntity cashFlowEntity, String cashid, String dataBaseName) throws CashFlowDaoException{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update cash flow database name is ::" + dataBaseName + " cashId is ::" + cashid + " request cash flow is ::"
				+ cashFlowEntity);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					CashFlowQuaryConstant.UPDATE_CASHFLOW.replace(CashFlowQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, cashFlowEntity.getLineItem());
			pstmt.setString(2, cashFlowEntity.getAlternativeName());
			pstmt.setString(3, cashFlowEntity.getType());
			
			
			pstmt.setString(4, cashid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " Cash Flow updated successfully");
			return cashFlowEntity;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CashFlowDaoException("unable to update "+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
	
	
	}



	@Override
	public String deleteCashFlow(String cashid, String dataBaseName)  throws CashFlowDaoException{
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(CashFlowQuaryConstant.DELETE_CASHFLOW_BY_ID
					.replace(CashFlowQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, cashid);
			pstmt.executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CashFlowDaoException("enter valid request"+e.getMessage());
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;

	}



	@Override
	public ArrayList<CashFlowEntity> addCashFlowEntityObject(ArrayList<CashFlowEntity> cashFlowEntity,
			String dataBaseName) {
		// TODO Auto-generated method stub

		Connection connection=null;
		PreparedStatement pstmt=null;
		String id=null;
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(CashFlowQuaryConstant.INSERT_INTO_CASHFLOW
					.replace(CashFlowQuaryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			Iterator it=cashFlowEntity.iterator();
			while(it.hasNext())
			{
				CashFlowEntity entity=(CashFlowEntity) it.next();
				String cashid=UUID.randomUUID().toString();
				id=cashid;
				entity.setCashId(cashid);;
				pstmt.setString(1,entity.getCashId());
				pstmt.setString(2,entity.getLineItem());
				pstmt.setString(3, entity.getAlternativeName());
				pstmt.setString(4,entity.getType());
				pstmt.executeUpdate();
				
			}

			return cashFlowEntity;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		LOGGER.info("closing the connections");
		InvestorDatabaseUtill.close(pstmt, connection);
	}
		return null;


	}
	}

		
	

