package com.Anemoi.InvestorRelation.IncomeStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IncomeStatementService {
	
	IncomeStatementEntity createIncomeStatement(IncomeStatementEntity incomestatement) throws SQLException,IncomeStatementServiceException;
	 
	IncomeStatementEntity getIncomeStatementById(String incomeid) throws SQLException,IncomeStatementServiceException;
	
	List<IncomeStatementEntity> getAllIncomeStatementDetails() throws SQLException,IncomeStatementServiceException;
	
	IncomeStatementEntity updateIncomeStatement(IncomeStatementEntity incomestatement, String incomeid) throws IncomeStatementServiceException;
	
	IncomeStatementEntity deleteIncomeStatement(String incomeid) throws IncomeStatementServiceException;
	
	ArrayList<IncomeStatementEntity> addIncomestatmentObject(ArrayList<IncomeStatementEntity> incomeentity) throws SQLException,IncomeStatementServiceException, IncomeStatementDaoException;

}
