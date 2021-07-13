package Dao;

import connection.jdbc.SingleConnection;

import java.sql.Connection;

public class UserDao {

    private Connection connection  = null;

    public UserDao() {
        connection = SingleConnection.getConnection();
    }
}
