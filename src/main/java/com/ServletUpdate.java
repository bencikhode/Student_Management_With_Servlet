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

public class ServletUpdate extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String pno=req.getParameter("phone");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","rootroot");
			PreparedStatement pstmt=con.prepareStatement("update person set username=?, usermail=?, userpno=? where userid=?");
			int uid=Integer.parseInt(id);
			Long phone=Long.parseLong(pno);
			
			pstmt.setInt(4, uid);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setLong(3, phone);
			
			pstmt.executeUpdate();
			
			PrintWriter out=res.getWriter();
			out.print("UPDATE SUCCESS");
			
			con.close();
		} catch (Exception e) {
		
			e.printStackTrace();
		}	
	}
}
