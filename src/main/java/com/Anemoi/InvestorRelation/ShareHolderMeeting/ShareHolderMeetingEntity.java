package com.Anemoi.InvestorRelation.ShareHolderMeeting;

import java.sql.Date;

public class ShareHolderMeetingEntity {

	private String shareholderid;
	
	private String meetingId;

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
	
	private String summary;
	
	private String actionItem;
	
	private String investorConcerns;
	
	private String analysis;

	private Long uploadedDate;
	
	private String uploadedBy;
 
	private String mediakey;
	
	private String momfileName;
	
	private String momFileType;
	
	private byte[] momFileData;
	
	private String audioVedioFileStatus;
	
	private String momStatus;

	public String getShareholderid() {
		return shareholderid;
	}

	public void setShareholderid(String shareholderid) {
		this.shareholderid = shareholderid;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getActionItem() {
		return actionItem;
	}

	public void setActionItem(String actionItem) {
		this.actionItem = actionItem;
	}

	public String getInvestorConcerns() {
		return investorConcerns;
	}

	public void setInvestorConcerns(String investorConcerns) {
		this.investorConcerns = investorConcerns;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public Long getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Long uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getMediakey() {
		return mediakey;
	}

	public void setMediakey(String mediakey) {
		this.mediakey = mediakey;
	}

	public String getMomfileName() {
		return momfileName;
	}

	public void setMomfileName(String momfileName) {
		this.momfileName = momfileName;
	}

	public String getMomFileType() {
		return momFileType;
	}

	public void setMomFileType(String momFileType) {
		this.momFileType = momFileType;
	}

	public byte[] getMomFileData() {
		return momFileData;
	}

	public void setMomFileData(byte[] momFileData) {
		this.momFileData = momFileData;
	}

	public String getAudioVedioFileStatus() {
		return audioVedioFileStatus;
	}

	public void setAudioVedioFileStatus(String audioVedioFileStatus) {
		this.audioVedioFileStatus = audioVedioFileStatus;
	}

	public String getMomStatus() {
		return momStatus;
	}

	public void setMomStatus(String momStatus) {
		this.momStatus = momStatus;
	}

	public ShareHolderMeetingEntity(String shareholderid, String meetingId, String date, String startTime,
			String endTime, String organisation, String stakeholderType, String meetingType, String subject,
			String broker, String location, String status, String comments, String participants, String feedback,
			String summary, String actionItem, String investorConcerns, String analysis, Long uploadedDate,
			String uploadedBy, String mediakey, String momfileName, String momFileType, byte[] momFileData,
			String audioVedioFileStatus, String momStatus) {
		super();
		this.shareholderid = shareholderid;
		this.meetingId = meetingId;
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
		this.summary = summary;
		this.actionItem = actionItem;
		this.investorConcerns = investorConcerns;
		this.analysis = analysis;
		this.uploadedDate = uploadedDate;
		this.uploadedBy = uploadedBy;
		this.mediakey = mediakey;
		this.momfileName = momfileName;
		this.momFileType = momFileType;
		this.momFileData = momFileData;
		this.audioVedioFileStatus = audioVedioFileStatus;
		this.momStatus = momStatus;
	}

	public ShareHolderMeetingEntity() {
		super();
	}
	
	

}