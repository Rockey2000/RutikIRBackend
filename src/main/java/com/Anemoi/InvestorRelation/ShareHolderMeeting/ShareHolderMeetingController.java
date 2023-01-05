package com.Anemoi.InvestorRelation.ShareHolderMeeting;




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

@Controller("/investor/shareholdermeeting")
public class ShareHolderMeetingController {
	
	@Inject
	private ShareHolderMeetingService service;
	
	
	@Post("/add")
	  public HttpResponse<ShareHolderMeetingEntity> addShareholderDataformDetails(@Body ShareHolderMeetingEntity shareholdermeetingEntity) throws SQLException,ShareHolderMeetingControllerExcetion
	  {     try
	         {
			ShareHolderMeetingEntity newshareholdermeeting=this.service.createShareHolderMeeting(shareholdermeetingEntity);
			return HttpResponse.status(HttpStatus.OK).body(newshareholdermeeting);
	          }
	  catch (Exception e) {
		// TODO: handle exception
		  e.printStackTrace();
		  throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
	    
	  }
	
	}

		@Get("/{holderid}")
		public HttpResponse<ShareHolderMeetingEntity> getDataById(@PathVariable("holderid") String holderid) throws ShareHolderMeetingControllerExcetion
		{
			try
			{
				ShareHolderMeetingEntity shareholderEntity=this.service.getShareHolderMeetingById(holderid);
				return HttpResponse.status(HttpStatus.OK).body(shareholderEntity);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
			}
		
		}
		
		@Get("/list")
		public List<ShareHolderMeetingEntity> getDetails() throws SQLException,ShareHolderMeetingControllerExcetion
		{ try {
			List<ShareHolderMeetingEntity> shareholderData=this.service.getShareHolderMeetingDetails();
			return shareholderData;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
		}
		
		@Patch("/{holderid}")
		public HttpResponse<ShareHolderMeetingEntity> updateShareholder(@Body ShareHolderMeetingEntity shareholdermeetingEntity,@PathVariable("holderid") String holderid) throws ShareHolderMeetingControllerExcetion
		{
			try
			{
			ShareHolderMeetingEntity updatedshareholder=this.service.updateShareHolderMeeting(shareholdermeetingEntity, holderid);
			return HttpResponse.status(HttpStatus.OK).body(updatedshareholder);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
			}
		
			
		}
		
		@Delete("/{holderid}")
		public HttpResponse<ShareHolderMeetingEntity> deleteShareholder(@PathVariable("holderid") String holderid) throws ShareHolderMeetingControllerExcetion
		{ 
			try
			{
			ShareHolderMeetingEntity response=this.service.deleteShareHoderMeeting(holderid);
			return HttpResponse.status(HttpStatus.OK).body(response);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ShareHolderMeetingControllerExcetion(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
			}
			
		}


	
	

}
