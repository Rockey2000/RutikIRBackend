package com.Anemoi.InvestorRelation.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

	UserEntity createNewUser(UserEntity user) throws SQLException, UserModelServiceException;

	UserEntity getUserById(String userid) throws SQLException, UserModelServiceException;

	List<UserEntity> getAllUsers() throws SQLException, UserModelServiceException;

	UserEntity updateUser(UserEntity user, String userid) throws UserModelServiceException;

	UserEntity deleteUser(String userid) throws UserModelServiceException;

	ArrayList<UserEntity> getUserbyRolename(String roleName);

	UserEntity getUserByEmail(String email) throws UserModelServiceException;

}
