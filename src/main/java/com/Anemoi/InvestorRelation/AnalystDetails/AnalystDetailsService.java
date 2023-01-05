package com.Anemoi.InvestorRelation.AnalystDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnalystDetailsService {
	
	public AnalystDetailsEntity createAnalystDetails(AnalystDetailsEntity analystdetails) throws AnalystDetailsServiceException;
	
	public AnalystDetailsEntity getAnalystDetailsById(String analystId) throws AnalystDetailsServiceException;
	
	public ArrayList<AnalystDetailsEntity> getAllAnalystDetails() throws AnalystDetailsServiceException;
	
	public AnalystDetailsEntity updateAnalystDetails(AnalystDetailsEntity analystDetails,String analystId) throws AnalystDetailsServiceException;
	
	public AnalystDetailsEntity deleteAnalystDetails(String analystId);
	
	

}
