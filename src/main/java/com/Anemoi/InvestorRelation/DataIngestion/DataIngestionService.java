package com.Anemoi.InvestorRelation.DataIngestion;

import java.util.ArrayList;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface DataIngestionService {
	
	public DataIngestionModel saveDataIngestion(DataIngestionModel dataIngestionModel,CompletedFileUpload file) throws DataIngestionServiceException;

	public ArrayList<DataIngestionTableModel> getTableIdByFileId(String fileId) throws DataIngestionServiceException;

	public ArrayList<DataIngestionTableModel> getDataIngestionTableDetails(String tableId) throws DataIngestionServiceException;

	public ArrayList<DataIngestionModel> getDataIngestionDetails() throws DataIngestionServiceException;

	//public String deleteDataById(String fileId) throws DataIngestionServiceException;

	public DataIngestionModel getDataIngesionByFileIdDetails(String fileId) throws DataIngestionServiceException;

	public ArrayList<DataIngestionTableModel> updateeDataIngestionTabledata(ArrayList<DataIngestionTableModel> dataIngestionTableData,
			String tableId) throws DataIngestionServiceException;

	
	//DataIngestion Mapping table 
	public ArrayList<DataIngestionMappingModel> addDataIngestionMappingTable(ArrayList<DataIngestionMappingModel> dataIngestionMappingTable) throws DataIngestionServiceException;

	public ArrayList<DataIngestionMappingModel> getDataIngestionMappingDetailss() throws DataIngestionServiceException;

	public DataIngestionMappingModel getDataIngestionMappingByMapId(String mapId) throws DataIngestionServiceException;

	
	

	
}
