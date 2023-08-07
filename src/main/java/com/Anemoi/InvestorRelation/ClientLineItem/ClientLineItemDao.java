package com.Anemoi.InvestorRelation.ClientLineItem;

import java.util.ArrayList;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface ClientLineItemDao {

	ClientLineItemEntity createClientLineItem(ClientLineItemEntity clientLineitem, String dataBaseName)
			throws ClientLineItemDaoException;

	ClientLineItemEntity getClientLineItemById(String clientLineId, String dataBaseName)
			throws ClientLineItemDaoException;

	ArrayList<ClientLineItemEntity> getclientLineItemListDetails(String dataBaseName) throws ClientLineItemDaoException;

	ArrayList<ClientLineItemEntity> addMultipleObject(ArrayList<ClientLineItemEntity> clientLineItem,
			String dataBaseName) throws ClientLineItemDaoException;

	ArrayList<ClientLineItemEntity> getByClientNameandTable(String clientName, String masterTableSource,
			String dataBaseName) throws ClientLineItemDaoException;

	String updatelineItemName(ClientDetails clientDetails, String dataBaseName) throws ClientLineItemDaoException;

	ArrayList<ClientLineItemEntity> uploadClientLineItem(CompletedFileUpload file, String dataBaseName) throws ClientLineItemDaoException;

	ClientLineItemEntity updateClientLineItem(ClientLineItemEntity clientLineItem, String clientLineId,
			String dataBaseName) throws ClientLineItemDaoException;

}
