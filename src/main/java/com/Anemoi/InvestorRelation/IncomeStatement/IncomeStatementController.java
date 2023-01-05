package com.Anemoi.InvestorRelation.IncomeStatement;

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

@Controller("/investor/incomestatement")
public class IncomeStatementController {

	@Inject
	private IncomeStatementService service;

	@Post("/add")
	public HttpResponse<IncomeStatementEntity> addIncomeStatementDetails(@Body IncomeStatementEntity incomestatementEntity) throws SQLException,IncomeStatementControllerException {
		try {
			IncomeStatementEntity incomestatement = this.service.createIncomeStatement(incomestatementEntity);
			return HttpResponse.status(HttpStatus.OK).body(incomestatement);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new IncomeStatementControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());

		}
	
	}

	@Get("/{incomeid}")
	public HttpResponse<IncomeStatementEntity> getDataById(@PathVariable("incomeid") String incomeid) throws IncomeStatementControllerException {
		try {
			IncomeStatementEntity incomestatement = this.service.getIncomeStatementById(incomeid);
			return HttpResponse.status(HttpStatus.OK).body(incomestatement);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncomeStatementControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
	}

	@Get("/list")
	public List<IncomeStatementEntity> getDetails() throws SQLException,IncomeStatementControllerException {
		try {
			List<IncomeStatementEntity> incomestatement = this.service.getAllIncomeStatementDetails();
			return incomestatement;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new IncomeStatementControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}

	@Patch("/{incomeid}")
	public HttpResponse<IncomeStatementEntity> updaterIncomeStatement(@Body IncomeStatementEntity incomestatementEntity,
			@PathVariable("incomeid") String incomeid) throws IncomeStatementControllerException {
		try {
			IncomeStatementEntity incomestatement = this.service.updateIncomeStatement(incomestatementEntity, incomeid);
			return HttpResponse.status(HttpStatus.OK).body(incomestatement);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new IncomeStatementControllerException(ReadPropertiesFile.readResponseProperty("403"), e,406,e.getMessage());
			
		}
	

	}

	@Delete("/{incomeid}")
	public HttpResponse<IncomeStatementEntity> deleteIncomeStatement(@PathVariable("incomeid") String incomeid) throws IncomeStatementControllerException {
		try {
			IncomeStatementEntity response = this.service.deleteIncomeStatement(incomeid);
			return HttpResponse.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new IncomeStatementControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}
	
	 @Consumes(value = { "application/json"})
	 @Produces(value = { "application/json"})
	@Post("/addobject")
	public ArrayList<IncomeStatementEntity> addIncomeStatementObject(@Body ArrayList<IncomeStatementEntity> incomeentity) throws Exception
	{
		System.out.println("vahs" +incomeentity.toString() );
		 
		return this.service.addIncomestatmentObject(incomeentity);
		
      
		
	}

}
