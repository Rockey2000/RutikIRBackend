package com.Anemoi.InvestorRelation.BalanceSheet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Anemoi.InvestorRelation.Configuration.ReadPropertiesFile;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class BalanceSheetServiceImpl implements BalanceSheetService
{
	@Inject
	private BalanceSheetDao balancesheetdao;
	
	
	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";
	private static String DATABASENAME = "databasename";
	
	private static String dataBaseName() {
		List<String> tenentList = ReadPropertiesFile.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = ReadPropertiesFile.dataBaseName(tenent);
		}
		return DATABASENAME;
	}

	

	@Override
	public BalanceSheetEntity createNewBalanceSheetForm(BalanceSheetEntity balance) throws BalanceSheetServiceException{
		
		try
		{
		String dataBaseName=BalanceSheetServiceImpl.dataBaseName();
		
	//	applyValidation(balance);
		
		BalanceSheetEntity createNewBalanceSheetForm=this.balancesheetdao.createNewBalanceSheetForm(balance, dataBaseName);
		return createNewBalanceSheetForm;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BalanceSheetServiceException("unable to create balance sheet "+e.getMessage());
		}
	
	}

	private void applyValidation(BalanceSheetEntity balance) throws Exception {
		if(balance.getLineItem()==null || balance.getLineItem().isEmpty())
		{
			throw new Exception("line item should not be null or empty");
		}
		if(balance.getAlternativeName()==null || balance.getAlternativeName().isEmpty())
		{
			throw new Exception("Alternative name should not be null or empty");
		}
		if(balance.getType()==null || balance.getType().isEmpty())
		{
			throw new Exception("type should not be null or empty");
		}
		
		
	}



	@Override
	public BalanceSheetEntity getBalancesheetById(String balanceid) throws  BalanceSheetServiceException {

		try {
			String dataBaseName = BalanceSheetServiceImpl.dataBaseName();
			if (balanceid == null || balanceid.isEmpty()) {
			System.out.print("Balance id must not be null or empty");
			}
			BalanceSheetEntity balanceEntity = this.balancesheetdao.getBalanceById(balanceid, dataBaseName);
			return balanceEntity;

		} catch (Exception e) {
			e.printStackTrace();
			throw new BalanceSheetServiceException("unable to get balance sheet details by id"+e.getMessage());
		}
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BalanceSheetEntity> getAllBalanceSheetDetails() throws BalanceSheetServiceException{

		try {
			String dataBaseName = BalanceSheetServiceImpl.dataBaseName();
			List<BalanceSheetEntity> balanceEntity = this.balancesheetdao.getAllBalanceSheetDetails(dataBaseName);
			JSONObject object = new JSONObject();
			JSONArray balancesheetJsonData = getJSONFromBalanceSheetList(balanceEntity);
			object.put(balanceEntity,balancesheetJsonData);
			return balanceEntity;
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new BalanceSheetServiceException("unable to get balance sheet list"+e.getMessage());
	}
	
	}

	@SuppressWarnings("unchecked")
	private JSONArray getJSONFromBalanceSheetList(List<BalanceSheetEntity> balanceEntity) {
		JSONArray array = new JSONArray();
		for (BalanceSheetEntity balance : balanceEntity) {
			JSONObject object = buildJsonFrombalanceSheet(balance);
			array.add(object);
		}
		return array;
	}


	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFrombalanceSheet(BalanceSheetEntity balance) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(BalanceSheetQueryConstant.BALANCEID, balance.getBalanceid());
		jsonObject.put(BalanceSheetQueryConstant.LINE_ITEM,balance.getLineItem());
		jsonObject.put(BalanceSheetQueryConstant.ALTERNATIVE_NAME,balance.getAlternativeName());
		jsonObject.put(BalanceSheetQueryConstant.TYPE,balance.getType());
		
		return jsonObject;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public BalanceSheetEntity updateBalanceSheetForm(BalanceSheetEntity balance, String balanceid) throws BalanceSheetServiceException {
		try {
			String dataBaseName = BalanceSheetServiceImpl.dataBaseName();
				if (balanceid == null || balanceid.isEmpty()) {
					System.out.println("balance sheet id can't be null or empty ");
				
				}
				BalanceSheetEntity balancesheet = this.balancesheetdao.updateBalanceSheetDetails(balance, balanceid, dataBaseName);
				JSONObject object = new JSONObject();
				JSONObject jsonFrombalancesheet = buildJsonFrombalanceSheet(balance);
				object.put(balancesheet, jsonFrombalancesheet);
				return balancesheet;

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BalanceSheetServiceException("cannot update "+e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public BalanceSheetEntity deleteBalanceSheet(String balanceid) throws BalanceSheetServiceException {
		try {
			String dataBaseName = BalanceSheetServiceImpl.dataBaseName();
				if (balanceid == null || balanceid.isEmpty()) {
					System.out.println("balance sheet cannot be null or empty");
				
				}
				BalanceSheetEntity incomestatement =balancesheetdao.getBalanceById(balanceid, dataBaseName);
				if (incomestatement == null) {
					
					
				}
			   this.balancesheetdao.deleteBalanceSheet(balanceid, dataBaseName);
				JSONObject reposneJSON = new JSONObject();
				reposneJSON.put(STATUS, SUCCESS);
				reposneJSON.put(MSG, "balance sheet deleted suucessfully");
				return incomestatement;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BalanceSheetServiceException("enter valid request" +e.getMessage());
		}
	
	}



	@Override
	public ArrayList<BalanceSheetEntity> addObjectBalanceSheetEntity(ArrayList<BalanceSheetEntity> balanceSheetEntity) throws BalanceSheetServiceException {
		// TODO Auto-generated method stub
		try
		{
		String dataBaseName=BalanceSheetServiceImpl.dataBaseName();
		ArrayList<BalanceSheetEntity> entity=this.balancesheetdao.addObjectBalanceSheetEntity(balanceSheetEntity,dataBaseName);
		return entity;
	}
		catch (Exception e) {
			// TODO: handle exception
			throw new BalanceSheetServiceException("enter valid request" +e.getMessage());
		}
		
	}
}
