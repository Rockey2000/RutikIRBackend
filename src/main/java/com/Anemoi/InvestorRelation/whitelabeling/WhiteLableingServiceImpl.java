package com.Anemoi.InvestorRelation.whitelabeling;

import java.util.ArrayList;
import java.util.List;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class WhiteLableingServiceImpl implements WhitelableingService {

	
	@Inject
	WhiteLableingDao dao;
	
	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";

	private static String DATABASENAME = "databaseName";

	private static String dataBaseName() {

		List<String> tenentList = ReadPropertiesFile.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = ReadPropertiesFile.dataBaseName(tenent);
		}
		return DATABASENAME;

	}
	@Override
	public WhiteLableingEntity addLogoAndCssFile(String clientName,String filePath, CompletedFileUpload cssfile) throws ServiceException{
		
		String dataBaseName=WhiteLableingServiceImpl.dataBaseName();
		try
		{
			WhiteLableingEntity entity=new WhiteLableingEntity();
			entity.setClientName(clientName);
			String fileName1=cssfile.getFilename();
			String fileType1=cssfile.getContentType().toString();
			byte[] fileData1=cssfile.getBytes();
			entity.setCssFileName(fileName1);
			entity.setCssFileType(fileType1);
			entity.setCssFileData(fileData1);
			entity.setFilePath(filePath);
			WhiteLableingEntity response=this.dao.addcssFileAndLogoFile(entity,dataBaseName);
			return response;
		}
		catch (Exception e) {
		throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public ArrayList<WhiteLableingEntity> getlist() throws ServiceException {
	   
		String dataBaseName=WhiteLableingServiceImpl.dataBaseName();
		try
		{
			ArrayList<WhiteLableingEntity>  responselist=this.dao.getlist(dataBaseName);
			return responselist;
		}
		catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
