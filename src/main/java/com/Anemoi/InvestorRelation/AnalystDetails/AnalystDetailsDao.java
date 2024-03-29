package com.Anemoi.InvestorRelation.AnalystDetails;

import java.sql.SQLException;
import java.util.ArrayList;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface AnalystDetailsDao {

	AnalystDetailsEntity createAnalystDetails(AnalystDetailsEntity analystDetails, String dataBaseName)
			throws AnalystDetailsDaoException;

	AnalystDetailsEntity getAnalystDetailsById(long analystId, String dataBaseName) throws AnalystDetailsDaoException;

	ArrayList<AnalystDetailsEntity> getAllAnalystDetails(String dataBaseName) throws AnalystDetailsDaoException;

	AnalystDetailsEntity updateAnalystDetails(AnalystDetailsEntity analystDetails, long analystId,
			String dataBaseName) throws AnalystDetailsDaoException;

	String deleteAnalystDetails(long analystId, String dataBaseName) throws SQLException, AnalystDetailsDaoException;

	ArrayList<AnalystDetailsEntity> uploadAnalystDetailsExcelSheet(CompletedFileUpload file, String dataBaseName)
			throws AnalystDetailsDaoException, Exception;

	String deleteAnalystContactDetails(long analystId, long analystcontactid, String dataBaseName) throws AnalystDetailsDaoException;

}
