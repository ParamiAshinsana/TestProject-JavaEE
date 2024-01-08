package com.example.exampledemo.api;

import lombok.var;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "ExampleDemo", urlPatterns = "/test",
        initParams = {
                @WebInitParam(name = "db-user", value = "root"),
                @WebInitParam(name = "db-pw", value = "1234"),
                @WebInitParam(name = "db-url", value = "jdbc:mysql://localhost:3306/system24?createDatabaseIfNotExist=true"),
                @WebInitParam(name = "db-class", value = "com.mysql.cj.jdbc.Driver"),
        },
        loadOnStartup = 5)
public class Student extends HttpServlet {
    private static final String SAVE_STUDENT_DATA = "INSERT INTO STUDENT (stid, stname, staddress) VALUES (?,?,?)";

    Connection connection;

    @Override
    public void init() throws ServletException {
        System.out.println("Hello Init");
        try {
            var user = getServletConfig().getInitParameter("db-user");
            var password = getServletConfig().getInitParameter("db-pw");
            var url = getServletConfig().getInitParameter("db-url");
            var dbClass = getServletConfig().getInitParameter("db-class");

            Class.forName(dbClass);
            this.connection = DriverManager.getConnection(url, user, password);

            System.out.println(user);
            System.out.println(password);
            System.out.println(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello doGet");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        try {
            var id = req.getParameter("id");

            String SELECT_DATA = "SELECT * FROM STUDENT WHERE stid = ?";
            var ps = connection.prepareStatement(SELECT_DATA);
            ps.setString(1, id);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String stId = resultSet.getString("stid");
                String stName = resultSet.getString("stname");
                String stAddress = resultSet.getString("staddress");

                System.out.println("Student ID : " + stId);
                System.out.println("Student Name : " + stName);
                System.out.println("Student Address : " + stAddress);

                writer.println("Student ID : " + stId);
                writer.println("Student Name : " + stName);
                writer.println("Student Address : " + stAddress);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello doPost");

        var id = req.getParameter("id");
        var name = req.getParameter("name");
        var address = req.getParameter("address");
        var writer = resp.getWriter();

        resp.setContentType("text/html");

        try {
            var ps = connection.prepareStatement(SAVE_STUDENT_DATA);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, address);

            if (ps.executeUpdate() != 0) {
                writer.println("Data Saved!");
                System.out.println("Saved");
            } else {
                writer.println("Failed to save data!");
                System.out.println("Not Saved");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");

        resp.setContentType("text/html");
        var writer = resp.getWriter();

        try {
            var name = req.getParameter("name");
            var address = req.getParameter("address");

            String UPDATE_DATA = "UPDATE STUDENT SET stname = ?, staddress = ? WHERE stid = ?";
            var ps = connection.prepareStatement(UPDATE_DATA);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, address);


            if (ps.executeUpdate() != 0) {
                writer.println("Data Updated!");
                System.out.println("Updated");
            } else {
                writer.println("Failed to update data!");
                System.out.println("Not Updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");

        resp.setContentType("text/html");
        var writer = resp.getWriter();

        try {
            String DELETE_DATA = "DELETE FROM STUDENT WHERE stid = ?";
            var ps = connection.prepareStatement(DELETE_DATA);
            ps.setString(1, id);

            if (ps.executeUpdate() != 0) {
                writer.println("Data Deleted!");
                System.out.println("Deleted");
            } else {
                writer.println("Failed to delete data!");
                System.out.println("Not Deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}