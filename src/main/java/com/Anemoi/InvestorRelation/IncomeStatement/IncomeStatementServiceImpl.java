package com.Anemoi.InvestorRelation.IncomeStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;



import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class IncomeStatementServiceImpl implements IncomeStatementService{
	
	@Inject
	private IncomeStatementDao incomestatementdao;
	
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
	public IncomeStatementEntity createIncomeStatement(IncomeStatementEntity incomestatement) throws SQLException,IncomeStatementServiceException {
		try
		{
			String dataBaseName=IncomeStatementServiceImpl.dataBaseName();
			
		//	applyValidation(incomestatement);
			
			IncomeStatementEntity createIncomeStatement=this.incomestatementdao.createIncomeStatement(incomestatement,dataBaseName);
			return createIncomeStatement;
		}catch(Exception e) {
			e.printStackTrace();
			throw new IncomeStatementServiceException("unable to create income statement"+e.getMessage());
		}
	
	}

	private void applyValidation(IncomeStatementEntity incomestatement) throws Exception {
		// TODO Auto-generated method stub
		if(incomestatement.getLineItem()==null || incomestatement.getLineItem().isEmpty())
		{
			throw new Exception("line item should not be null or empty");
		}
		if(incomestatement.getAlternativeName()==null || incomestatement.getAlternativeName().isEmpty())
		{
			throw new Exception("alternavtive should not be null or empty");
		}
		if(incomestatement.getType()==null || incomestatement.getType().isEmpty())
		{
			throw new Exception("type should not be null or empty");
		}
		
	}


	@Override
	public IncomeStatementEntity getIncomeStatementById(String incomeid) throws SQLException,IncomeStatementServiceException {
		try {
			String dataBaseName = IncomeStatementServiceImpl.dataBaseName();
			if (incomeid == null || incomeid.isEmpty()) {
			System.out.print("IncomeStatement id must not be null or empty");
			}
			IncomeStatementEntity incomestatementEntity = this.incomestatementdao.getIncomeStatementById(incomeid, dataBaseName);
			return incomestatementEntity;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IncomeStatementServiceException("unable to get income statement by id"+e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncomeStatementEntity> getAllIncomeStatementDetails() throws SQLException,IncomeStatementServiceException {
		try {
			String dataBaseName = IncomeStatementServiceImpl.dataBaseName();
			List<IncomeStatementEntity> incomestatementEntity = this.incomestatementdao.getAllIncomeStatementDetails(dataBaseName);
			JSONObject object = new JSONObject();
			JSONArray incomestatementlJsonData = getJSONFromIncomestatemtList(incomestatementEntity);
			object.put(incomestatementEntity,incomestatementlJsonData);
			return incomestatementEntity;
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new IncomeStatementServiceException("unable to get income statement list"+e.getMessage());
		
	}
	
	}
	
	@SuppressWarnings("unchecked")
	private JSONArray getJSONFromIncomestatemtList(List<IncomeStatementEntity> incomestatementEntity) {
		JSONArray array = new JSONArray();
		for (IncomeStatementEntity incomestatement : incomestatementEntity) {
			JSONObject object = buildJsonIncomeStatement(incomestatement);
			array.add(object);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject buildJsonIncomeStatement(IncomeStatementEntity incomestatement) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(IncomeStatementQuaryConstatnt.INCOMEID, incomestatement.getIncomeid());
		jsonObject.put(IncomeStatementQuaryConstatnt.LINEITEM,incomestatement.getLineItem());
		jsonObject.put(IncomeStatementQuaryConstatnt.ALTERNATIVENAME,incomestatement.getAlternativeName());
		jsonObject.put(IncomeStatementQuaryConstatnt.TYPE,incomestatement.getType());
		
		
		return jsonObject;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IncomeStatementEntity updateIncomeStatement(IncomeStatementEntity incomestatement, String incomeid) throws IncomeStatementServiceException{
		try {
			String dataBaseName = IncomeStatementServiceImpl.dataBaseName();
				if (incomeid == null || incomeid.isEmpty()) {
					System.out.println("income statement id can't be null or empty ");
				
				}
				IncomeStatementEntity incomestatement1 = this.incomestatementdao.updateIncomeStatementDetails(incomestatement, incomeid, dataBaseName);
				JSONObject object = new JSONObject();
				JSONObject jsonFromincomestatement = buildJsonIncomeStatement(incomestatement);
				object.put(incomestatement1, jsonFromincomestatement);
				return incomestatement1;

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncomeStatementServiceException("unable to update income statement details"+e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IncomeStatementEntity deleteIncomeStatement(String incomeid) throws IncomeStatementServiceException {
		try {
			String dataBaseName = IncomeStatementServiceImpl.dataBaseName();
				if (incomeid == null || incomeid.isEmpty()) {
					System.out.println("income statement cannot be null or empty");
				
				}
				IncomeStatementEntity incomestatement =incomestatementdao.getIncomeStatementById(incomeid, dataBaseName);
				if (incomestatement == null) {
					
					
				}
			   this.incomestatementdao.deleteIncomeStatement(incomeid, dataBaseName);
				JSONObject reposneJSON = new JSONObject();
				reposneJSON.put(STATUS, SUCCESS);
				reposneJSON.put(MSG, "income statement deleted suucessfully");
				return incomestatement;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IncomeStatementServiceException("unable to delete income statement"+e.getMessage());
		}
	
	}


	@Override
	public ArrayList<IncomeStatementEntity> addIncomestatmentObject(
			ArrayList<IncomeStatementEntity> incomeentity) throws SQLException, IncomeStatementServiceException, IncomeStatementDaoException {
		// TODO Auto-generated method stub
		
		String dataBaseName=IncomeStatementServiceImpl.dataBaseName();
		ArrayList<IncomeStatementEntity> incomeStatementEntity=this.incomestatementdao.addIncomeStatementObject(incomeentity, dataBaseName);
		return incomeStatementEntity;
	}

}
