package com.Anemoi.InvestorRelation.AnalystLineItem;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface AnalystLineItemService {

	AnalystLineItemEntity createAnalystLineItem(AnalystLineItemEntity analystlineItem)
			throws AnalystLineItemServiceException;

	AnalystLineItemEntity getAnalystLineItemById(String aid) throws AnalystLineItemServiceException;

	List<AnalystLineItemEntity> getAllAnalystLineItemDetails() throws AnalystLineItemServiceException;

	ArrayList<AnalystLineItemEntity> getbyAnalystName(String analystName, String analystTableHeaderName)
			throws AnalystLineItemServiceException;

	String updateAnalystLineItem(AnalystDetails analystDetails) throws AnalystLineItemServiceException;

	ArrayList<AnalystLineItemEntity> uploadAnalstLineItemExcelSheet(CompletedFileUpload file) throws AnalystLineItemServiceException;

	ArrayList<AnalystLineItemEntity> addMultipleObject(ArrayList<AnalystLineItemEntity> analystLineItem)
			throws AnalystLineItemServiceException;

	AnalystLineItemEntity updateAnalystLineItem(AnalystLineItemEntity analystLineItem, String analystLineId)
			throws AnalystLineItemServiceException;

}
