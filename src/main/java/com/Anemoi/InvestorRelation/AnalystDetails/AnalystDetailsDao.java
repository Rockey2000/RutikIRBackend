package com.Anemoi.InvestorRelation.AnalystDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnalystDetailsDao {
	
	AnalystDetailsEntity createAnalystDetails(AnalystDetailsEntity analystDetails,String dataBaseName) throws AnalystDetailsDaoException;
	
	AnalystDetailsEntity getAnalystDetailsById(String analystId,String dataBaseName) throws AnalystDetailsDaoException;
	
	ArrayList<AnalystDetailsEntity> getAllAnalystDetails(String dataBaseName) throws AnalystDetailsDaoException;
	
	AnalystDetailsEntity updateAnalystDetails(AnalystDetailsEntity analystDetails,String analystId,String dataBaseName) throws AnalystDetailsDaoException;
	
	String deleteAnalystDetails(String analystId,String dataBaseName) throws SQLException,AnalystDetailsDaoException;

}

