package dbexample.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dbexample.model.User;
import dbexample.mappers.UserMapper;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().create();

        User user = gson.fromJson(reader, User.class);

        UserMapper userMapper = new UserMapper();
        try {
            if(userMapper.findUser(user)) {
                response.getWriter().write("The User already existing in the database");
            } else {
                userMapper.addUser(user);
                response.getWriter().write("User Registered");
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
