package com.Anemoi.InvestorRelation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class InvestorDatabaseUtill {

private static final Logger logger=LoggerFactory.getLogger(InvestorDatabaseUtill.class);
	
	public static Connection getConnection() throws ClassNotFoundException
	{
		Connection connection=null;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://localhost";
		String user="Anemoi";
	String password="Anemoi@123";
		// String password="Password@123";
		
		try {
			connection=DriverManager.getConnection(url,user,password);
			
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println("unable to connect database");
			e.printStackTrace();
		}
		if(connection!=null)
		{
			return connection;
			
		}
		return null;
		
	}
	
	public static void close(Statement statement,Connection connection)
	{
		if(statement!=null)
		{
			try
			{
				statement.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
		}
		if(connection!=null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

	
	public static void close(ResultSet resultset,PreparedStatement statment,Connection connectionclose)
	{
		if(resultset !=null)
		{
			try
			{
				resultset.close();
			}
			catch (SQLException e) {
				
				// TODO: handle exception
			}
		}
		if(statment !=null)
		{
			try
			{
				statment.close();
			}
			catch (SQLException e) {
				
				// TODO: handle exception
			}
	}
		if(connectionclose !=null)
		{
			try
			{
				connectionclose.close();
			}
			catch (SQLException e) {
				
				// TODO: handle exception
			}

		}
	}


}
