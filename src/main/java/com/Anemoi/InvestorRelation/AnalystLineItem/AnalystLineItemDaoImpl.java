package com.Anemoi.InvestorRelation.AnalystLineItem;

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
public class AnalystLineItemDaoImpl implements AnalystLineItemDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalystLineItemDaoImpl.class);

	@Override
	public AnalystLineItemEntity createAnalystLineItem(AnalystLineItemEntity lineItem, String dataBaseName)
			throws AnalystLineItemDaoException {

		Connection connection = null;
		PreparedStatement psta = null;
		Date date = new Date();
		try

		{

			connection = InvestorDatabaseUtill.getConnection();
			psta = connection.prepareStatement(AnalystLineItemQueryConstant.INSERT_INTO_ANALYSTLINE_ITEM
					.replace(AnalystLineItemQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			String aid = UUID.randomUUID().toString();
			lineItem.setAnalystLineId(aid);
			String id = lineItem.getAnalystLineId();
			psta.setString(1, id);
			psta.setString(2, lineItem.getAnalystName());
			psta.setString(3, lineItem.getLineItemName());
			psta.setString(4, lineItem.getAnalystLineItemName());
			psta.setString(5, lineItem.getAnalystTableHeaderName());
			psta.setString(6, lineItem.getMasterTableSource());
			psta.setLong(7, date.getTime());

			psta.executeUpdate();
			return lineItem;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AnalystLineItemDaoException("unable to create analyst line item " + e.getMessage());
		} finally {
			LOGGER.info("closing the connection");
			InvestorDatabaseUtill.close(psta, connection);

		}

	}

	@Override
	public AnalystLineItemEntity getAnalystLineItemById(String aid, String dataBaseName)
			throws AnalystLineItemDaoException {
		System.out.println("welcome" + aid);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			System.out.println("welcome" + connection);
			pstmt = connection.prepareStatement(AnalystLineItemQueryConstant.SELECT_ANALYSTLINEITEM_BY_ID
					.replace(AnalystLineItemQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, aid);
			result = pstmt.executeQuery();
			while (result.next()) {
				AnalystLineItemEntity analystLineItem = buildAnalystLineItemDeatils(result);
				return analystLineItem;
			}
		} catch (Exception e) {
			LOGGER.error("Analyst line Item contact not found" + e.getMessage());
			throw new AnalystLineItemDaoException("unable to get analyst line item by id" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}

	private AnalystLineItemEntity buildAnalystLineItemDeatils(ResultSet result) throws SQLException {
		AnalystLineItemEntity analystlineItem = new AnalystLineItemEntity();
		analystlineItem.setAnalystLineId(result.getString(AnalystLineItemQueryConstant.ANALYSTLINEID));
		analystlineItem.setAnalystName(result.getString(AnalystLineItemQueryConstant.ANALYSTNAME));
		analystlineItem.setLineItemName(result.getString(AnalystLineItemQueryConstant.LINEITEMNAME));
		analystlineItem.setAnalystLineItemName(result.getString(AnalystLineItemQueryConstant.ANALYSTLINEITEMNAME));
		analystlineItem
				.setAnalystTableHeaderName(result.getString(AnalystLineItemQueryConstant.ANALYSTTABLEHEADERNAME));
		analystlineItem.setMasterTableSource(result.getString(AnalystLineItemQueryConstant.MASTERTABLESOURCE));
		analystlineItem.setCreatedOn(result.getLong(AnalystLineItemQueryConstant.CREATEDON));
		System.out.println("analystlineItem" + analystlineItem);
		return analystlineItem;
	}

	@Override
	public List<AnalystLineItemEntity> getAllAnalystLineItem(String dataBaseName) throws AnalystLineItemDaoException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<AnalystLineItemEntity> listofanlystlineItemDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(AnalystLineItemQueryConstant.SELECT_ANALYSTLINEITEM
					.replace(AnalystLineItemQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				AnalystLineItemEntity analystLineitem = buildAnalystLineItemDeatils(result);
				listofanlystlineItemDetails.add(analystLineitem);
			}
			return listofanlystlineItemDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of analyst line item" + e.getMessage());
			e.printStackTrace();
			throw new AnalystLineItemDaoException("unbale to get analyst line item " + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}

	}

	@Override
	public ArrayList<AnalystLineItemEntity> getByanalystnameandTable(String analystName, String masterTableSource,
			String dataBaseName) throws AnalystLineItemDaoException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<AnalystLineItemEntity> listNalaystlineitem = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(AnalystLineItemQueryConstant.SELECT_ANALYSTLINEITEM_BY_ANALYSTNAME
					.replace(AnalystLineItemQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, analystName);
			pstmt.setString(2, masterTableSource);
			result = pstmt.executeQuery();
			while (result.next()) {
				AnalystLineItemEntity analystLineitem = buildAnalystLineItemDeatils(result);
				listNalaystlineitem.add(analystLineitem);
			}
			return listNalaystlineitem;
		} catch (Exception e) {
			LOGGER.error("unble to get list of analyst line item" + e.getMessage());
			e.printStackTrace();
			throw new AnalystLineItemDaoException(
					"unable to get analyst line item details by analyst name and master table source" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}

	}

	@Override
	public String updateAnalystlineItem(String lineItemName,String analystName,String analystLineItemName, String dataBaseName) throws AnalystLineItemDaoException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = InvestorDatabaseUtill.getConnection();
		     pstmt = connection.prepareStatement(AnalystLineItemQueryConstant.UPDATE_ANALYSTLINEITEM
					.replace(AnalystLineItemQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, lineItemName);
			pstmt.setString(2, analystName);
			pstmt.setString(3, analystLineItemName);
		pstmt.executeUpdate();
		
		return "update sucessfully";
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new AnalystLineItemDaoException("unable to update" + e.getMessage());
		}
	}

}
