package com.Anemoi.InvestorRelation.MeetingScheduler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;
import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;
import com.azure.identity.UsernamePasswordCredential;
import com.azure.identity.UsernamePasswordCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.models.Attendee;
import com.microsoft.graph.models.DateTimeTimeZone;
import com.microsoft.graph.models.EmailAddress;
import com.microsoft.graph.models.ItemBody;
import com.microsoft.graph.models.OnlineMeeting;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.UserRequestBuilder;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;


@Singleton
public class ServiceImp implements ServiceInterface{

	@Inject
	MeetingDao meetingDao;
	
	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";
	private static String DATABASENAME = "databasename";

	private static String dataBaseName() {
		List<String> tenentList = ReadPropertiesFile.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = ReadPropertiesFile.dataBaseName(tenent);
		}
		return DATABASENAME;
	}
	
	@Override
	public MSTeamschedule schdeulemsteammeeting(MSTeamschedule msts) throws ServiceException {
		try
		{
		String dataBaseName=ServiceImp.dataBaseName();
		if(msts.getMeetingType().equalsIgnoreCase("Microsoft Teams"))
		{
		MSTeamschedule responseobject=this.meetingDao.saveTeamMeetingSchedule(msts,dataBaseName);
		return responseobject;
		}
		else
		{
			System.out.println("call");
			MSTeamschedule responsegoogle=this.meetingDao.saveGoogleMeetingSchedule(msts,dataBaseName);
			return responsegoogle;
		}
		}
		catch (Exception e) {

                throw new ServiceException(e.getMessage());
		}
	
		
	}

	@Override
	public ArrayList<MSTeamschedule> getmeedingSheduleList() throws ServiceException {
        try
        {
        	String dataBaseName=ServiceImp.dataBaseName();
        	ArrayList<MSTeamschedule> responselist=this.meetingDao.getmeetingShecdulList(dataBaseName);
        	return responselist;
        }
    	catch (Exception e) {

            throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public MSTeamschedule updateMeetingSchedule(long meetingSheduleId, MSTeamschedule teamschedule)
			throws ServiceException {
		try
		{
		String dataBaseName=ServiceImp.dataBaseName();
		if(teamschedule.getMeetingType().equalsIgnoreCase("Microsoft Teams"))
		{
		MSTeamschedule responseobject=this.meetingDao.updateTeamsmeetingSchedule(meetingSheduleId,teamschedule,dataBaseName);
		return responseobject;
		}
		else
		{
			MSTeamschedule response=this.meetingDao.updategoogleMeetingSchedule(meetingSheduleId,teamschedule,dataBaseName);
			return response;
		}
		}
		catch (Exception e) {

                throw new ServiceException(e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteScheduleMeeting(long meetingSheduleId) throws ServiceException {
		String meetingId=null;
		String eventId=null;
		String meetingtype=null;
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement psta=null;
		try
		{
			String dataBaseName=ServiceImp.dataBaseName();
			  con=InvestorDatabaseUtill.getConnection();
			  psta=con.prepareStatement(MeetingShedularQueryConstant.SELECT_MEETINGID_EVENTID.replace(MeetingShedularQueryConstant.DATA_BASE_PLACE_HOLDER,dataBaseName));
		        psta.setLong(1,meetingSheduleId);
		       rs=psta.executeQuery();
		       while(rs.next()) {
		    	   meetingId=rs.getString("meetingId");
		    	   eventId=rs.getString("eventId");
		    	   meetingtype=rs.getString("meetingType");
		       }
	if(meetingtype.equalsIgnoreCase("Microsoft Teams"))
	{
		this.meetingDao.deleteScheduleMeetingdetails(meetingId,eventId,dataBaseName);
		
		}
	else
	{
		this.meetingDao.deletegoogleMeetings(meetingId,eventId,dataBaseName);
	}
	JSONObject reposneJSON = new JSONObject();
	reposneJSON.put(STATUS, SUCCESS);
	reposneJSON.put(MSG, "delete meeting schedule");
	return reposneJSON.toString();
		}
		catch (Exception ex) {
			
			throw new ServiceException(ex.getMessage());
		}
		
	}

	@Override
	public MSTeamschedule getMeetingById(long meetingSheduleId) throws ServiceException {
		// TODO Auto-generated method stub
		
		String dataBaseName=ServiceImp.dataBaseName();
		try
		{
			MSTeamschedule response=this.meetingDao.getMeedingDataById(meetingSheduleId,dataBaseName);
			return response;
		}
		catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
