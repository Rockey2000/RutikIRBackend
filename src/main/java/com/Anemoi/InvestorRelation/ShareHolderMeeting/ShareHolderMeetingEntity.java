package com.Anemoi.InvestorRelation.ShareHolderMeeting;

public class ShareHolderMeetingEntity {
	
	private String holderid;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	private String organisation;
	
	private String stakeholderType;
	
	private String meetingType;
	
	private String subject;
	
	private String broker;
	
	private String location;
	
	private String status;
	
	private String comments;
	
	private String participants;
	
	private String feedback;

	public String getHolderid() {
		return holderid;
	}

	public void setHolderid(String holderid) {
		this.holderid = holderid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getStakeholderType() {
		return stakeholderType;
	}

	public void setStakeholderType(String stakeholderType) {
		this.stakeholderType = stakeholderType;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "ShareHolderMeetingEntity [holderid=" + holderid + ", date=" + date + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", organisation=" + organisation + ", stakeholderType=" + stakeholderType
				+ ", meetingType=" + meetingType + ", subject=" + subject + ", broker=" + broker + ", location="
				+ location + ", status=" + status + ", comments=" + comments + ", participants=" + participants
				+ ", feedback=" + feedback + "]";
	}

	public ShareHolderMeetingEntity(String holderid, String date, String startTime, String endTime, String organisation,
			String stakeholderType, String meetingType, String subject, String broker, String location, String status,
			String comments, String participants, String feedback) {
		super();
		this.holderid = holderid;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organisation = organisation;
		this.stakeholderType = stakeholderType;
		this.meetingType = meetingType;
		this.subject = subject;
		this.broker = broker;
		this.location = location;
		this.status = status;
		this.comments = comments;
		this.participants = participants;
		this.feedback = feedback;
	}

	public ShareHolderMeetingEntity() {
		super();
	}
	
	

}
