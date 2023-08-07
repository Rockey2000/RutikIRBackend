package com.Anemoi.InvestorRelation.whitelabeling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.Anemoi.InvestorRelation.Configuration.InvestorDatabaseUtill;

import io.micronaut.http.annotation.Get;
import jakarta.inject.Singleton;

@Singleton
public class WhitelableingDaoImpl implements WhiteLableingDao {

	@Override
	public WhiteLableingEntity addcssFileAndLogoFile(WhiteLableingEntity entity, String dataBaseName) throws DaoException {
		
		Connection con=null;
		PreparedStatement psta=null;
		try
		{
			con=InvestorDatabaseUtill.getConnection();
			psta=con.prepareStatement(WhiteLableingQueryConstant.INSERT_INTO_WHITELABLEING_TABLE.replace(WhiteLableingQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			String id=UUID.randomUUID().toString();
			entity.setWhitelableId(id);
        psta.setString(1, id);	
        psta.setString(2, entity.getClientName());
       psta.setString(3, entity.getFilePath());
        psta.setString(4, entity.getCssFileName());
        psta.setString(5, entity.getCssFileType());
        psta.setBytes(6, entity.getCssFileData());
        psta.executeUpdate();
        return entity;
		}catch (Exception e) {
		throw new DaoException("unable to add css file and logo"+e.getMessage());
		}
		
	}

	@Override
	public ArrayList<WhiteLableingEntity> getlist(String dataBaseName) throws DaoException {

        Connection con=null;
        PreparedStatement psta=null;
        ResultSet rs=null;
        ArrayList<WhiteLableingEntity> list=new ArrayList<>();
        try
        {
        	con=InvestorDatabaseUtill.getConnection();
        	psta=con.prepareStatement(WhiteLableingQueryConstant.SELECT_WHITELABLEING_LIST.replace(WhiteLableingQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
        	rs=psta.executeQuery();
        	while(rs.next())
        	{
        		WhiteLableingEntity entity=this.buildData(rs);
        		list.add(entity);	
        	}
        	return list;
        }
        catch (Exception e) {

   throw new DaoException("unable to get"+e.getMessage());
		}
	}

	private WhiteLableingEntity buildData(ResultSet rs) throws SQLException {
		WhiteLableingEntity entity=new WhiteLableingEntity();
		entity.setWhitelableId(rs.getString("whitelableId"));
		entity.setClientName(rs.getString("clientName"));
	    entity.setFilePath(rs.getString("filePath"));
//		entity.setLogoFileData(rs.getBytes("logoFileData"));
		entity.setCssFileName(rs.getString("cssFileName"));
		entity.setCssFileType(rs.getString("cssFileType"));
//		entity.setCssFileData(rs.getBytes("cssFileData"));
		return entity;
		
	}
	
	

}
