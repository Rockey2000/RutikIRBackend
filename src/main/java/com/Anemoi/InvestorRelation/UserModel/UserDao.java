package com.Anemoi.InvestorRelation.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {

	UserEntity createNewUser(UserEntity user, String dataBaseName) throws UserModelDaoException;

	UserEntity getUserById(String userid, String dataBaseName) throws UserModelDaoException;

	List<UserEntity> getAllUsers(String dataBaseName) throws UserModelDaoException;

	UserEntity updateUser(UserEntity user, String userid, String dataBaseName) throws UserModelDaoException;

	String deleteUser(String userid, String dataBaseName) throws UserModelDaoException;

	ArrayList<UserEntity> getUserbyRoleName(String roleName, String dataBaseName);

	UserEntity getUserByEmail(String email, String dataBaseName) throws UserModelDaoException;
}
