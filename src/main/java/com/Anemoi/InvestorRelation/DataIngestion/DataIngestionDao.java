package com.Anemoi.InvestorRelation.DataIngestion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DataIngestionDao {

	DataIngestionModel saveDataIngestionDetails(DataIngestionModel dataIngestionModel, String dataBaseName) throws SQLException,DataIngestionDaoException;


	String saveDataIngestionInDataBase(DataIngestionModel dataIngestion, String dataBaseName) throws SQLException, FileNotFoundException, IOException, ClassNotFoundException,DataIngestionDaoException;


	ArrayList<DataIngestionTableModel> getTableIdByFileId(String fileId, String dataBaseName) throws DataIngestionDaoException;


	ArrayList<DataIngestionTableModel> getTableIngestionTableData(String tableId, String dataBaseName) throws DataIngestionDaoException;


	ArrayList<DataIngestionModel> getDataIngestionDetails(String dataBaseName) throws DataIngestionDaoException;


//	String deleteDataIngestionDetailsById(String fileId, String dataBaseName) throws DataIngestionDaoException;


	DataIngestionModel getdataIngetionByfileId(String fileId, String dataBaseName) throws DataIngestionDaoException;


	ArrayList<DataIngestionTableModel> updatedataIngestionTableData(ArrayList<DataIngestionTableModel> dataIngestionTableData, String tableId,
			String dataBaseName) throws DataIngestionDaoException;


	ArrayList<DataIngestionMappingModel> addDataIngestionMappingTableData(ArrayList<DataIngestionMappingModel> dataIngestionMappingTable,
			String dataBaseName) throws DataIngestionDaoException;


	ArrayList<DataIngestionMappingModel> getDataIngestionMappingTableDetails(String dataBaseName) throws DataIngestionDaoException;


	DataIngestionMappingModel getDataIngestionMappingDetailsByMapId(String mapId, String dataBaseName) throws DataIngestionDaoException;


	


  
	
}
