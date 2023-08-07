package com.Anemoi.InvestorRelation.MeetingScheduler;

import java.io.IOException;
import java.util.ArrayList;

public interface ServiceInterface {
	
	
	
	MSTeamschedule schdeulemsteammeeting(MSTeamschedule msts) throws ServiceException;

	ArrayList<MSTeamschedule> getmeedingSheduleList() throws ServiceException;

	MSTeamschedule updateMeetingSchedule(long meetingSheduleId,MSTeamschedule teamschedule) throws ServiceException;

	String deleteScheduleMeeting(long meetingSheduleId) throws ServiceException;

	MSTeamschedule getMeetingById(long meetingSheduleId) throws ServiceException;

}
