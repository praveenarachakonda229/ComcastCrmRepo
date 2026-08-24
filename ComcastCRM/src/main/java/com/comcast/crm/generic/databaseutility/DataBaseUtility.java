package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
	Connection con;
	public void getDbConnection(String url,String username,String password) {
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			
			con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e){}
	}
	
	public void getDbConnection() {
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cricketers","root","root");
		}
		catch(Exception e){}
	}
	
	public void closeDbConnection() throws SQLException {
		con.close();
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet rs =null;
		try {
			 Statement stmt=con.createStatement();
				rs=stmt.executeQuery(query);
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
	 
	     return rs;
	}
	public int executeNonSelectQuery(String query) {
		int rs =0;
		try {
			 Statement stmt=con.createStatement();
				rs=stmt.executeUpdate(query);
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
	 
	     return rs;
	}
	
}
