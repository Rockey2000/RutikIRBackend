package com.Anemoi.InvestorRelation.AnalystLineItem;

import java.util.ArrayList;
import java.util.List;

public interface AnalystLineItemService {
	

	AnalystLineItemEntity createAnalystLineItem(AnalystLineItemEntity analystlineItem) throws AnalystLineItemServiceException;
	
	AnalystLineItemEntity getAnalystLineItemById(String aid) throws AnalystLineItemServiceException;
	
	List<AnalystLineItemEntity> getAllAnalystLineItemDetails() throws AnalystLineItemServiceException;

	ArrayList<AnalystLineItemEntity> getbyAnalystName(String analystName, String analystTableHeaderName) throws AnalystLineItemServiceException;
	
	String updateAnalystLineItem(String lineItemName,String analystName, String analystLineItemName) throws AnalystLineItemServiceException;
	
}
