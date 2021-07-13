package Dao;

import connection.jdbc.SingleConnection;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    private Connection connection  = null;

    public UserDao() {
        connection = SingleConnection.getConnection();
    }

    public void save(UserModel user) {
        try {
            String sql = "insert into useposjava (idtable, nome, email) values (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setLong(1, user.getId());
            insert.setString(2, user.getNome());
            insert.setString(3, user.getEmail());
            insert.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            }catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }


            e.printStackTrace();
        }




    }
}
