package com.Anemoi.InvestorRelation.DataIngestion;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DataIngestionServiceImpl implements DataIngestionService {
	
	@Inject
	DataIngestionDao dataIngestionDao;
	
	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";

	
private static String DATABASENAME="databaseName";
	
	private static String dataBaseName() {
		
		List<String>tenentList= ReadPropertiesFile.getAllTenant();
		for(String tenent : tenentList) {
			 DATABASENAME = ReadPropertiesFile.dataBaseName(tenent);
		}
		return DATABASENAME;
		
	}

	@Override
	public DataIngestionModel saveDataIngestion(DataIngestionModel dataIngestionModel, CompletedFileUpload file) throws DataIngestionServiceException {
		// TODO Auto-generated method stub
		String dataBaseName=DataIngestionServiceImpl.dataBaseName();
		try {
			//DataIngestionModel model=new DataIngestionModel();
			String fileName=file.getFilename();
			String fileType=file.getContentType().toString();
			byte[] fileData=file.getBytes();
			
			dataIngestionModel.setFileName(fileName);
			
			dataIngestionModel.setFileType(fileType);
			dataIngestionModel.setFileData(fileData);
			
			DataIngestionModel dataIngestion=this.dataIngestionDao.saveDataIngestionDetails(dataIngestionModel,dataBaseName);
			String fileid=this.dataIngestionDao.saveDataIngestionInDataBase(dataIngestion,dataBaseName);
			return dataIngestion;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DataIngestionServiceException("unable to create"+e.getMessage());
		}
	
	
	}

	@Override
	public ArrayList<DataIngestionTableModel> getTableIdByFileId(String fileId) throws DataIngestionServiceException {
		// TODO Auto-generated method stub
	try {
		String dataBaseName=DataIngestionServiceImpl.dataBaseName();
		ArrayList<DataIngestionTableModel> tableid=this.dataIngestionDao.getTableIdByFileId(fileId,dataBaseName);
		return tableid;
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new DataIngestionServiceException("unable ot get"+e.getMessage());
	}

	}

	@Override
	public ArrayList<DataIngestionTableModel> getDataIngestionTableDetails(String tableId) throws DataIngestionServiceException {
		// TODO Auto-generated method stub
	  try
	   {
		String dataBaseName=DataIngestionServiceImpl.dataBaseName();
		ArrayList<DataIngestionTableModel> tableData=this.dataIngestionDao.getTableIngestionTableData(tableId,dataBaseName);
	   return tableData;
	   }catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
		   throw new DataIngestionServiceException("unable to get"+e.getMessage());
	}

	}

	@Override
	public ArrayList<DataIngestionModel> getDataIngestionDetails() throws DataIngestionServiceException{
		// TODO Auto-generated method stub
	try {
		String dataBaseName=DataIngestionServiceImpl.dataBaseName();
		ArrayList<DataIngestionModel> details=this.dataIngestionDao.getDataIngestionDetails(dataBaseName);
		return details;
	}
	catch (Exception e) {
		// TODO: handle exception
		throw new DataIngestionServiceException("unable to get"+e.getMessage());
	}
	}

//	@Override
//	public String deleteDataById(String fileId) throws DataIngestionServiceException {
//		
//		try {
//			String dataBaseName=DataIngestionServiceImpl.dataBaseName();
//			if (fileId == null || fileId.isEmpty()) {
//				System.out.println("file Id cannot be null or empty");
//			
//			}
//			DataIngestionModel model=this.dataIngestionDao.getdataIngetionByfileId(fileId,dataBaseName);
//			if(model==null)
//			{
//				
//			}
//			this.dataIngestionDao.deleteDataIngestionDetailsById(fileId,dataBaseName);
//			JSONObject reposneJSON = new JSONObject();
//			reposneJSON.put(STATUS, SUCCESS);
//			reposneJSON.put(MSG, "Data Ingestion deleted sucessfully");
//			return reposneJSON.toString();
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			throw new DataIngestionServiceException("unable to delete "+e.getMessage());
//		}
//	}

	@Override
	public DataIngestionModel getDataIngesionByFileIdDetails(String fileId) throws DataIngestionServiceException {
		// TODO Auto-generated method stub
       try {
    	   String dataBaseName=DataIngestionServiceImpl.dataBaseName();
			if (fileId == null || fileId.isEmpty()) {
				System.out.println("file Id cannot be null or empty");
			
			}
			DataIngestionModel model=this.dataIngestionDao.getdataIngetionByfileId(fileId,dataBaseName);
			return model;
			
       }catch (Exception e) {
		// TODO: handle exception
    	   throw new DataIngestionServiceException("unable to get "+e.getMessage());
	}
	}

@Override
public ArrayList<DataIngestionTableModel> updateeDataIngestionTabledata(ArrayList<DataIngestionTableModel> dataIngestionTableData, String tableId)
		throws DataIngestionServiceException {
	try
	{
		String dataBaseName=DataIngestionServiceImpl.dataBaseName();
		ArrayList<DataIngestionTableModel> response=this.dataIngestionDao.updatedataIngestionTableData(dataIngestionTableData,tableId,dataBaseName);
	   return response;
	}
	catch (Exception e) {
		// TODO: handle exception
		throw new DataIngestionServiceException("unable to update dataIngestion table data "+e.getMessage());
	}
}

@Override
public DataIngestionMappingModel addDataIngestionMappingTable(DataIngestionMappingModel dataIngestionMappingTable) throws DataIngestionServiceException {
	// TODO Auto-generated method stub
    try
    {
    	String dataBaseName=DataIngestionServiceImpl.dataBaseName();
    	DataIngestionMappingModel mappingModel=this.dataIngestionDao.addDataIngestionMappingTableData(dataIngestionMappingTable,dataBaseName);
         return mappingModel;
    
    }
    catch (Exception e) {
		// TODO: handle exception
    	throw new DataIngestionServiceException("unable to add dataingestion mapping data in table "+e.getMessage());
	}
}

@Override
public ArrayList<DataIngestionMappingModel> getDataIngestionMappingDetailss() throws DataIngestionServiceException {
	 try
	    {
	    	String dataBaseName=DataIngestionServiceImpl.dataBaseName();
	    	ArrayList<DataIngestionMappingModel> modelDetails=this.dataIngestionDao.getDataIngestionMappingTableDetails(dataBaseName);
	    	return modelDetails;
	    }
	    catch (Exception e) {
			// TODO: handle exception
	    	throw new DataIngestionServiceException("unable to get dataingestion mapping table data "+e.getMessage());
		}
}



	

}
