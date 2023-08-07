package com.Anemoi.InvestorRelation.UserModel;

public class UserEntity {

	private String userid;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String domain;
	private String assignedName;
	private String roleName;
	private String userStatus;
	private String password;
	private Long editedOn;
	private Long createdOn;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAssignedName() {
		return assignedName;
	}

	public void setAssignedName(String assignedName) {
		this.assignedName = assignedName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getEditedOn() {
		return editedOn;
	}

	public void setEditedOn(Long editedOn) {
		this.editedOn = editedOn;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public UserEntity(String userid, String firstName, String lastName, String email, String mobileNumber,
			String domain, String assignedName, String roleName, String userStatus, String password, Long editedOn,
			Long createdOn) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.domain = domain;
		this.assignedName = assignedName;
		this.roleName = roleName;
		this.userStatus = userStatus;
		this.password = password;
		this.editedOn = editedOn;
		this.createdOn = createdOn;
	}

	public UserEntity() {
		super();
	}

	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", domain=" + domain + ", assignedName=" + assignedName
				+ ", roleName=" + roleName + ", userStatus=" + userStatus + ", password=" + password + ", editedOn="
				+ editedOn + ", createdOn=" + createdOn + "]";
	}

}