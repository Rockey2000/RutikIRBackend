package com.Anemoi.InvestorRelation.ClientDetails;

import java.util.ArrayList;

import com.Anemoi.InvestorRelation.AnalystDetails.AnalystDetailsControllerException;
import com.Anemoi.InvestorRelation.ClientLineItem.ClientLineItemControllerException;
import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("Investor/ClientDetails")
public class ClientDetailsController {

	@Inject
	ClientDetailsService clientDetailsService;

	@Post("/addprojectCode")
	public ClientDetailsEntity addProjectCodeForClient(@Body ClientDetailsEntity clientDetailsEntity) {
		try {
			ClientDetailsEntity response = this.clientDetailsService.addProjectCodeDetails(clientDetailsEntity);
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return clientDetailsEntity;
	}

	@Get("/getCode/{projectCode}/{taskCode}")
	public ClientDetailsEntity getclientDetailsbyProjectCode(@PathVariable("projectCode") int projectCode,
			@PathVariable("taskCode") int taskCode) throws ClientDetailsControllerException {
		try {
			ClientDetailsEntity response = this.clientDetailsService.getClientDetailsByProjectCode(projectCode,
					taskCode);
			return response;
		} catch (Exception e) {
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	
	}

	@Post("/add")
	public ClientDetailsEntity addClientDetails(@Body ClientDetailsEntity detailsEntity)  throws ClientDetailsControllerException{
		try {
			System.out.println("detailsEntity"+detailsEntity.getSuggestedPeers());
			ClientDetailsEntity response = this.clientDetailsService.addClientDetails(detailsEntity);
			return response;

		} catch (Exception e) {
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	
	}

	@Get("/list")
	public ArrayList<ClientDetailsEntity> getAllClientDetails() throws ClientDetailsControllerException {
		try {
			ArrayList<ClientDetailsEntity> responseList = this.clientDetailsService.getAllClientDetails();
			return responseList;
		} catch (Exception e) {
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	
	}
	
	@Get("getClientData/{clientId}")
	public ClientDetailsEntity getClientDataByclientId(@PathVariable("clientId") String clientId) throws ClientDetailsControllerException
	{
		try
		{
			ClientDetailsEntity response=this.clientDetailsService.getClientData(clientId);
			return response;
		}
		catch (Exception e) {
			
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	}
	
	@Patch("updateClientDetails/{clientId}")
	public ClientDetailsEntity updateClientDetails(@PathVariable("clientId") String clientId,@Body ClientDetailsEntity clientDetailsEntity) throws ClientDetailsControllerException 
	{
		try {
		
			ClientDetailsEntity response=this.clientDetailsService.updateClientDetails(clientId,clientDetailsEntity);
			return response;
	
		}
		catch (Exception e) {
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	}
	
	@Delete("deleteClient/{clientId}")
	public String deleteClientDetails(@PathVariable("clientId") String clientId) throws ClientDetailsControllerException
	{
		try
		{
			String response=this.clientDetailsService.deleteClientDetails(clientId);
			return response;
			
		}catch (Exception e) {
			
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	}
	
	@Get("getClientDatabyClientName/{clientName}")
	public ClientDetailsEntity getClientDataByclientName(@PathVariable("clientName") String clientName) throws ClientDetailsControllerException
	{
		try
		{
			ClientDetailsEntity response=this.clientDetailsService.getClientDataByClientName(clientName);
			return response;
		}
		catch (Exception e) {
			
			throw new ClientDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	}
}
