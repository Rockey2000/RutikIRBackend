package com.Anemoi.InvestorRelation.BalanceSheet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public interface BalanceSheetDao {
	
	BalanceSheetEntity createNewBalanceSheetForm(BalanceSheetEntity balanceEntity,String dataBaseName) throws BalanceSheetDaoException;

	BalanceSheetEntity getBalanceById(String balanceid,String dataBaseName) throws BalanceSheetDaoException;

	List<BalanceSheetEntity> getAllBalanceSheetDetails(String dataBaseName) throws BalanceSheetDaoException;

	BalanceSheetEntity updateBalanceSheetDetails(BalanceSheetEntity balanceEntity, String balanceid,String dataBaseName)throws BalanceSheetDaoException;

	String deleteBalanceSheet(String balanceid,String dataBaseName) throws BalanceSheetDaoException;
	
	ArrayList<BalanceSheetEntity> addObjectBalanceSheetEntity(ArrayList<BalanceSheetEntity> balanceSheetEntity,String dataBaseName) throws BalanceSheetDaoException;

}
