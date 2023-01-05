package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

import java.sql.SQLException;
import java.util.List;

public interface ShareHoderDataFromService {
	
	ShareHolderDataFromEntity CreateShareHoderDataForm(ShareHolderDataFromEntity sharehoderdataform) throws SQLException,ShareHolderDataFromServiceException;
	 
	ShareHolderDataFromEntity getShareHolderDataFormById(String shareid) throws SQLException,ShareHolderDataFromServiceException;
	
	List<ShareHolderDataFromEntity> getShareHolderDataFormDetails() throws SQLException,ShareHolderDataFromServiceException;
	
	ShareHolderDataFromEntity updateShareHolderDataForm(ShareHolderDataFromEntity sharehoderdataform, String shareid) throws ShareHolderDataFromServiceException;
	
	ShareHolderDataFromEntity deleteShareHoderDataForm(String shareid) throws ShareHolderDataFromServiceException;


}
