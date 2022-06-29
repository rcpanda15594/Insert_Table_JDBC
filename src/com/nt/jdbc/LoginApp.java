package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args)throws Exception{

		Scanner scn=null;
		String user=null;
		String pwd=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		
		try {
		scn=new Scanner(System.in);
		if(scn!=null) {
			System.out.println("Enter Username::");
			user=scn.next();
			System.out.println("Enter Password::");
			pwd=scn.next();
			
		}//if
		
//convert input values as required for the SQL query
		user="'"+user+"'";
		pwd="'"+pwd+"'";
		

//register JDBC Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
		
//create statement object
        if(con!=null) {
        	st=con.createStatement();
        	
        }//if
		
		
//prepare SQL Query
        //select count(*) from userlist where username="+user+" and password="+pwd;
        
        query="select count(*) from userlist where username="+user+" and password="+pwd;
                
        System.out.println(query);

//execute the query
        if(st!=null) {
        	rs=st.executeQuery(query);
        }//if
        
        if(rs!=null) {
        	if(rs.next())
        		count=rs.getInt(1);
        	System.out.println(count);
        }//if
        
        if(count==0)
        	System.out.println("Invaild Credentials");
        else
        	System.out.println("vaild Credentials");
		}//try
		catch(SQLException se) {
			System.out.println("Record Insertion failed");
			se.printStackTrace();
			
		}//catch
		catch(ClassNotFoundException ce) {
			System.out.println("Record Insertion failed");
			ce.printStackTrace();
			
		}//catch
		catch(Exception e) {
			System.out.println("Record Insertion failed");
			e.printStackTrace();
			
		}//catch
		
		finally {
			
//close jdbc objects
			
			try {
				
				if(st!=null)
					st.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch
			
			
            try {
				
				if(con!=null)
					con.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch
			
			
             try {
				
				if(scn!=null)
					scn.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch
             
		}//finally

	
        
	}//main

}//class
