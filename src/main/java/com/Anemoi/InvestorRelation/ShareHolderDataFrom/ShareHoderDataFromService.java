package com.Anemoi.InvestorRelation.ShareHolderDataFrom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface ShareHoderDataFromService {

	ShareHolderDataFromEntity CreateShareHoderDataForm(ShareHolderDataFromEntity sharehoderdataform)
			throws SQLException, ShareHolderDataFromServiceException;

	ShareHolderDataFromEntity getShareHolderDataFormById(String shareid)
			throws SQLException, ShareHolderDataFromServiceException;

	List<ShareHolderDataFromEntity> getShareHolderDataFormDetails()
			throws SQLException, ShareHolderDataFromServiceException;

	ShareHolderDataFromEntity updateShareHolderDataForm(ShareHolderDataFromEntity sharehoderdataform, String shareid)
			throws ShareHolderDataFromServiceException;

	ShareHolderDataFromEntity deleteShareHoderDataForm(String shareid) throws ShareHolderDataFromServiceException;

	ArrayList<ShareHolderDataFromEntity> uploadShareHolderDataExcelSheet(CompletedFileUpload file) throws ShareHolderDataFromServiceException;

}
