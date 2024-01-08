package com.example.exampledemo;

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

//@WebServlet(name = "ExampleDemo", urlPatterns = "/ootest",
//        initParams = {
//                @WebInitParam(name = "db-user", value = "root"),
//                @WebInitParam(name = "db-pw", value = "1234"),
//                @WebInitParam(name = "db-url", value = "jdbc:mysql://localhost:3306/system24?createDatabaseIfNotExist=true"),
//                @WebInitParam(name = "db-class", value = "com.mysql.cj.jdbc.Driver"),
//        },
//        loadOnStartup = 5
//)
public class Item  {
}