package com.Anemoi.InvestorRelation.DataIngestion;

import java.util.ArrayList;

import com.Anemoi.InvestorRelation.CashFlow.CashFlowControllerException;
import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;

@Controller("/investor/dataIngestion")
public class DataIngestionController {

	@Inject 
	DataIngestionService dataingestionService;
	

	@Post(uri="/addDataIngestion",consumes = {MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA})
	public HttpResponse<DataIngestionModel> saveDataIngetion(@Body DataIngestionModel dataIngestionModel,CompletedFileUpload file) throws DataIngestionControllerException

	{  try {
		DataIngestionModel dataingestionObject=this.dataingestionService.saveDataIngestion(dataIngestionModel, file);
		return HttpResponse.status(HttpStatus.OK).body(dataingestionObject);
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		  throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
	}
	}
	
	@Get("/getDataIngestionDetails")
	public ArrayList<DataIngestionModel> getDataIngestionDetails() throws DataIngestionControllerException
	{  
		try {
		ArrayList<DataIngestionModel> details =this.dataingestionService.getDataIngestionDetails();
		return details;
	}
	catch (Exception e) {
		// TODO: handle exception
		throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
	}
	}
	
	@Get("/getDataIngetionByFileId/{fileId}")
	public HttpResponse<DataIngestionModel> getDataIngestionByFileId(@PathVariable("fileId") String fileId) throws DataIngestionControllerException
	{
		try
		{
			DataIngestionModel model=this.dataingestionService.getDataIngesionByFileIdDetails(fileId);
			return HttpResponse.status(HttpStatus.OK).body(model);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
		
	}
	
	
	@Get("/file/{fileId}")
	public ArrayList<DataIngestionTableModel> getTableId(@PathVariable("fileId") String fileId) throws DataIngestionControllerException
	{
		 try {
		ArrayList<DataIngestionTableModel> tableid=this.dataingestionService.getTableIdByFileId(fileId);
		return tableid;
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			  throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
	}
	
	@Get("/table/{tableId}")
	public ArrayList<DataIngestionTableModel> getDataIngestionTableDetails(@PathVariable("tableId") String tableId) throws DataIngestionControllerException
	{
		try
		{
			ArrayList<DataIngestionTableModel> tableData=this.dataingestionService.getDataIngestionTableDetails(tableId);
			return tableData;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			  throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}

	}
	
//	@Delete("/deleteById/{fileId}")
//	public HttpResponse<String> deleteDataIngestionDetailsById(@PathVariable("fileId") String fileId) throws DataIngestionControllerException
//	{
//		try {
//			String response=this.dataingestionService.deleteDataById(fileId);
//			return HttpResponse.status(HttpStatus.OK).body(response);
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
//		
//		}
//		
//	}
//	
	@Patch("/update/{tableId}")
	public ArrayList<DataIngestionTableModel> updateDataIngestionTableData(@Body ArrayList<DataIngestionTableModel> dataIngestionTableData,@PathVariable("tableId") String tableId) 
	throws DataIngestionControllerException
	{
	
		try {
			ArrayList<DataIngestionTableModel> response=this.dataingestionService.updateeDataIngestionTabledata(dataIngestionTableData,tableId);
		return  response;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	
	
	}
	
	
	///////Data Ingestion Mapping table API
	@Post("/addDataIngestionMappingTableDetails")
	public HttpResponse<DataIngestionMappingModel> addDataIngestionMappingDetails(@Body DataIngestionMappingModel dataIngestionMappingTable) throws DataIngestionControllerException
	{  
		try {
		DataIngestionMappingModel mappingTable=this.dataingestionService.addDataIngestionMappingTable(dataIngestionMappingTable);
		return HttpResponse.status(HttpStatus.OK).body(mappingTable);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	}
	
	@Get("/getDataIngestionMappingdetails")
	public ArrayList<DataIngestionMappingModel> getDataIngetionMappingDetails() throws DataIngestionControllerException
	{
		try
		{
			ArrayList<DataIngestionMappingModel> modelDetails=this.dataingestionService.getDataIngestionMappingDetailss();
			return modelDetails;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new DataIngestionControllerException(ReadPropertiesFile.readResponseProperty("101"), e,400,e.getMessage());
		}
	}
	
	
	
}
