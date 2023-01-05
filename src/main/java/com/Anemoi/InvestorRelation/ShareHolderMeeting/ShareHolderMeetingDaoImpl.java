package com.Anemoi.InvestorRelation.ShareHolderMeeting;

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
public class ShareHolderMeetingDaoImpl implements ShareHolderMeetingDao {

	private static final Logger LOGGER =LoggerFactory.getLogger(ShareHolderMeetingDaoImpl.class);

	@Override
	public ShareHolderMeetingEntity createNewShareHolderMeeting(ShareHolderMeetingEntity shareholdermeeting, String dataBaseName) throws ShareHolderMeetingDaoException {
		Connection connection=null;
		PreparedStatement pstmt=null;
		
		try
		{
			connection=InvestorDatabaseUtill.getConnection();
			LOGGER.debug("inserting the data");
			
			pstmt=connection.prepareStatement(ShareHolderMeetingQuaryContant.INSERT_INTO_SHAREHOLDERMEETING
					.replace(ShareHolderMeetingQuaryContant.DATA_BASE_PLACE_HOLDER,dataBaseName));
			String holderid = UUID.randomUUID().toString();
			
			shareholdermeeting.setHolderid(holderid);
			String id = shareholdermeeting.getHolderid();
			System.out.println(id+" "+shareholdermeeting);
			pstmt.setString(1, holderid);
			pstmt.setString(2, shareholdermeeting.getDate());
			pstmt.setString(3, shareholdermeeting.getStartTime());
			pstmt.setString(4, shareholdermeeting.getEndTime());
			pstmt.setString(5, shareholdermeeting.getOrganisation());
			pstmt.setString(6, shareholdermeeting.getStakeholderType());
			pstmt.setString(7, shareholdermeeting.getMeetingType());
			pstmt.setString(8, shareholdermeeting.getSubject());
			pstmt.setString(9, shareholdermeeting.getBroker());
			pstmt.setString(10, shareholdermeeting.getLocation());
			pstmt.setString(11, shareholdermeeting.getStatus());
			pstmt.setString(12, shareholdermeeting.getComments());
			pstmt.setString(13, shareholdermeeting.getParticipants());
			pstmt.setString(14, shareholdermeeting.getFeedback());
			pstmt.executeUpdate();
			return shareholdermeeting;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new ShareHolderMeetingDaoException("unable to create share holder"+e.getMessage());
			
		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
	
	}

	@Override
	public ShareHolderMeetingEntity getShareHolderMeetingById(String holderid, String dataBaseName) throws ShareHolderMeetingDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderMeetingQuaryContant.SELECT__SHAREHOLDERMEETING_BY_ID
					.replace(ShareHolderMeetingQuaryContant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, holderid);
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderMeetingEntity shareholdermeetingEntity = buildshareholdermeetingDeatils(result);
				return shareholdermeetingEntity;
			}
		} catch (Exception e) {
			LOGGER.error("share holder meeting not found" + e.getMessage());
			throw new ShareHolderMeetingDaoException("unable to get share holder "+e.getMessage());
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;
	}
	
	private ShareHolderMeetingEntity buildshareholdermeetingDeatils(ResultSet result) throws SQLException {


		ShareHolderMeetingEntity shareholdermeetingEntity = new ShareHolderMeetingEntity();
		shareholdermeetingEntity.setHolderid(result.getString(ShareHolderMeetingQuaryContant.HOLDERID));
		shareholdermeetingEntity.setDate(result.getString(ShareHolderMeetingQuaryContant.DATE));
		shareholdermeetingEntity.setStartTime(result.getString(ShareHolderMeetingQuaryContant.STARTTIME));
		shareholdermeetingEntity.setEndTime(result.getString(ShareHolderMeetingQuaryContant.ENDTIME));
		shareholdermeetingEntity.setOrganisation(result.getString(ShareHolderMeetingQuaryContant.ORGANISATION));
		shareholdermeetingEntity.setStakeholderType(result.getString(ShareHolderMeetingQuaryContant.STAKEHOLDERTYPE));
		shareholdermeetingEntity.setMeetingType(result.getString(ShareHolderMeetingQuaryContant.MEETINGTYPE));
		shareholdermeetingEntity.setSubject(result.getString(ShareHolderMeetingQuaryContant.SUBJECT));
		shareholdermeetingEntity.setBroker(result.getString(ShareHolderMeetingQuaryContant.BROKER));
		shareholdermeetingEntity.setLocation(result.getString(ShareHolderMeetingQuaryContant.LOCATION));
		shareholdermeetingEntity.setStatus(result.getString(ShareHolderMeetingQuaryContant.STATUS));
		shareholdermeetingEntity.setComments(result.getString(ShareHolderMeetingQuaryContant.COMMENTS));
		shareholdermeetingEntity.setParticipants(result.getString(ShareHolderMeetingQuaryContant.PARTICIPANTS));
		shareholdermeetingEntity.setFeedback(result.getString(ShareHolderMeetingQuaryContant.FEEDBACK));

		


		return shareholdermeetingEntity;
	}

	@Override
	public List<ShareHolderMeetingEntity> getAllShareHolderMeetingDetails(String dataBaseName) throws SQLException,ShareHolderMeetingDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<ShareHolderMeetingEntity> listofshareholdermeetingDetails = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					ShareHolderMeetingQuaryContant.SELECT_SHAREHOLDERMEETING.replace(ShareHolderMeetingQuaryContant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				ShareHolderMeetingEntity sharemeeting = buildshareholdermeetingDeatils(result);
				listofshareholdermeetingDetails.add(sharemeeting);
			}
			return listofshareholdermeetingDetails;
		} catch (Exception e) {
			LOGGER.error("unble to get list of share holder meeting" + e.getMessage());
			e.printStackTrace();
			throw new ShareHolderMeetingDaoException("unable to get share holder list"+e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		

	}

	@Override
	public ShareHolderMeetingEntity updateShareHolderMeetingDetails(ShareHolderMeetingEntity shareholdermeeting,
			String holderid, String dataBaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update shareholder meeting database name is ::" + dataBaseName + " holderId is ::" + holderid + " request shareholder meeting is ::"
				+ shareholdermeeting);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					ShareHolderMeetingQuaryContant.UPDATE_SHAREHOLDERMEETING.replace(ShareHolderMeetingQuaryContant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, shareholdermeeting.getDate());
			pstmt.setString(2, shareholdermeeting.getStartTime());
			pstmt.setString(3, shareholdermeeting.getEndTime());
			pstmt.setString(4, shareholdermeeting.getOrganisation());
			pstmt.setString(5, shareholdermeeting.getStakeholderType());
			pstmt.setString(6, shareholdermeeting.getMeetingType());
			pstmt.setString(7, shareholdermeeting.getSubject());
			pstmt.setString(8, shareholdermeeting.getBroker());
			pstmt.setString(9, shareholdermeeting.getLocation());
			pstmt.setString(10, shareholdermeeting.getStatus());
			pstmt.setString(11, shareholdermeeting.getComments());
			pstmt.setString(12, shareholdermeeting.getParticipants());
			pstmt.setString(13, shareholdermeeting.getFeedback());
			
			
			pstmt.setString(14, holderid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " shareholder meeting updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return shareholdermeeting;
	}

	@Override
	public String deleteShareHolderMeeting(String holderid, String dataBaseName) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ShareHolderMeetingQuaryContant.DELETE_SHAREHOLDERMEETING_BY_ID
					.replace(ShareHolderMeetingQuaryContant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, holderid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " shareholder meeting deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}

}
