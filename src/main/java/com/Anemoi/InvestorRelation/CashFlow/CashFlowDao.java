package com.Anemoi.InvestorRelation.CashFlow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.micronaut.http.multipart.CompletedFileUpload;

public interface CashFlowDao {

	CashFlowEntity createNewCashFlow(CashFlowEntity cashFlowEntity, String dataBaseName) throws CashFlowDaoException;

	CashFlowEntity getCashFlowById(String cashid, String dataBaseName) throws CashFlowDaoException;

	List<CashFlowEntity> getAllCashFlowDetails(String dataBaseName) throws SQLException, CashFlowDaoException;

	CashFlowEntity updateCashFlowDetails(CashFlowEntity cashFlowEntity, String cashid, String dataBaseName)
			throws CashFlowDaoException;

	String deleteCashFlow(String cashid, String dataBaseName) throws CashFlowDaoException;

	ArrayList<CashFlowEntity> addCashFlowEntityObject(ArrayList<CashFlowEntity> cashFlowEntity, String dataBaseName)
			throws CashFlowDaoException;

	ArrayList<CashFlowEntity> uploadCashFlowExcelSheet(CompletedFileUpload file, String dataBaseName) throws CashFlowDaoException;

}
