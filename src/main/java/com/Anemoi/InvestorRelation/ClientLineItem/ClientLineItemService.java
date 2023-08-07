package com.Anemoi.InvestorRelation.ClientLineItem;

import java.util.ArrayList;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface ClientLineItemService {

	ClientLineItemEntity createClientLineItem(ClientLineItemEntity clientLineitem)
			throws ClientLineItemServiceException;

	ClientLineItemEntity getClientLiteItemById(String clientLineId) throws ClientLineItemServiceException;

	ArrayList<ClientLineItemEntity> getclientlineItemList() throws ClientLineItemServiceException;

	ArrayList<ClientLineItemEntity> addMultipleObjectClientLineItem(ArrayList<ClientLineItemEntity> clientLineItem)
			throws ClientLineItemServiceException;

	ArrayList<ClientLineItemEntity> getByClientName(String clientName, String masterTableSource)
			throws ClientLineItemServiceException;

	String updateLineItemName(ClientDetails clientDetails) throws ClientLineItemServiceException;

	ArrayList<ClientLineItemEntity> uploadClientLineItemExcelSheet(CompletedFileUpload file) throws ClientLineItemServiceException;

	ClientLineItemEntity updateClientLineItem(ClientLineItemEntity clientLineItem, String clientLineId)
			throws ClientLineItemServiceException;

}
