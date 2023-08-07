package com.Anemoi.InvestorRelation.ClientDetails;

import java.util.ArrayList;

public interface ClientDetailsService {

	ClientDetailsEntity addClientDetails(ClientDetailsEntity detailsEntity) throws ClientServiceException;

	ArrayList<ClientDetailsEntity> getAllClientDetails() throws ClientServiceException;

	ClientDetailsEntity getClientDetailsByProjectCode(int projectCode, int taskCode) throws ClientServiceException;

	ClientDetailsEntity addProjectCodeDetails(ClientDetailsEntity clientDetailsEntity);

	ClientDetailsEntity updateClientDetails(String clientId, ClientDetailsEntity clientDetailsEntity) throws ClientServiceException;

	String deleteClientDetails(String clientId) throws ClientServiceException;

	ClientDetailsEntity getClientData(String clientId) throws ClientServiceException;

	ClientDetailsEntity getClientDataByClientName(String clientName) throws ClientServiceException;

}
