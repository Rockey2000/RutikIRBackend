package com.Anemoi.InvestorRelation.AnalystLineItem;

import java.util.ArrayList;
import java.util.List;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/investor/analyst")
public class AnalystLineItemController {

	@Inject
	private AnalystLineItemService analystlineitemservice;

	@Post("/add")
	public HttpResponse<AnalystLineItemEntity> addAnalystLineItemDetails(@Body AnalystLineItemEntity analystlineitem) throws AnalystLineItemControllerException {
		try {
			AnalystLineItemEntity analyst= this.analystlineitemservice.createAnalystLineItem(analystlineitem);
			return HttpResponse.status(HttpStatus.OK).body(analyst);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AnalystLineItemControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
		}
	
	}

	@Get("/{aid}")
	public HttpResponse<AnalystLineItemEntity> getAnalystLineItemById(@PathVariable("aid") String aid)  throws AnalystLineItemControllerException{
		try {
			AnalystLineItemEntity analystlineitem = this.analystlineitemservice.getAnalystLineItemById(aid);
			return HttpResponse.status(HttpStatus.OK).body(analystlineitem);
			
		} catch (Exception e) {
			e.printStackTrace();
			
         throw new AnalystLineItemControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
	}

	@Get("/list")
	public List<AnalystLineItemEntity> getAnalystLineItem() throws AnalystLineItemControllerException {
		try {
			List<AnalystLineItemEntity> analystlineitem = this.analystlineitemservice.getAllAnalystLineItemDetails();
			return analystlineitem;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AnalystLineItemControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}

	@Get("/{analystName}/{masterTableSource}")
	public ArrayList<AnalystLineItemEntity> getbyAnalystName(@PathVariable("analystName") String analystName,
			@PathVariable("masterTableSource") String masterTableSource) throws AnalystLineItemControllerException {
		try {
			ArrayList<AnalystLineItemEntity> analystlineitemlist = this.analystlineitemservice
					.getbyAnalystName(analystName, masterTableSource);
			return analystlineitemlist;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AnalystLineItemControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	}
	
	@Patch("/{lineItemName}/{analystName}/{analystLineItemName}")
		public HttpResponse<String> updateAnalystLineItem(@PathVariable("lineItemName") String lineItemName,@PathVariable("analystName") String analystName ,@PathVariable("analystLineItemName") String analystLineItemName) throws AnalystLineItemControllerException
		{
		try {
		String response=this.analystlineitemservice.updateAnalystLineItem(lineItemName,analystName, analystLineItemName);
		return HttpResponse.status(HttpStatus.OK).body(response);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new AnalystLineItemControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		}

	}

