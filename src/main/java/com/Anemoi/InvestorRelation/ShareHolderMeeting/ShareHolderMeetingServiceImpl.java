package com.Anemoi.InvestorRelation.ShareHolderMeeting;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;
import com.Anemoi.InvestorRelation.ShareHolderDataFrom.ShareHolderDataFromEntity;
import com.Anemoi.InvestorRelation.ShareHolderDataFrom.ShareHolderDataFromQuaryConstant;
import com.Anemoi.InvestorRelation.ShareHolderDataFrom.ShareHolderDataFromServiceImpl;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ShareHolderMeetingServiceImpl  implements ShareHolderMeetingService{
	
	@Inject
	private ShareHolderMeetingDao sharemeetingdao;
	
	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";
	

	private static String DATABASENAME="databaseName";
	
	private static String dataBaseName() {
		
		List<String>tenentList= ReadPropertiesFile.getAllTenant();
		for(String tenent : tenentList) {
			 DATABASENAME = ReadPropertiesFile.dataBaseName(tenent);
		}
		return DATABASENAME;
		
	}

	@Override
	public ShareHolderMeetingEntity createShareHolderMeeting(ShareHolderMeetingEntity sharehodermeeting) throws SQLException , ShareHolderMeetingServiceException {
		try
		{
			String dataBaseName=ShareHolderMeetingServiceImpl.dataBaseName();
			
		//	applyValidation(sharehodermeeting);
			
			ShareHolderMeetingEntity createNewShareHolderMeeting=this.sharemeetingdao.createNewShareHolderMeeting(sharehodermeeting,dataBaseName);
			return createNewShareHolderMeeting;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ShareHolderMeetingServiceException("unable to create share holder "+e.getMessage());
		}
		
	}

	private void applyValidation(ShareHolderMeetingEntity sharehodermeeting) throws Exception {
		// TODO Auto-generated method stub
		if(sharehodermeeting.getSubject()==null || sharehodermeeting.getSubject().isEmpty())
		{
			throw new Exception("subject should not be null or empty");
		}
		if(sharehodermeeting.getOrganisation()==null || sharehodermeeting.getOrganisation().isEmpty())
		{
			throw new Exception("organisation should not be null or empty");
		}
		if(sharehodermeeting.getStakeholderType()==null || sharehodermeeting.getStakeholderType().isEmpty())
		{
			throw new Exception("StakeHolder type should not be null or empty");
		}
		if(sharehodermeeting.getMeetingType()==null || sharehodermeeting.getMeetingType().isEmpty())
		{
			throw new Exception("meeting type should not be null or empty");
		}
		if(sharehodermeeting.getBroker()==null || sharehodermeeting.getBroker().isEmpty())
		{
			throw new Exception("Broker should not be null or empty");
		}
		if(sharehodermeeting.getLocation()==null || sharehodermeeting.getLocation().isEmpty())
		{
			throw new Exception("Location should not be null or empty");
		}
		if(sharehodermeeting.getStatus()==null || sharehodermeeting.getStatus().isEmpty())
		{
			throw new Exception("status should not be null or empty");
		}
		
	}

	@Override
	public ShareHolderMeetingEntity getShareHolderMeetingById(String holderid) throws SQLException,ShareHolderMeetingServiceException {
		try {
			String dataBaseName = ShareHolderMeetingServiceImpl.dataBaseName();
			if (holderid == null || holderid.isEmpty()) {
			System.out.print("ShareHolderMeeting id must not be null or empty");
			}
			ShareHolderMeetingEntity sharemeetingEntity = this.sharemeetingdao.getShareHolderMeetingById(holderid, dataBaseName);
			return sharemeetingEntity;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ShareHolderMeetingServiceException("unable to get share holder by id"+e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShareHolderMeetingEntity> getShareHolderMeetingDetails() throws SQLException,ShareHolderMeetingServiceException {
		try {
			String dataBaseName = ShareHolderMeetingServiceImpl.dataBaseName();
			List<ShareHolderMeetingEntity> sharehodermeetingEntity = this.sharemeetingdao.getAllShareHolderMeetingDetails(dataBaseName);
			JSONObject object = new JSONObject();
			JSONArray shareholdermeetingJsonData = getJSONFromShareHolderMeetingList(sharehodermeetingEntity);
			object.put(sharehodermeetingEntity,shareholdermeetingJsonData);
			return sharehodermeetingEntity;
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new ShareHolderMeetingServiceException("unable to get share holder list"+e.getMessage());
	}
		
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray getJSONFromShareHolderMeetingList(List<ShareHolderMeetingEntity> shareholdermeetingEntity) {
		JSONArray array = new JSONArray();
		for (ShareHolderMeetingEntity shareholder : shareholdermeetingEntity) {
			JSONObject object = buildJsonFromShareHolderMeetingForm(shareholder);
			array.add(object);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromShareHolderMeetingForm(ShareHolderMeetingEntity shareholdermeeting) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ShareHolderMeetingQuaryContant.HOLDERID, shareholdermeeting.getHolderid());
		jsonObject.put(ShareHolderMeetingQuaryContant.DATE,shareholdermeeting.getDate());
		jsonObject.put(ShareHolderMeetingQuaryContant.STARTTIME,shareholdermeeting.getStartTime());
		jsonObject.put(ShareHolderMeetingQuaryContant.ENDTIME,shareholdermeeting.getEndTime());
		jsonObject.put(ShareHolderMeetingQuaryContant.ORGANISATION,shareholdermeeting.getOrganisation());
		jsonObject.put(ShareHolderMeetingQuaryContant.STAKEHOLDERTYPE,shareholdermeeting.getStakeholderType());
		jsonObject.put(ShareHolderMeetingQuaryContant.MEETINGTYPE,shareholdermeeting.getMeetingType());
		jsonObject.put(ShareHolderMeetingQuaryContant.SUBJECT,shareholdermeeting.getSubject());
		jsonObject.put(ShareHolderMeetingQuaryContant.BROKER,shareholdermeeting.getBroker());
		jsonObject.put(ShareHolderMeetingQuaryContant.LOCATION,shareholdermeeting.getLocation());
		jsonObject.put(ShareHolderMeetingQuaryContant.STATUS,shareholdermeeting.getStatus());
		jsonObject.put(ShareHolderMeetingQuaryContant.COMMENTS,shareholdermeeting.getComments());
		jsonObject.put(ShareHolderMeetingQuaryContant.PARTICIPANTS,shareholdermeeting.getParticipants());
		jsonObject.put(ShareHolderMeetingQuaryContant.FEEDBACK,shareholdermeeting.getFeedback());
		
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShareHolderMeetingEntity updateShareHolderMeeting(ShareHolderMeetingEntity sharehodermeeting, String holderid) throws ShareHolderMeetingServiceException {
		try {
			String dataBaseName = ShareHolderMeetingServiceImpl.dataBaseName();
				if (holderid == null || holderid.isEmpty()) {
					System.out.println("share holder meeting id can't be null or empty ");
				
				}
				ShareHolderMeetingEntity updatedshareholdermeeting = this.sharemeetingdao.updateShareHolderMeetingDetails(sharehodermeeting, holderid, dataBaseName);
				JSONObject object = new JSONObject();
				JSONObject jsonFromUser = buildJsonFromUpdatedShareHolderMeetingDataForm(updatedshareholdermeeting);
				object.put(updatedshareholdermeeting, jsonFromUser);
				return updatedshareholdermeeting;

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromUpdatedShareHolderMeetingDataForm(ShareHolderMeetingEntity updatedshareholdermeeting) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ShareHolderMeetingQuaryContant.HOLDERID, updatedshareholdermeeting.getHolderid());
		jsonObject.put(ShareHolderMeetingQuaryContant.DATE, updatedshareholdermeeting.getDate());
		jsonObject.put(ShareHolderMeetingQuaryContant.STARTTIME, updatedshareholdermeeting.getStartTime());
		jsonObject.put(ShareHolderMeetingQuaryContant.ENDTIME, updatedshareholdermeeting.getEndTime());
		jsonObject.put(ShareHolderMeetingQuaryContant.ORGANISATION, updatedshareholdermeeting.getOrganisation());
		
		jsonObject.put(ShareHolderMeetingQuaryContant.STAKEHOLDERTYPE, updatedshareholdermeeting.getStakeholderType());
		
		jsonObject.put(ShareHolderMeetingQuaryContant.MEETINGTYPE, updatedshareholdermeeting.getMeetingType());
		
		jsonObject.put(ShareHolderMeetingQuaryContant.SUBJECT, updatedshareholdermeeting.getSubject());
		
		jsonObject.put(ShareHolderMeetingQuaryContant.BROKER, updatedshareholdermeeting.getBroker());
		jsonObject.put(ShareHolderMeetingQuaryContant.LOCATION, updatedshareholdermeeting.getLocation());
		jsonObject.put(ShareHolderMeetingQuaryContant.STATUS, updatedshareholdermeeting.getStatus());
		jsonObject.put(ShareHolderMeetingQuaryContant.COMMENTS, updatedshareholdermeeting.getComments());
		jsonObject.put(ShareHolderMeetingQuaryContant.PARTICIPANTS, updatedshareholdermeeting.getParticipants());
		jsonObject.put(ShareHolderMeetingQuaryContant.FEEDBACK, updatedshareholdermeeting.getFeedback());
		
		return jsonObject;
}

	
	@SuppressWarnings("unchecked")
	@Override
	public ShareHolderMeetingEntity deleteShareHoderMeeting(String holderid) throws ShareHolderMeetingServiceException {
		try {
			String dataBaseName = ShareHolderMeetingServiceImpl.dataBaseName();
				if (holderid == null || holderid.isEmpty()) {
					System.out.println("share holder meeting cannot be null or empty");
				
				}
				ShareHolderMeetingEntity shareholdermeeting =sharemeetingdao.getShareHolderMeetingById(holderid, dataBaseName);
				if (shareholdermeeting == null) {
					
					
				}
			   this.sharemeetingdao.deleteShareHolderMeeting(holderid, dataBaseName);
				JSONObject reposneJSON = new JSONObject();
				reposneJSON.put(STATUS, SUCCESS);
				reposneJSON.put(MSG, "share holder meeting deleted suucessfully");
				return shareholdermeeting;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShareHolderMeetingServiceException("unable to delete share holder "+e.getMessage());
		}
	
	}

}
