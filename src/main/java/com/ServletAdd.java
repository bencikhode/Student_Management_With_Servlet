package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletAdd extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String pno=req.getParameter("phone");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Load and Register the drive
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","rootroot");//Estab the connection
			PreparedStatement pstmt=con.prepareStatement("insert into person values(?,?,?,?)"); //create statement
			int uid=Integer.parseInt(id);
			Long phone=Long.parseLong(pno);
			
			pstmt.setInt(1, uid);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setLong(4, phone);
			
			pstmt.executeUpdate(); //Execute Query
			
			PrintWriter out=res.getWriter();
			out.print("INSERTED SUCCESS");//process the result
			
			con.close(); //close the connection
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

}
