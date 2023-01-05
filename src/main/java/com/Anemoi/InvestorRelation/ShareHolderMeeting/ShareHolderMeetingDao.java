package com.Anemoi.InvestorRelation.ShareHolderMeeting;

import java.sql.SQLException;
import java.util.List;

public interface ShareHolderMeetingDao {
	
ShareHolderMeetingEntity createNewShareHolderMeeting(ShareHolderMeetingEntity shareholdermeeting,String dataBaseName) throws ShareHolderMeetingDaoException;
	
ShareHolderMeetingEntity getShareHolderMeetingById(String holderid,String dataBaseName) throws ShareHolderMeetingDaoException;

	List<ShareHolderMeetingEntity> getAllShareHolderMeetingDetails(String dataBaseName) throws SQLException ,ShareHolderMeetingDaoException;

	ShareHolderMeetingEntity updateShareHolderMeetingDetails(ShareHolderMeetingEntity shareholdermeeting, String holderid,String dataBaseName);

	String deleteShareHolderMeeting(String holderid,String dataBaseName) throws SQLException;


}
