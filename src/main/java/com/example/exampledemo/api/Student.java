package com.example.exampledemo.api;

import lombok.var;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        System.out.println("Parami Ashinsana");


    }
}