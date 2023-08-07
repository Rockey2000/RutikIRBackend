package com.Anemoi.InvestorRelation.MeetingScheduler;

public class MeetingShedularQueryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	public static final String INSERT_INTO_MEETINGSHUDULAR = "INSERT INTO #$DataBaseName#$.dbo.meetingshedulartable values(?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_SHEDULEMEETINGDETAILS = "SELECT * FROM #$DataBaseName#$.dbo.meetingshedulartable ";

	public static final String SELECT_SHEDULEMEETINGDETAILS_BYID = "SELECT * FROM #$DataBaseName#$.dbo.meetingshedulartable where meetingSheduleId=?";

	
	public static final String UPDATE_MEETINGSCHEDULE= "UPDATE #$DataBaseName#$.dbo.meetingshedulartable SET title=?,agenda=?,participant=?,meetingDate=?,startTime=?,endTime=?,meetingType=?,recordAutomatically=? WHERE meetingSheduleId=?";

	public static final String UPDATE_GOOGLEMEETING= "UPDATE #$DataBaseName#$.dbo.meetingshedulartable SET joinUrl=?,title=?,agenda=?,participant=?,meetingDate=?,startTime=?,endTime=?,meetingType=?,recordAutomatically=? WHERE meetingSheduleId=?";

	public static final String DELETE_MEETINGSCHEDULE= "DELETE #$DataBaseName#$.dbo.meetingshedulartable WHERE meetingId=?";

	public static final String SELECT_MEETINGID_EVENTID = "SELECT meetingId,eventId,meetingType FROM #$DataBaseName#$.dbo.meetingshedulartable where meetingSheduleId=? ";

}
