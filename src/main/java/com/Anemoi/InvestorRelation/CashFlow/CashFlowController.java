package com.Anemoi.InvestorRelation.CashFlow;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

@Controller("/investor/cashFlow")
public class CashFlowController {
	
		@Inject
		private CashFlowService service;
		
		@Post("/add")
	  public HttpResponse<CashFlowEntity> addCashFlowDetails(@Body CashFlowEntity cashFlowEntity) throws CashFlowControllerException
	  {     try
	         {
			CashFlowEntity newCashFlow=this.service.createCashFlow(cashFlowEntity);
			return HttpResponse.status(HttpStatus.OK).body(newCashFlow);
	          }
	  catch (Exception e) {
		// TODO: handle exception
		  e.printStackTrace();
		  throw new CashFlowControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
	    
	  }
	
	}

		@Get("/{cashid}")
		public HttpResponse<CashFlowEntity> getDataById(@PathVariable("cashid") String cashid) throws CashFlowControllerException
		{
			try
			{
				CashFlowEntity cashFlowEntity=this.service.getCashFlowById(cashid);
				return HttpResponse.status(HttpStatus.OK).body(cashFlowEntity);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new CashFlowControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
				
			}
		
		}
		
		@Get("/list")
		public List<CashFlowEntity> getDetails() throws CashFlowControllerException
		{ try {
			List<CashFlowEntity> cashflowData=this.service.getAllCashFlowDetails();
			return cashflowData;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CashFlowControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
		}
		
		@Patch("/{cashid}")
		public HttpResponse<CashFlowEntity> updateCashFlow(@Body CashFlowEntity cashflow,@PathVariable("cashid") String cashid) throws CashFlowControllerException
		{
			try
			{
			CashFlowEntity updatedcashflow=this.service.updateCashFlow(cashflow, cashid);
			return HttpResponse.status(HttpStatus.OK).body(updatedcashflow);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new CashFlowControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
			}
			
			
		}
		
		@Delete("/{cashid}")
		public HttpResponse<CashFlowEntity> deleteCashFLow(@PathVariable("cashid") String cashid) throws CashFlowControllerException
		{ 
			try
			{
			CashFlowEntity response=this.service.deleteCashFlow(cashid);
			return HttpResponse.status(HttpStatus.OK).body(response);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new CashFlowControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
			}
		
		}
		
		@Post("/addObject")
		public ArrayList<CashFlowEntity> addCashFlowObject(@Body ArrayList<CashFlowEntity> cashFlowEntity)
		{    
			try {
				
			
			ArrayList<CashFlowEntity> entity=this.service.addCashFlowObject(cashFlowEntity);
			return entity;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}


}
