package com.Anemoi.InvestorRelation.MeetingScheduler;

import java.util.List;

public class MSTeamschedule {
	private long meetingSheduleId;
	private String meetingId;
	private String eventId;
	private String joinUrl;
	private String title;
	private String agenda;
	private List<String> participant;
	private String meetingDate;
	private String startTime;
	private String endTime;
	private String meetingType;
	private boolean recordAutomatically;
	private String status;
	private String meetingDataStatus;
	public long getMeetingSheduleId() {
		return meetingSheduleId;
	}
	public void setMeetingSheduleId(long meetingSheduleId) {
		this.meetingSheduleId = meetingSheduleId;
	}
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getJoinUrl() {
		return joinUrl;
	}
	public void setJoinUrl(String joinUrl) {
		this.joinUrl = joinUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public List<String> getParticipant() {
		return participant;
	}
	public void setParticipant(List<String> participant) {
		this.participant = participant;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public boolean isRecordAutomatically() {
		return recordAutomatically;
	}
	public void setRecordAutomatically(boolean recordAutomatically) {
		this.recordAutomatically = recordAutomatically;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMeetingDataStatus() {
		return meetingDataStatus;
	}
	public void setMeetingDataStatus(String meetingDataStatus) {
		this.meetingDataStatus = meetingDataStatus;
	}
	public MSTeamschedule(long meetingSheduleId, String meetingId, String eventId, String joinUrl, String title,
			String agenda, List<String> participant, String meetingDate, String startTime, String endTime,
			String meetingType, boolean recordAutomatically, String status, String meetingDataStatus) {
		super();
		this.meetingSheduleId = meetingSheduleId;
		this.meetingId = meetingId;
		this.eventId = eventId;
		this.joinUrl = joinUrl;
		this.title = title;
		this.agenda = agenda;
		this.participant = participant;
		this.meetingDate = meetingDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.meetingType = meetingType;
		this.recordAutomatically = recordAutomatically;
		this.status = status;
		this.meetingDataStatus = meetingDataStatus;
	}
	public MSTeamschedule() {
		super();
	}
	
	
}