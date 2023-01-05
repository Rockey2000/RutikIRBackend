package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

import java.sql.SQLException;
import java.util.List;

public interface ShareHoderDataFromDao {
	
	
	ShareHolderDataFromEntity createNewShareHolderDataForm(ShareHolderDataFromEntity shareholderdataform,String dataBaseName) throws ShareHolderDataFormDaoException;
	
	ShareHolderDataFromEntity getShareHolderDataFormById(String shareid,String dataBaseName) throws ShareHolderDataFormDaoException;

	List<ShareHolderDataFromEntity> getAllShareHolderDataFormDetails(String dataBaseName) throws SQLException,ShareHolderDataFormDaoException;

	ShareHolderDataFromEntity updateShareHolderDAtaFormDetails(ShareHolderDataFromEntity shareholderdataform, String shareid,String dataBaseName);

	String deleteShareHolderDataForm(String shareid,String dataBaseName) throws SQLException;


}
