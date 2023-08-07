package com.Anemoi.InvestorRelation.ClientDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Anemoi.InvestorRelation.AnalystLineItem.AnalystLineItemDaoImpl;
import com.Anemoi.InvestorRelation.AnalystLineItem.AnalystLineItemQueryConstant;
import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;

import jakarta.inject.Singleton;

@Singleton
public class ClientDetailsDaoImpl implements ClientDetailsDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnalystLineItemDaoImpl.class);

	@SuppressWarnings("resource")
	@Override
	public ClientDetailsEntity addClientDetails(ClientDetailsEntity detailsEntity, String dataBaseName) throws ClientDaoException{
		Connection con = null;
		PreparedStatement psta = null;
		ResultSet result = null;
		List<String> existingValues = new ArrayList<>();
		try {
			con = InvestorDatabaseUtill.getConnection();
			String clientName = detailsEntity.getClientName();
			psta = con.prepareStatement(ClientDetailsQueryConstant.SELECT_CLIENTNAME
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = psta.executeQuery();
			while (result.next()) {
				existingValues.add(result.getString("clientName"));
			}
			  boolean isDuplicate = existingValues.contains(clientName);
				boolean isMatched = existingValues.stream().anyMatch(clientName::equalsIgnoreCase);
	             if(!isDuplicate && isMatched == false)
	             {
			psta = con.prepareStatement(ClientDetailsQueryConstant.INSERT_INTO_CLIENTDETAILS
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			String id = UUID.randomUUID().toString();
			detailsEntity.setClientId(id);
			psta.setString(1, detailsEntity.getClientId());
			psta.setInt(2, detailsEntity.getProjectCode());
			psta.setInt(3, detailsEntity.getTaskCode());
			psta.setString(4, detailsEntity.getClientName().trim());
			psta.setString(5, detailsEntity.getClientAddress());
			psta.setString(6, detailsEntity.getEmailDomain());
			psta.setString(7, detailsEntity.getEmailId().toString());
			psta.setString(8, detailsEntity.getIndustry());
			System.out.println("detailsEntity.getSuggestedPeers().size()"+detailsEntity.getSuggestedPeers());
			if(detailsEntity.getSuggestedPeers()==null)
			{
				psta.setString(9, null);
			}else {
			psta.setString(9, detailsEntity.getSuggestedPeers().toString());
			}
			psta.setString(10, detailsEntity.getAssignAA().toString());
			psta.executeUpdate();
			}
			else
			{
				throw new ClientDaoException("Cannot insert Duplicate ClientName");
			}
			return detailsEntity;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ClientDaoException(e.getMessage());
		}
		finally {
			LOGGER.info("closing the connection");
			InvestorDatabaseUtill.close(psta, con);

		}
	

	}

	@Override
	public ArrayList<ClientDetailsEntity> getAllClientDetails(String dataBaseName) throws ClientDaoException {

		Connection con = null;
		PreparedStatement psta = null;
		ResultSet rs = null;
		ArrayList<ClientDetailsEntity> clientDetailsList = new ArrayList<>();
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta = con.prepareStatement(ClientDetailsQueryConstant.SELECT_CLIENTDETAILS
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			rs = psta.executeQuery();
			while (rs.next()) {
				ClientDetailsEntity entity = buildData(rs);
				clientDetailsList.add(entity);
			}
			return clientDetailsList;
		} catch (Exception e) {
			throw new ClientDaoException("unable to get client details"+e.getMessage());
		}
	
	}

	private ClientDetailsEntity buildData(ResultSet rs) throws SQLException {
		ClientDetailsEntity entity = new ClientDetailsEntity();
		entity.setClientId(rs.getString("clientId"));
		entity.setProjectCode(rs.getInt("projectCode"));
		entity.setTaskCode(rs.getInt("taskCode"));
		entity.setClientName(rs.getString("clientName"));
		entity.setClientAddress(rs.getString("clientAddress"));
		entity.setEmailDomain(rs.getString("emailDomain"));
		
		 String participantField = rs.getString("emailId");
		    participantField = participantField.substring(1, participantField.length() - 1); // Remove the square brackets
		    String[] participants = participantField.split(", ");
		    ArrayList<String> participantList = new ArrayList<>(Arrays.asList(participants));
		    entity.setEmailId(participantList);
		    
		entity.setIndustry(rs.getString("industry"));
		
		if(rs.getString("suggestedPeers")!=null) {
		 String suggestedPeers = rs.getString("suggestedPeers");
		 suggestedPeers = suggestedPeers.substring(1, suggestedPeers.length() - 1); // Remove the square brackets
		    String[] suggestedPeer = suggestedPeers.split(", ");
		    ArrayList<String> suggestedPeerlist = new ArrayList<>(Arrays.asList(suggestedPeer));
		    entity.setSuggestedPeers(suggestedPeerlist);
		}else {
		    entity.setSuggestedPeers(null);

		}
		    String assignAA = rs.getString("assignAA");
		    assignAA = assignAA.substring(1, assignAA.length() - 1); // Remove the square brackets
			    String[] assignAAs = assignAA.split(", ");
			    ArrayList<String> assignAAlist = new ArrayList<>(Arrays.asList(assignAAs));
			    entity.setAssignAA(assignAAlist);
		
		return entity;

	}

	@Override
	public ClientDetailsEntity getclientDetailsByprojectCode(int projectCode, int taskCode, String dataBaseName) throws ClientDaoException{

		Connection con = null;
		PreparedStatement psta = null;
		ResultSet rs = null;
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta = con.prepareStatement(ClientDetailsQueryConstant.SELECT_CLIENTDETAILS_BYPROJECTCODE
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			psta.setInt(1, projectCode);
			psta.setInt(2, taskCode);
			rs = psta.executeQuery();
			while (rs.next()) {
				ClientDetailsEntity enity = buildClientDetailsByprojectCode(rs);
				return enity;
			}

		} catch (Exception e) {
			throw new ClientDaoException("unable to get project code and task code"+e.getMessage());
		}
		return null;
	}

	private ClientDetailsEntity buildClientDetailsByprojectCode(ResultSet rs) throws SQLException {
		ClientDetailsEntity entity = new ClientDetailsEntity();
		entity.setProjectCode(rs.getInt("projectCode"));
		entity.setTaskCode(rs.getInt("taskCode"));
		entity.setClientName(rs.getString("clientName"));
		entity.setClientAddress(rs.getString("clientAddress"));
		return entity;
	}

	@Override
	public ClientDetailsEntity addProjectCodeFor(ClientDetailsEntity clientDetailsEntity, String dataBaseName) {

		Connection con = null;
		PreparedStatement psta = null;
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta = con.prepareStatement(ClientDetailsQueryConstant.INSERT_CLIENTDETAILS_BYPROJECTCODE
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			psta.setInt(1, clientDetailsEntity.getProjectCode());
			psta.setInt(2, clientDetailsEntity.getTaskCode());
			psta.setString(3, clientDetailsEntity.getClientName());
			psta.setString(4, clientDetailsEntity.getClientAddress());
			psta.executeUpdate();
			return clientDetailsEntity;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("resource")
	@Override
	public ClientDetailsEntity updateClientDetails(String clientId, ClientDetailsEntity clientDetailsEntity,
			String dataBaseName) throws ClientDaoException {
		Connection con = null;
		PreparedStatement psta = null;
		ResultSet result = null;
		List<String> existingValues = new ArrayList<>();
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta = con.prepareStatement(ClientDetailsQueryConstant.UPDATE_CLIENTDETAILS
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			psta.setString(1, clientDetailsEntity.getEmailDomain());
			psta.setString(2, clientDetailsEntity.getEmailId().toString());
			psta.setString(3, clientDetailsEntity.getIndustry());
			psta.setString(4, clientDetailsEntity.getSuggestedPeers().toString());
			psta.setString(5, clientDetailsEntity.getAssignAA().toString());
			psta.setString(6, clientId);
			psta.executeUpdate();
		
			return clientDetailsEntity;
	}catch (Exception e) {
		// TODO: handle exception
		throw new ClientDaoException(e.getMessage());
	}
		finally {
			LOGGER.info("closing the connection");
			InvestorDatabaseUtill.close(psta, con);

		}
	
	

}

	@Override
	public void deleteClientDetails(String clientId, String dataBaseName) throws ClientDaoException {
		Connection con = null;
		PreparedStatement psta = null;
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta = con.prepareStatement(ClientDetailsQueryConstant.DELETE_CLIENTDETAILS
					.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
		psta.setString(1, clientId);
		psta.executeUpdate();
	}catch (Exception e) {
		throw new ClientDaoException("unable to delete client details"+e.getMessage());
	}
}

	@Override
	public ClientDetailsEntity getClientDataByclientId(String clientId, String dataBaseName) throws ClientDaoException {
		Connection con = null;
		PreparedStatement psta = null;
		ResultSet rs=null;
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta=con.prepareStatement(ClientDetailsQueryConstant.SELECT_CLIENTDETAILS_BYCLIENTID.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			psta.setString(1, clientId);
			rs=psta.executeQuery();
			while(rs.next())
			{
			
				ClientDetailsEntity entity=this.buildData(rs);
				return entity;
			}
		}catch (Exception e) {
			throw new ClientDaoException("unable to get client details by client id"+e.getMessage());
		}
		return null;
	}

	@Override
	public ClientDetailsEntity getDetailsByClientName(String clientName, String dataBaseName)
			throws ClientDaoException {
		Connection con = null;
		PreparedStatement psta = null;
		ResultSet rs=null;
		try {
			con = InvestorDatabaseUtill.getConnection();
			psta=con.prepareStatement(ClientDetailsQueryConstant.SELECT_CLIENTDETAILS_BYCLIENTNAME.replace(ClientDetailsQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			psta.setString(1, clientName);
			rs=psta.executeQuery();
			while(rs.next())
			{
			
				ClientDetailsEntity entity=this.buildData(rs);
				return entity;
			}
		}catch (Exception e) {
			throw new ClientDaoException("unable to get client details by client name"+e.getMessage());
		}
		return null;
	}
	}

