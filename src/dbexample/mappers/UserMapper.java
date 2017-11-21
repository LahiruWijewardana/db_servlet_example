package dbexample.mappers;

import dbexample.connection.DatabaseConnect;
import dbexample.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserMapper {

    public Boolean findUser(User user) throws SQLException{
        Boolean userFound = false;

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String query = "SELECT msisdn from users";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);

        ArrayList<Integer> msisdnArray = new ArrayList<Integer>();

        while (rs.next()) {
            msisdnArray.add(rs.getInt("msisdn"));

        }

        if (msisdnArray.contains(user.getMsisdn())){
            userFound = true;
        }

        con.close();

        return userFound;
    }

    public void addUser(User user) throws SQLException {

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "INSERT INTO users (Id, name, msisdn, register) VALUES (?,?,?,?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, user.getId());
        preparedStmt.setString(2, user.getName());
        preparedStmt.setInt(3, user.getMsisdn());
        preparedStmt.setBoolean(4, user.isRegister());

        System.out.println(preparedStmt);
        preparedStmt.execute();
        con.close();

    }

    public void unregisterUser(User user) throws SQLException {

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "UPDATE users SET register = FALSE WHERE msisdn = ?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, user.getMsisdn());

        System.out.println(preparedStmt);
        preparedStmt.execute();
        con.close();

    }

    public void deleteUser(User user) throws SQLException{

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "DELETE FROM users WHERE msisdn = ?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, user.getMsisdn());

        System.out.println(preparedStmt);
        preparedStmt.execute();
        con.close();
    }


    public User viewUser(User user) throws SQLException {

        DatabaseConnect connect = new DatabaseConnect();

        Connection con = connect.dbConnect();

        String sql = "SELECT * FROM users WHERE msisdn = ?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, user.getMsisdn());

        System.out.println(preparedStmt);
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            user.setId(rs.getInt("Id"));
            user.setMsisdn(rs.getInt("msisdn"));
            user.setName(rs.getString("name"));
            user.setRegister(rs.getBoolean("register"));
        }

        con.close();

        return user;
    }

}
