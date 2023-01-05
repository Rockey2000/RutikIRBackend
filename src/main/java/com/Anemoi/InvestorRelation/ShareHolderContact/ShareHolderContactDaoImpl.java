package com.Anemoi.InvestorRelation.ShareHolderContact;

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
import com.Anemoi.InvestorRelation.ShareHolderDataFrom.ShareHolderDataFromEntity;
import com.Anemoi.InvestorRelation.ShareHolderDataFrom.ShareHolderDataFromQuaryConstant;

import jakarta.inject.Singleton;


@Singleton
public class ShareHolderContactDaoImpl implements ShareHolderContactDao {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(ShareHolderContactDaoImpl.class);

	@Override
	public ShareHolderContactEntity createNewShareHolderContact(ShareHolderContactEntity shareholdercontact, String dataBaseName) throws ShareHolderContactDaoException {
		Connection connection=null;
		PreparedStatement pstmt=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(ShareHolderContactQuaryConstant.INSERT_INTO_SHAREHOLDERCONTACT
					.replace(ShareHolderContactQuaryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			String contactid = UUID.randomUUID().toString();
			
			shareholdercontact.setContactid(contactid);
			String id = shareholdercontact.getContactid();
			System.out.println(id+" "+shareholdercontact);
			pstmt.setString(1, contactid);
		//	pstmt.setString(2, shareholdercontact.getContactid());
			pstmt.setString(2, shareholdercontact.getName());
			pstmt.setString(3, shareholdercontact.getPoc());
			pstmt.setString(4, shareholdercontact.getEmail());
			pstmt.setString(5, shareholdercontact.getMinorcode());
			pstmt.setString(6, shareholdercontact.getAddress());
			pstmt.setString(7, shareholdercontact.getContact());
			
			pstmt.executeUpdate();
			return shareholdercontact;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new ShareHolderContactDaoException("unable to create share holder "+e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		
	}

	@Override
	public ShareHolderContactEntity getShareHolderContactById(String contactid, String dataBaseName)  throws ShareHolderContactDaoException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderContactQuaryConstant.SELECT__SHAREHOLDERCONTACT_BY_ID
					.replace(ShareHolderContactQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, contactid);
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderContactEntity shareholdercontactEntity = buildshareholdercontactDeatils(result);
				return shareholdercontactEntity;
			}
		} catch (Exception e) {
			LOGGER.error("share holder contact not found" + e.getMessage());
			throw new ShareHolderContactDaoException("unable to get share holder by id"+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}

	private ShareHolderContactEntity buildshareholdercontactDeatils(ResultSet result) throws SQLException {


		ShareHolderContactEntity shareholdercontactEntity = new ShareHolderContactEntity();
		shareholdercontactEntity.setContactid(result.getString(ShareHolderContactQuaryConstant.CONTACTID));
		shareholdercontactEntity.setName(result.getString(ShareHolderContactQuaryConstant.NAME));
		shareholdercontactEntity.setPoc(result.getString(ShareHolderContactQuaryConstant.POC));
		shareholdercontactEntity.setEmail(result.getString(ShareHolderContactQuaryConstant.EMAIL));
		shareholdercontactEntity.setMinorcode(result.getString(ShareHolderContactQuaryConstant.MINORCODE));
		shareholdercontactEntity.setAddress(result.getString(ShareHolderContactQuaryConstant.ADDRESS));
		shareholdercontactEntity.setContact(result.getString(ShareHolderContactQuaryConstant.CONTACT));
		

		return shareholdercontactEntity;
	}

	
	@Override
	public List<ShareHolderContactEntity> getAllShareHolderContactDetails(String dataBaseName) throws SQLException ,ShareHolderContactDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<ShareHolderContactEntity> listofshareholderContactDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					ShareHolderContactQuaryConstant.SELECT_SHAREHOLDERCONTACT.replace(ShareHolderContactQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderContactEntity shareholder = buildshareholdercontactDeatils(result);
				listofshareholderContactDetails.add(shareholder);
			}
			return listofshareholderContactDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of share holder contact" + e.getMessage());
			e.printStackTrace();
			throw new ShareHolderContactDaoException("unable to get share holder list"+e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		
	}

	@Override
	public ShareHolderContactEntity updateShareHolderContactDetails(ShareHolderContactEntity shareholdercontact,
			String contactid, String dataBaseName) throws ShareHolderContactDaoException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update shareholder contact database name is ::" + dataBaseName + " cashId is ::" + contactid + " request cash flow is ::"
				+ shareholdercontact);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					ShareHolderContactQuaryConstant.UPDATE_SHAREHOLDERCONTACT.replace(ShareHolderContactQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, shareholdercontact.getName());
			pstmt.setString(2, shareholdercontact.getPoc());
			pstmt.setString(3, shareholdercontact.getEmail());
			pstmt.setString(4, shareholdercontact.getMinorcode());
			pstmt.setString(5, shareholdercontact.getAddress());
			pstmt.setString(6, shareholdercontact.getContact());
			
			
			pstmt.setString(7, contactid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " shareholder Contact updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShareHolderContactDaoException("unable to update share holder list"+e.getMessage());
			
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return shareholdercontact;
	}

	@Override
	public String deleteShareHolderContact(String contactid, String dataBaseName) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderContactQuaryConstant.DELETE_SHAREHOLDERCONTACT_BY_ID
					.replace(ShareHolderContactQuaryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, contactid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " shareholder contact deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}

}
