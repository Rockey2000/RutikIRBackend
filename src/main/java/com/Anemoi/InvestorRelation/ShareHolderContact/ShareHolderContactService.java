package com.Anemoi.InvestorRelation.ShareHolderContact;

import java.sql.SQLException;
import java.util.List;

public interface ShareHolderContactService {

	ShareHolderContactEntity createShareHolderContact(ShareHolderContactEntity shareholdercontact)
			throws SQLException, ShareHolderContactServiceException;

	ShareHolderContactEntity getShareHolderContactById(String contactid)
			throws SQLException, ShareHolderContactServiceException;

	List<ShareHolderContactEntity> getAllShareholderContactDetails()
			throws SQLException, ShareHolderContactServiceException;

	ShareHolderContactEntity updateShareHolderContact(ShareHolderContactEntity shareholdercontact, String contactid)
			throws ShareHolderContactServiceException;

	ShareHolderContactEntity deleteShareHolderContact(String contactid) throws ShareHolderContactServiceException;

}
