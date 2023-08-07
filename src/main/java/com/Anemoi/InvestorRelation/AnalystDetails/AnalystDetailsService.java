package com.Anemoi.InvestorRelation.AnalystDetails;

import java.sql.SQLException;
import java.util.ArrayList;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface AnalystDetailsService {

	public AnalystDetailsEntity createAnalystDetails(AnalystDetailsEntity analystdetails)
			throws AnalystDetailsServiceException;

	public AnalystDetailsEntity getAnalystDetailsById(long analystId ) throws AnalystDetailsServiceException;

	public ArrayList<AnalystDetailsEntity> getAllAnalystDetails() throws AnalystDetailsServiceException;

	public AnalystDetailsEntity updateAnalystDetails(AnalystDetailsEntity analystDetails, long analystId)
			throws AnalystDetailsServiceException;

	public AnalystDetailsEntity deleteAnalystDetails(long analystId);

	public ArrayList<AnalystDetailsEntity> uploadAnalstDetailsExcelSheet(CompletedFileUpload file) throws AnalystDetailsServiceException, Exception;

	public String deleteAnalystContactDetails(long analystId, long analystcontactid) throws AnalystDetailsServiceException;

}
