package com.Anemoi.InvestorRelation.UserModel;

public class UserQueryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	public static final String USERID = "userid";

	public static final String FIRST_NAME = "firstName";

	public static final String LAST_NAME = "lastName";

	public static final String EMAIL = "email";

	public static final String MOBILE_NUMBER = "mobileNumber";

	public static final String DOMAIN = "domain";

	public static final String ASSIGNEDNAME = "assignedName";

	public static final String ROLENAME = "roleName";

	public static final String STATUS = "userStatus";

	public static final String PASSWORD = "password";

	public static final String EDITEDON = "editedOn";

	public static final String CREATEDON = "createdOn";

	public static final String INSERT_INTO_USERDETAILS = "INSERT INTO #$DataBaseName#$.dbo.userTable values(?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SELECT_USER_BY_ID = "SELECT * FROM #$DataBaseName#$.dbo.userTable where userid=?";

	public static final String SELECT_ALL_USERS = "SELECT * FROM #$DataBaseName#$.dbo.userTable ORDER BY (createdOn) ASC ,RAND()";

	public static final String UPDATE_USER = "UPDATE #$DataBaseName#$.dbo.userTable SET firstName=?, lastName=?, email=?, mobileNumber=?, domain=?, assignedName=?, roleName=?,userStatus=? ,password=?, editedOn=? WHERE userid=?";

	public static final String DELETE_USER_BY_ID = "DELETE #$DataBaseName#$.dbo.userTable WHERE userid=?";

	public static final String SELECT_USER_BY_ROLENAME = "SELECT * FROM #$DataBaseName#$.dbo.userTable WHERE roleName=?";

	public static final String SELECT_USERDETAILS_BYEMAILID = "SELECT * FROM #$DataBaseName#$.dbo.userTable WHERE email=?";

}
