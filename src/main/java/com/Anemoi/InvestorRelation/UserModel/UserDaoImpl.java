package com.Anemoi.InvestorRelation.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;

import jakarta.inject.Singleton;

@Singleton
public class UserDaoImpl implements UserDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public UserEntity createNewUser(UserEntity user, String dataBaseName) throws UserModelDaoException {

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();

			LOGGER.debug("inserting the data");
			pstmt = connection.prepareStatement(UserQueryConstant.INSERT_INTO_USERDETAILS
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			String userId = UUID.randomUUID().toString();
			user.setUserid(userId);
			String id = user.getUserid();
//			user.setPassword(password);
//			String password1 = user.getPassword();

//			System.out.println(id+" "+user);
			Date date = new Date();

			pstmt.setString(1, id);
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getMobileNumber());
			pstmt.setString(6, user.getDomain());
			pstmt.setString(7, user.getAssignedName());
			pstmt.setString(8, user.getRoleName());
			pstmt.setString(9, user.getUserStatus());
			pstmt.setString(10, null);
			pstmt.setLong(11, date.getTime());
			pstmt.setLong(12, date.getTime());

			pstmt.executeUpdate();
			return user;

		} catch (Exception e) {
			LOGGER.error("unable to  created :");
			e.printStackTrace();
			throw new UserModelDaoException("unable to create user" + e.getMessage());

		} finally {
			LOGGER.info("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}

	}

	@Override
	public UserEntity getUserById(String userid, String dataBaseName) throws UserModelDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(UserQueryConstant.SELECT_USER_BY_ID
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, userid);
			result = pstmt.executeQuery();
			while (result.next()) {
				UserEntity user = buildUser(result);
				return user;
			}
		} catch (Exception e) {
			LOGGER.error("User not found" + e.getMessage());
			throw new UserModelDaoException("unable to get user by id" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;

	}

	private UserEntity buildUser(ResultSet result) throws SQLException {
		UserEntity user = new UserEntity();
		user.setUserid(result.getString(UserQueryConstant.USERID));
		user.setFirstName(result.getString(UserQueryConstant.FIRST_NAME));
		user.setLastName(result.getString(UserQueryConstant.LAST_NAME));
		user.setEmail(result.getString(UserQueryConstant.EMAIL));
		user.setMobileNumber(result.getString(UserQueryConstant.MOBILE_NUMBER));
		user.setDomain(result.getString(UserQueryConstant.DOMAIN));
		user.setAssignedName(result.getString(UserQueryConstant.ASSIGNEDNAME));
		user.setRoleName(result.getString(UserQueryConstant.ROLENAME));
		user.setUserStatus(result.getString(UserQueryConstant.STATUS));
		user.setPassword(result.getString(UserQueryConstant.PASSWORD));
		user.setCreatedOn(result.getLong(UserQueryConstant.CREATEDON));
		user.setEditedOn(result.getLong(UserQueryConstant.EDITEDON));

		return user;
	}

	@Override
	public List<UserEntity> getAllUsers(String dataBaseName) throws UserModelDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<UserEntity> listOfUsers = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					UserQueryConstant.SELECT_ALL_USERS.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				UserEntity user = buildUser(result);
				listOfUsers.add(user);
			}
			return listOfUsers;
		} catch (Exception e) {
			LOGGER.error("unble to get list of user" + e.getMessage());
			e.printStackTrace();
			throw new UserModelDaoException("unable to get user list" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}

	}

	@Override
	public UserEntity updateUser(UserEntity user, String userid, String dataBaseName) throws UserModelDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		LOGGER.info(".in update user database name is ::" + dataBaseName + " userId is ::" + userid
				+ " request user is ::" + user);

		try {

			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					UserQueryConstant.UPDATE_USER.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			Date date = new Date();
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getMobileNumber());
			pstmt.setString(5, user.getDomain());
			pstmt.setString(6, user.getAssignedName());
			pstmt.setString(7, user.getRoleName());
			pstmt.setString(8, user.getUserStatus());
			pstmt.setString(9, user.getPassword());
			pstmt.setLong(10, date.getTime());
			pstmt.setString(11, userid);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			LOGGER.info(executeUpdate + " User updated successfully");
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserModelDaoException("unable to update" + e.getMessage());

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}

	}

	@Override
	public String deleteUser(String userid, String dataBaseName) throws UserModelDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(UserQueryConstant.DELETE_USER_BY_ID
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, userid);
			int executeUpdate = pstmt.executeUpdate();
			LOGGER.info(executeUpdate + " user deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(pstmt, connection);
		}
		return null;

	}

	@Override
	public ArrayList<UserEntity> getUserbyRoleName(String roleName, String dataBaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<UserEntity> listOfUsers = new ArrayList<>();
		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(UserQueryConstant.SELECT_USER_BY_ROLENAME
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, roleName);
			result = pstmt.executeQuery();
			while (result.next()) {
				UserEntity user = buildUser(result);
				listOfUsers.add(user);
			}
			return listOfUsers;
		} catch (Exception e) {
			LOGGER.error("unble to get list of user" + e.getMessage());
			e.printStackTrace();

		} finally {
			LOGGER.debug("closing the connections");
			InvestorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;

	}

	@Override
	public UserEntity getUserByEmail(String email, String dataBaseName) throws UserModelDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = InvestorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(UserQueryConstant.SELECT_USERDETAILS_BYEMAILID
					.replace(UserQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, email);
			result = pstmt.executeQuery();
			while (result.next()) {
				UserEntity user = buildUser(result);
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new UserModelDaoException("unable to get user details by email id");
		}
		return null;
	}

}
