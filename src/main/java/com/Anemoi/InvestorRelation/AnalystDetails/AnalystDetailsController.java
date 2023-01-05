package com.Anemoi.InvestorRelation.AnalystDetails;

import java.util.ArrayList;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/investor/analystDetails")
public class AnalystDetailsController {
	
	@Inject
	AnalystDetailsService service;
	 
	
	@Post("/add")
	public HttpResponse<AnalystDetailsEntity> createAnalystDetails(@Body AnalystDetailsEntity entity) throws AnalystDetailsControllerException
	{
		try
		{
			AnalystDetailsEntity analystentity=this.service.createAnalystDetails(entity);
			return HttpResponse.status(HttpStatus.OK).body(analystentity);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AnalystDetailsControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
		}
		
		
	}
	
	@Get("/{analystId}")
	public HttpResponse<AnalystDetailsEntity> getAnalystDetails(@PathVariable("analystId") String analystId) throws AnalystDetailsControllerException
	{
		try
		{
			AnalystDetailsEntity analystEntity=this.service.getAnalystDetailsById(analystId);
			return HttpResponse.status(HttpStatus.OK).body(analystEntity);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new AnalystDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
	}
	
	@Get("/list")
	public ArrayList<AnalystDetailsEntity> getAllAnalstDetails() throws AnalystDetailsControllerException
	{
		try
		{
			ArrayList<AnalystDetailsEntity> entity=this.service.getAllAnalystDetails();
		   return entity;	
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new AnalystDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}
	
	@Patch("/{analystId}")
	public HttpResponse<AnalystDetailsEntity> updateAnalystDetails(@Body AnalystDetailsEntity analystEntity,@PathVariable("analystId") String analystId) throws AnalystDetailsControllerException
	{  try {
		AnalystDetailsEntity analyst=this.service.updateAnalystDetails(analystEntity, analystId);
		return HttpResponse.status(HttpStatus.OK).body(analyst);
	}
	catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		throw new AnalystDetailsControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
	}
	
	}
	
	@Delete("/{analystId}")
	public HttpResponse<AnalystDetailsEntity> deleteAnalystDetails(@PathVariable("analystId") String analystId) throws AnalystDetailsControllerException
	{
		try
		{
			AnalystDetailsEntity analyst=this.service.deleteAnalystDetails(analystId);
			return HttpResponse.status(HttpStatus.OK).body(analyst);
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new AnalystDetailsControllerException(ReadPropertiesFile.readResponseProperty("101"),e,400,e.getMessage());
		}
		
	}
}
