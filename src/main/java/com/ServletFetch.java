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

public class ServletFetch extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // Set the response content type to HTML
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Retrieve the 'id' parameter from the request
        String id = req.getParameter("id");

        // Check if the 'id' parameter is present
        if (id == null || id.isEmpty()) {
            out.println("<h1>Please provide a valid User ID.</h1>");
            return;
        }

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "rootroot");

            // Prepare the SQL query with a parameterized statement
            pstmt = con.prepareStatement("SELECT * FROM person WHERE userid=?");
            int uid = Integer.parseInt(id);
            pstmt.setInt(1, uid);

            // Execute the query
            rs = pstmt.executeQuery();

            // Generate HTML response
            out.println("<html><body>");
            out.println("<h2>User Details</h2>");

            if (rs.next()) {
                // If a user is found, display the details in a table format
                out.println("<table border='1'>");
                out.println("<tr><th>UserId</th><th>UserName</th><th>UserEmail</th><th>UserPhone</th></tr>");
                out.println("<tr>");
                out.println("<td>" + rs.getInt("userid") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("usermail") + "</td>");
                out.println("<td>" + rs.getLong("userpno") + "</td>");
                out.println("</tr>");
                out.println("</table>");
            } else {
                // If no user is found, display a message
                out.println("<h3>No Data Present for User ID: " + uid + "</h3>");
            }

            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            // Close all resources to prevent memory leaks
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
