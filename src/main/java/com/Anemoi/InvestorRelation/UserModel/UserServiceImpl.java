package com.Anemoi.InvestorRelation.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.mail.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;
import com.Anemoi.MailSession.MailService;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userdao;

	@Inject
	private MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
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
	public UserEntity createNewUser(UserEntity user) throws UserModelServiceException {
//		Transport transport = null;

		try {
//			String tempPassword = UUID.randomUUID().toString();
//			String password=tempPassword.substring(0,8).toString();
//			user.getRoleName();
//			Session session = com.Anemoi.MailSession.MailSessionInstance.getMailSession();
//			transport = session.getTransport();
//			transport.connect();
//			sendFirstTimeRegistrationEmailToUser(transport, user,password);
			String dataBaseName = UserServiceImpl.dataBaseName();

			// applyValidation(user);

			UserEntity createNewUser = this.userdao.createNewUser(user, dataBaseName);
			return createNewUser;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UserModelServiceException("unable to create user " + e.getMessage());
		}

	}

	private void sendFirstTimeRegistrationEmailToUser(Transport transport, UserEntity user, String password) {
		// TODO Auto-generated method stub
		logger.debug("sending credentials to user.....");
		logger.debug("User : " + user);
		logger.debug("Sending temp password to user ::");
		System.out.println("*************************");
		mailService.sendFirstTimeRegistrationMailToUser(transport, user.getEmail(), user.getFirstName(),
				user.getRoleName(), password);
	}

	private void applyValidation(UserEntity user) throws Exception {
		// TODO Auto-generated method stub
		Pattern pattern;
		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
			throw new Exception("first name should not be null or empty");
		}
		pattern = Pattern.compile("[A-Z]{1}[a-z]{2,14}");
		boolean result = pattern.matcher(user.getFirstName()).matches();
		if (!result) {
			throw new Exception("please enter the valid firstname formate");
		}

		if (user.getLastName() == null || user.getLastName().isEmpty()) {
			throw new Exception("last name should not be null or empty");
		}
		pattern = Pattern.compile("[A-Z]{1}[a-z]{2,14}");
		boolean result1 = pattern.matcher(user.getLastName()).matches();
		if (!result1) {
			throw new Exception("please enter the valid lastname formate");
		}

		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			throw new Exception("Email cannot be null or empty");
		}
		String emailRegex = "([a-zA-Z0-9]+)([\\.{1}])?([a-zA-Z0-9]+)\\@(?:gmail|GMAIL)([\\.])(?:com|COM)";
		pattern = Pattern.compile(emailRegex);
		boolean result3 = pattern.matcher(user.getEmail()).matches();
		if (!result3) {
			throw new Exception(user.getEmail() + " is invalid email formate");
		}

		if (user.getMobileNumber() == null || user.getMobileNumber().isEmpty()) {
			throw new Exception("mobileNumber cannot be null or empty");
		}
		pattern = Pattern.compile("[6-9]{1}[0-9]{9}");
		boolean result2 = pattern.matcher(user.getMobileNumber()).matches();
		if (!result2) {
			throw new Exception("invalid mobile number formate");
		}

		if (user.getDomain() == null || user.getDomain().isEmpty()) {
			throw new Exception("domain cannot be null or empty");
		}
		if (user.getAssignedName() == null || user.getAssignedName().isEmpty()) {
			throw new Exception("assigned Name cannot be null or empty");
		}
		if (user.getRoleName() == null || user.getRoleName().isEmpty()) {
			throw new Exception("role name cannot be null or empty");
		}
		if (user.getUserStatus() == null || user.getUserStatus().isEmpty()) {
			throw new Exception("status cannot be null or empty");
		}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new Exception("password cannot be null or empty");
		}

	}

	@Override
	public UserEntity getUserById(String userid) throws UserModelServiceException {
		try {
			String dataBaseName = UserServiceImpl.dataBaseName();
			if (userid == null || userid.isEmpty()) {
				System.out.print("User id must not be null or empty");
			}
			UserEntity user = this.userdao.getUserById(userid, dataBaseName);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserModelServiceException("unable to get user by id" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAllUsers() throws UserModelServiceException {
		try {
			String dataBaseName = UserServiceImpl.dataBaseName();
			List<UserEntity> users = this.userdao.getAllUsers(dataBaseName);
			JSONObject object = new JSONObject();
			JSONArray userJsonData = getJSONFromUserList(users);
			object.put(users, userJsonData);
			return users;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserModelServiceException("unable to get user list" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	private JSONArray getJSONFromUserList(List<UserEntity> users) {
		JSONArray array = new JSONArray();
		for (UserEntity user : users) {
			JSONObject object = buildJsonFromUser(user);
			array.add(object);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromUser(UserEntity user) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(UserQueryConstant.USERID, user.getUserid());
		jsonObject.put(UserQueryConstant.FIRST_NAME, user.getFirstName());
		jsonObject.put(UserQueryConstant.LAST_NAME, user.getLastName());
		jsonObject.put(UserQueryConstant.EMAIL, user.getEmail());
		jsonObject.put(UserQueryConstant.MOBILE_NUMBER, user.getMobileNumber());
		jsonObject.put(UserQueryConstant.DOMAIN, user.getDomain());
		jsonObject.put(UserQueryConstant.ASSIGNEDNAME, user.getAssignedName());
		jsonObject.put(UserQueryConstant.ROLENAME, user.getRoleName());
		jsonObject.put(UserQueryConstant.STATUS, user.getUserStatus());
		jsonObject.put(UserQueryConstant.CREATEDON, user.getCreatedOn());

		return jsonObject;

	}

	@SuppressWarnings("unchecked")
	@Override
	public UserEntity updateUser(UserEntity user, String userid) throws UserModelServiceException {
		try {
			String dataBaseName = UserServiceImpl.dataBaseName();
			if (userid == null || userid.isEmpty()) {
				System.out.println("user id can't be null or empty ");

			}
			UserEntity updatedUser = this.userdao.updateUser(user, userid, dataBaseName);
			JSONObject object = new JSONObject();
			JSONObject jsonFromUser = buildJsonFromUpdatedUser(updatedUser);
			object.put(updatedUser, jsonFromUser);
			return updatedUser;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserModelServiceException("unable to update user" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromUpdatedUser(UserEntity updatedUser) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(UserQueryConstant.FIRST_NAME, updatedUser.getFirstName());
		jsonObject.put(UserQueryConstant.LAST_NAME, updatedUser.getLastName());
		jsonObject.put(UserQueryConstant.EMAIL, updatedUser.getEmail());
		jsonObject.put(UserQueryConstant.MOBILE_NUMBER, updatedUser.getMobileNumber());
		jsonObject.put(UserQueryConstant.DOMAIN, updatedUser.getDomain());
		jsonObject.put(UserQueryConstant.ASSIGNEDNAME, updatedUser.getAssignedName());
		jsonObject.put(UserQueryConstant.ROLENAME, updatedUser.getRoleName());
		jsonObject.put(UserQueryConstant.STATUS, updatedUser.getUserStatus());
		jsonObject.put(UserQueryConstant.CREATEDON, updatedUser.getCreatedOn());
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserEntity deleteUser(String userid) throws UserModelServiceException {
		try {
			String dataBaseName = UserServiceImpl.dataBaseName();
			if (userid == null || userid.isEmpty()) {
				System.out.println("userId cannot be null or empty");

				// throw new UserServiceException("userId cannot be null or empty ");
			}
			UserEntity user = userdao.getUserById(userid, dataBaseName);
			if (user == null) {

				// throw new UserServiceException("user not found");
			}
			this.userdao.deleteUser(userid, dataBaseName);
			JSONObject reposneJSON = new JSONObject();
			reposneJSON.put(STATUS, SUCCESS);
			reposneJSON.put(MSG, "User deleted suucessfully");
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserModelServiceException("unable to delete user" + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserEntity> getUserbyRolename(String roleName) {

		String dataBaseName = UserServiceImpl.dataBaseName();
		if (roleName == null || roleName.isEmpty()) {
			System.out.println("roleName cannot be null or empty");

			// throw new Exception("userId cannot be null or empty ");
		}
		ArrayList<UserEntity> user = this.userdao.getUserbyRoleName(roleName, dataBaseName);
		JSONObject object = new JSONObject();
		JSONArray userJsonData = getJSONFromUserList(user);
		object.put(user, userJsonData);
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) throws UserModelServiceException {
		try {
			String dataBaseName = UserServiceImpl.dataBaseName();
			ArrayList<String> emailList = new ArrayList<>();
			if (email == null || email.isEmpty()) {
				System.out.println("email cannot be null or empty");
				throw new Exception("userId cannot be null or empty ");
			}
			List<UserEntity> allUsers = this.userdao.getAllUsers(dataBaseName);
			for (UserEntity userEntity : allUsers) {
				emailList.add(userEntity.getEmail());

			}
			System.out.println("list" + emailList);
			if (emailList.contains(email)) {
				UserEntity user = this.userdao.getUserByEmail(email, dataBaseName);
				return user;
			} else {
				throw new Exception("email id not found ");
			}
		} catch (Exception e) {
			throw new UserModelServiceException("unable to get user details by email id" + e.getMessage());
		}
	}
}
