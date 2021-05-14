/**
 * 
 */
package com;

/**
 * @author INURI-PC
 *
 */

	import java.sql.*;
	public class cart
	{ //A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/y3proj","root", "");
	 }
	 	catch (Exception e)
	 		{e.printStackTrace();}
	 	return con;
	 }
	
	public String insertCart( String prodid, String prodname, String prodqty, String prodprice)
	 {
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
				
	 // create a prepared statement
	 String query = " insert into shoppingcart(`prodnum`,`prodid`,`prodname`,`prodqty`,`prodprice`)" + " values (?,?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, prodid);
			 preparedStmt.setString(3, prodname);
			 preparedStmt.setString(4, prodqty);
			 preparedStmt.setString(5, prodprice);
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
			}
		
			catch (Exception e)
			{
					output = "Error while inserting into the cart.";
					System.err.println(e.getMessage());
			}
	 return output;
	 }
	public String readCart()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Product ID</th><th>Product Name</th>" + "<th>Product quantity</th>" + "<th>Product price </th>" +"<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from shoppingcart";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String prodnum = Integer.toString(rs.getInt("prodnum"));
	 String prodid = rs.getString("prodid");
	 String prodname = rs.getString("prodname");
	 String prodqty = rs.getString("prodqty");
	 String prodprice = rs.getString("prodprice");
	 // Add into the html table
	 output += "<tr><td>" + prodnum + "</td>";
	 output += "<td>" + prodid + "</td>";
	 output += "<td>" + prodname + "</td>";
	 output += "<td>" + prodqty + "</td>";
	 output += "<td>" + prodprice + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='cart.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='prodnum' type='hidden' value='" + prodnum+ "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Cart.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updateCart(String prodnum , String prodid, String prodname, String prodqty, String prodprice)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE shoppingcart SET prodid=?,prodname=?,prodqty=?,prodprice=? WHERE prodnum=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, prodid);
		 preparedStmt.setString(2, prodname);
		 preparedStmt.setString(3, prodqty);
		 preparedStmt.setString(4, prodprice);
		 preparedStmt.setInt(5, Integer.parseInt(prodnum));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the shopping cart.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteCart(String prodnum)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from shoppingcart where prodnum=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(prodnum));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the cart.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		} 
