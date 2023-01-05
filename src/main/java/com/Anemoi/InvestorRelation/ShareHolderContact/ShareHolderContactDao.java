package com.Anemoi.InvestorRelation.ShareHolderContact;

import java.sql.SQLException;
import java.util.List;


public interface ShareHolderContactDao {
	
ShareHolderContactEntity createNewShareHolderContact(ShareHolderContactEntity shareholdercontact,String dataBaseName) throws ShareHolderContactDaoException;
	
ShareHolderContactEntity getShareHolderContactById(String contactid,String dataBaseName) throws ShareHolderContactDaoException;

	List<ShareHolderContactEntity> getAllShareHolderContactDetails(String dataBaseName) throws SQLException,ShareHolderContactDaoException;

	ShareHolderContactEntity updateShareHolderContactDetails(ShareHolderContactEntity shareholdercontact, String contactid,String dataBaseName) throws ShareHolderContactDaoException;

	String deleteShareHolderContact(String contactid,String dataBaseName)throws SQLException;

}
