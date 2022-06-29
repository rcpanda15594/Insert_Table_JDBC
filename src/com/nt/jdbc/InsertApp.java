/* Write a jdbc app that gather person details form 
end user & insert into table ? */

package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertApp {

	public static void main(String[] args) throws Exception {
		Scanner scn=new Scanner(System.in);
		
		int sno=0;
		String name=null;
		int sal=0;
		
		Connection con=null;
		Statement st=null;
		try {
//read inputs from end user
		if(scn!=null) {
			System.out.println("Enter Person No:");
			sno=scn.nextInt();
			System.out.println("Enter Person Name:");
			name=scn.next();
			scn.nextLine();
			System.out.println("Enter Person Salary:");
			sal=scn.nextInt();
		}//if
		
//convert input values as required for the SQL Query
		name="'"+name+"'";
		
		
//register JDBC driver
		Class.forName("oracle.jdbc.OracleDriver");

//Establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");

//create Statement Object
		if(con!=null)
			st=con.createStatement();

//Prepare SQL Query
		String query="insert into first values("+sno+","+name+","+sal+")";
        System.out.println(query);
        
//send and execute SQL Query in database 
      int result = st.executeUpdate(query);
		}//try
		
		catch(Exception e) {
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
		
		
	
	}// main method

}//class
