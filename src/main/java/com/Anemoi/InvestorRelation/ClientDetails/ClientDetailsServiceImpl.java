package com.Anemoi.InvestorRelation.ClientDetails;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Inject
	ClientDetailsDao clientDetailsDao;

	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";
	private static String DATABASENAME = "databasename";

	private static String dataBaseName() {
		List<String> tenentList = ReadPropertiesFile.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = ReadPropertiesFile.dataBaseName(tenent);
		}
		return DATABASENAME;
	}

	@Override
	public ClientDetailsEntity addClientDetails(ClientDetailsEntity detailsEntity) throws ClientServiceException {

		try {
			String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
			ClientDetailsEntity response = this.clientDetailsDao.addClientDetails(detailsEntity, dataBaseName);
			return response;
		} catch (Exception e) {

		throw new ClientServiceException(e.getMessage());
		}
	
	}

	@Override
	public ArrayList<ClientDetailsEntity> getAllClientDetails() throws ClientServiceException {
		try {
			String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
			ArrayList<ClientDetailsEntity> responseList = this.clientDetailsDao.getAllClientDetails(dataBaseName);
			return responseList;
		} catch (Exception e) {
			throw new ClientServiceException(e.getMessage());
		}

	}

	@Override
	public ClientDetailsEntity getClientDetailsByProjectCode(int projectCode, int taskCode) throws ClientServiceException{

		try {
			String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
			ClientDetailsEntity response = this.clientDetailsDao.getclientDetailsByprojectCode(projectCode, taskCode,
					dataBaseName);
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			throw new ClientServiceException(e.getMessage());
		}
	
	}

	@Override
	public ClientDetailsEntity addProjectCodeDetails(ClientDetailsEntity clientDetailsEntity) {

		try {
			String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
			ClientDetailsEntity response = this.clientDetailsDao.addProjectCodeFor(clientDetailsEntity, dataBaseName);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ClientDetailsEntity updateClientDetails(String clientId, ClientDetailsEntity clientDetailsEntity)
			throws ClientServiceException {
		  try
		  {
			  String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
			  ClientDetailsEntity response=this.clientDetailsDao.updateClientDetails(clientId,clientDetailsEntity,dataBaseName);
			  return response;
		  }
		  catch (Exception e) {
			  throw new ClientServiceException(e.getMessage());
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteClientDetails(String clientId) throws ClientServiceException {
		 try
		  {
			  String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
			  this.clientDetailsDao.deleteClientDetails(clientId,dataBaseName);
			  JSONObject reposneJSON = new JSONObject();
				reposneJSON.put(STATUS, SUCCESS);
				reposneJSON.put(MSG, "client details deleted suucessfully");
				return reposneJSON.toString();
		  }
		  catch (Exception e) {
			  throw new ClientServiceException(e.getMessage());
			// TODO: handle exception
		}
	}

	@Override
	public ClientDetailsEntity getClientData(String clientId) throws ClientServiceException {
            try
            {
		String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
		ClientDetailsEntity response=this.clientDetailsDao.getClientDataByclientId(clientId,dataBaseName);
		return response;
            }
            catch (Exception e) {
            	
            	 throw new ClientServiceException(e.getMessage());
			}
	}

	@Override
	public ClientDetailsEntity getClientDataByClientName(String clientName) throws ClientServiceException {

            try
            {
            	String dataBaseName = ClientDetailsServiceImpl.dataBaseName();
            	ClientDetailsEntity reponse=this.clientDetailsDao.getDetailsByClientName(clientName,dataBaseName);
            	return reponse;
            }
            catch (Exception e) {
            	 throw new ClientServiceException(e.getMessage());
			}
	}

}
