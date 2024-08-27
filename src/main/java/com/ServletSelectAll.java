package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletSelectAll extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");  // Set content type to HTML
        PrintWriter out = res.getWriter();

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "rootroot");

            // Prepare SQL query to fetch all records
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM person");

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Start HTML table for displaying records
            out.println("<html><body>");
            out.println("<h2>All Users</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>UserId</th><th>UserName</th><th>UserEmail</th><th>UserPhone</th></tr>");

            // Iterate over the result set to display each record
            while (rs.next()) {
                int pid = rs.getInt("userid");
                String pname = rs.getString("username");
                String pemail = rs.getString("usermail");
                long pno = rs.getLong("userpno");

                out.println("<tr>");
                out.println("<td>" + pid + "</td>");
                out.println("<td>" + pname + "</td>");
                out.println("<td>" + pemail + "</td>");
                out.println("<td>" + pno + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            // Close the ResultSet and PreparedStatement
            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
       
        } finally {
            out.close();
        }
    }
}
