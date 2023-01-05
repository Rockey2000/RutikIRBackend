 package com.Anemoi.InvestorRelation.BalanceSheet;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

@Controller("/investor/balanceSheet")
public class BalanceSheetController {
	
	@Inject
	private BalanceSheetService service;
	
	@Post("/add")
  public HttpResponse<BalanceSheetEntity> addBalanceSheetDetails(@Body BalanceSheetEntity balanceEntity) throws BalanceSheetControllerException
  {     try
         {
	  BalanceSheetEntity balnacesheet	=this.service.createNewBalanceSheetForm(balanceEntity);
	  return HttpResponse.status(HttpStatus.OK).body(balnacesheet);
	
          }
  catch (Exception e) {
	// TODO: handle exception
	  e.printStackTrace();
	  throw new BalanceSheetControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
  }
}
	
	@Get("/{balanceid}")
	public HttpResponse<BalanceSheetEntity> getDataById(@PathVariable("balanceid") String balanceid) throws BalanceSheetControllerException
	{
		try
		{
			BalanceSheetEntity balanceEntity=this.service.getBalancesheetById(balanceid);
			return HttpResponse.status(HttpStatus.OK).body(balanceEntity);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BalanceSheetControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}
	
	@Get("/list")
	public List<BalanceSheetEntity> getDetails() throws BalanceSheetControllerException
	{ try {
		List<BalanceSheetEntity> balancesheetData=this.service.getAllBalanceSheetDetails();
		return balancesheetData;
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BalanceSheetControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
	}
	
	}
	
	@Patch("/{incomeid}")
	public HttpResponse<BalanceSheetEntity> updaterBalanceSheet(@Body BalanceSheetEntity balancesheetEntity,
			@PathVariable("incomeid") String incomeid) throws BalanceSheetControllerException {
		try {
			BalanceSheetEntity balancesheet = this.service.updateBalanceSheetForm(balancesheetEntity, incomeid);
			return HttpResponse.status(HttpStatus.OK).body(balancesheet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BalanceSheetControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
		}


	}

	@Delete("/{balanceid}")
	public HttpResponse<BalanceSheetEntity> deleteincomestatement(@PathVariable("balanceid") String balanceid) throws BalanceSheetControllerException {
		try {
			BalanceSheetEntity response = this.service.deleteBalanceSheet(balanceid);
			return HttpResponse.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BalanceSheetControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}
	
     @Consumes(value = { "application/json"})
	 @Produces(value = { "application/json"})
	@Post("/addobject")
	public ArrayList<BalanceSheetEntity> addObjectBalanceSheet(@Body ArrayList<BalanceSheetEntity> balanceSheetEntity) throws BalanceSheetControllerException
	{   
    	 try {
		ArrayList<BalanceSheetEntity> entity=this.service.addObjectBalanceSheetEntity(balanceSheetEntity);
		return entity;
    	 }
    	 catch (Exception e) {
 			// TODO: handle exception
 			e.printStackTrace();
 			throw new BalanceSheetControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
 		}
		
	}

}
