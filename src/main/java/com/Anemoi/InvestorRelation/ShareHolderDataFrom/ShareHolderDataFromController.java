package com.Anemoi.InvestorRelation.ShareHolderDataFrom;


import java.sql.SQLException;
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

@Controller("/investor/shareholderdataform")
public class ShareHolderDataFromController {
	
	@Inject
	private ShareHoderDataFromService shareholderservice;
	
	@Post("/add")
	  public HttpResponse<ShareHolderDataFromEntity> addshareholderdataformDetails(@Body ShareHolderDataFromEntity shareholderEntity) throws SQLException,ShareHolderDataFormControllerException
	  {     try
	         {
			ShareHolderDataFromEntity newshareholder=this.shareholderservice.CreateShareHoderDataForm(shareholderEntity);
			return HttpResponse.status(HttpStatus.OK).body(newshareholder);
	          }
	  catch (Exception e) {
		// TODO: handle exception
		  e.printStackTrace();
		  throw new ShareHolderDataFormControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
	    
	  }
	
	}

		@Get("/{shareid}")
		public HttpResponse<ShareHolderDataFromEntity> getDataById(@PathVariable("shareid") String shareid) throws ShareHolderDataFormControllerException
		{
			try
			{
				ShareHolderDataFromEntity shareholderEntity=this.shareholderservice.getShareHolderDataFormById(shareid);
				return HttpResponse.status(HttpStatus.OK).body(shareholderEntity);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ShareHolderDataFormControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		
		}
}
		
		@Get("/list")
		public List<ShareHolderDataFromEntity> getDetails() throws SQLException,ShareHolderDataFormControllerException
		{ try {
			List<ShareHolderDataFromEntity> shareholderData=this.shareholderservice.getShareHolderDataFormDetails();
			return shareholderData;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ShareHolderDataFormControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
		}
		
		@Patch("/{shareid}")
		public HttpResponse<ShareHolderDataFromEntity> updateshareholder(@Body ShareHolderDataFromEntity shareholderEntity,@PathVariable("shareid") String shareid) throws ShareHolderDataFormControllerException
		{
			try
			{
			ShareHolderDataFromEntity updatedcashflow=this.shareholderservice.updateShareHolderDataForm(shareholderEntity, shareid);
			return HttpResponse.status(HttpStatus.OK).body(updatedcashflow);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ShareHolderDataFormControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
			}
			
			
		}
		
		@Delete("/{shareid}")
		public HttpResponse<ShareHolderDataFromEntity> deleteshareholder(@PathVariable("shareid") String shareid) throws ShareHolderDataFormControllerException
		{ 
			try
			{
			ShareHolderDataFromEntity response=this.shareholderservice.deleteShareHoderDataForm(shareid);
			return HttpResponse.status(HttpStatus.OK).body(response);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ShareHolderDataFormControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
			}
		
		}


	
	
	
}
