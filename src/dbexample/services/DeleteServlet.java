package dbexample.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dbexample.model.User;
import dbexample.mappers.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().create();

        User user = gson.fromJson(reader, User.class);
        UserMapper userMapper = new UserMapper();

        try {

            if (userMapper.findUser(user)) {
                userMapper.deleteUser(user);
                response.getWriter().write("User succesfully deleted");
            } else {
                response.getWriter().write("There is no user with this msisdn");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
