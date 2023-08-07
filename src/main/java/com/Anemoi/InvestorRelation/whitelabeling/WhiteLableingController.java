package com.Anemoi.InvestorRelation.whitelabeling;


import java.util.ArrayList;
import java.util.UUID;

import com.Anemoi.InvestorRelation.ClientDetails.ClientDetailsControllerException;
import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;
import com.Anemoi.InvestorRelation.ShareHolderMeeting.MediaServiceInterface;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;

@Controller("/investor/whitelableing")
public class WhiteLableingController {
	
	@Inject
	WhitelableingService service;
	
	@Inject
	private MediaServiceInterface serviceInterface;
	
	@Post(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA)
	public WhiteLableingEntity addFiles( String clientName,CompletedFileUpload logofile,CompletedFileUpload cssfile) throws ControllerException
	{
		try
		{
	String k=UUID.randomUUID().toString();
	String key=k.substring(0, 8);
	String logoKey=key+logofile.getFilename();
	String filePath=this.serviceInterface.uploadlogoFile(logoKey,logofile);
		WhiteLableingEntity response=this.service.addLogoAndCssFile(clientName,filePath,cssfile);
		return response;
		}
		catch (Exception e) {
			throw new ControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}

	}
	@Get("/getlist")
	public ArrayList<WhiteLableingEntity> getlist() throws ControllerException
	{
		try
		{
			ArrayList<WhiteLableingEntity> responseList=this.service.getlist();
			return responseList;
		}
		catch (Exception e) {
			throw new ControllerException(ReadPropertiesFile.readResponseProperty("101"), e, 406,
					e.getMessage());
		}
	}

}
