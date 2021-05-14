package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class cart1 {
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/y3proj","root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
		}



	public String insertcart1(String prodid, String prodname, String prodqty, String prodprice) {
		
		 String output = "";
		 
		 try {
		
		Connection con = connect();
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		// create a prepared statement
		String query = "insert into shhoppingcart (prodnum, prodid, prodname, prodqty, prodprice)"
				 + " values (?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, 0); 
		preparedStmt.setString(2, prodid); 
		preparedStmt.setString(3, prodname); 
		preparedStmt.setString(4, prodqty);
		preparedStmt.setString(5, prodprice);
		
		System.out.println(prodid);
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newcart1 = readcart1();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newcart1 + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


	public String readcart1()
	{ 
			 String output = ""; 
			try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>prod number</th>" 
			 +"<th>product Name</th><th>product quantity</th>"
			 + "<th>product price</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from shoppingcart"; 
			 java.sql.Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String prodnum = Integer.toString(rs.getInt("prodnum")); 
			 String prodid = rs.getString("prodid"); 
			 String prodname = rs.getString("prodname"); 
			 String prodqty = rs.getString("prodqty"); 
			 String prodprice = rs.getString("proprice"); 
			 
			 // Add a row into the html table
			 output += "<tr><td><input id='hidprodnumUpdate' name='hidprodnumUpdate' type='hidden' value='" + prodnum + "'>"
					 + prodid + "</td>";
			 output += "<td>" + prodname + "</td>"; 
			 output += "<td>" + prodqty + "</td>"; 
			 output += "<td>" + prodprice + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-itemid='" + prodnum + "'></td>"
			 + "<td><form method='post' action='cart.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" + prodnum + "'>"
			 + "<input name='hidprodnumDelete' type='hidden' " 
			 + " value='" + prodnum + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the cart."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

	public String deletecart1(String prodnum)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from shoppingcart where prodnum=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(prodnum)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newcart1 = readcart1();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newcart1 + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
		}

	public String updatecart1(String prodnum, String prodid, String prodname, String prodqty, String prodprice)
	//4
	{
	String output = "";
	try {
	Connection conn = connect();
	if (conn == null) {
	return "Error while connecting to the database for updating.";
	}

	// create a prepared statement
	String query = "UPDATE cart1 SET prodid=?,prodname=?,prodqty=?,prodprice=? WHERE prodnum=?";
	PreparedStatement preparedStmt = conn.prepareStatement(query);
	//binding values
	preparedStmt.setString(1, prodid);
	preparedStmt.setString(2, prodname);
	preparedStmt.setString(3, prodqty);
	preparedStmt.setString(4, prodprice);
	preparedStmt.setInt(5, Integer.parseInt(prodnum));
	//execute the statement
	preparedStmt.execute();
	conn.close();
	String newcart1 = readcart1();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newcart1 + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the cart.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


}
