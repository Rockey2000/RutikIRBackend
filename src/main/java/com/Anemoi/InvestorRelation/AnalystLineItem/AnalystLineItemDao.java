package com.Anemoi.InvestorRelation.AnalystLineItem;

import java.util.ArrayList;
import java.util.List;

public interface AnalystLineItemDao {
	
	AnalystLineItemEntity createAnalystLineItem(AnalystLineItemEntity lineItem,String dataBaseName) throws AnalystLineItemDaoException;
	
	AnalystLineItemEntity getAnalystLineItemById(String aid,String dataBaseName) throws AnalystLineItemDaoException;
	
	List<AnalystLineItemEntity> getAllAnalystLineItem(String dataBaseName) throws AnalystLineItemDaoException;

	ArrayList<AnalystLineItemEntity> getByanalystnameandTable(String analystName, String analystTableHeaderName,
			String dataBaseName) throws AnalystLineItemDaoException;
	
String updateAnalystlineItem(String lineItemName,String analystName, String analystLineItemName,
		String dataBaseName)   throws AnalystLineItemDaoException;


}
