package Dao;

import connection.jdbc.SingleConnection;

import java.sql.Connection;

public class UserPosDao {

    private Connection connection  = null;

    public UserPosDao() {
        connection = SingleConnection.getConnection();
    }
}
