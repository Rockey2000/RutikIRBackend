package com.Anemoi.InvestorRelation.ClientDetails;

import java.util.ArrayList;

public interface ClientDetailsDao {

	ClientDetailsEntity addClientDetails(ClientDetailsEntity detailsEntity, String dataBaseName) throws ClientDaoException;

	ArrayList<ClientDetailsEntity> getAllClientDetails(String dataBaseName) throws ClientDaoException;

	ClientDetailsEntity getclientDetailsByprojectCode(int projectCode, int taskCode, String dataBaseName) throws ClientDaoException;

	ClientDetailsEntity addProjectCodeFor(ClientDetailsEntity clientDetailsEntity, String dataBaseName);

	ClientDetailsEntity updateClientDetails(String clientId, ClientDetailsEntity clientDetailsEntity,
			String dataBaseName) throws ClientDaoException;

	void deleteClientDetails(String clientId, String dataBaseName) throws ClientDaoException;

	ClientDetailsEntity getClientDataByclientId(String clientId, String dataBaseName) throws ClientDaoException;

	ClientDetailsEntity getDetailsByClientName(String clientName, String dataBaseName) throws ClientDaoException;

}
