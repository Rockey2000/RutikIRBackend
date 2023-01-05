package com.Anemoi.InvestorRelation.ShareHolderMeeting;

import java.sql.SQLException;
import java.util.List;

public interface ShareHolderMeetingService {
	
	ShareHolderMeetingEntity createShareHolderMeeting(ShareHolderMeetingEntity sharehodermeeting) throws SQLException,ShareHolderMeetingServiceException;
	 
	ShareHolderMeetingEntity getShareHolderMeetingById(String holderid) throws SQLException,ShareHolderMeetingServiceException;
	
	List<ShareHolderMeetingEntity> getShareHolderMeetingDetails() throws SQLException,ShareHolderMeetingServiceException;
	
	ShareHolderMeetingEntity updateShareHolderMeeting(ShareHolderMeetingEntity sharehodermeeting, String holderid)throws ShareHolderMeetingServiceException;
	
	ShareHolderMeetingEntity deleteShareHoderMeeting(String holderid) throws ShareHolderMeetingServiceException;

}
