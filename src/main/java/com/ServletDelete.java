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

public class ServletDelete extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String id=req.getParameter("id");
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","rootroot");
			PreparedStatement pstmt=con.prepareStatement("delete from person where userid=?");
			int uid=Integer.parseInt(id);
		
			
			pstmt.setInt(1, uid);
			
			
			pstmt.executeUpdate();
			
			PrintWriter out=res.getWriter();
			out.print("DELETED SUCCESS");
			
			con.close();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
	}
	
	
	
	

}
