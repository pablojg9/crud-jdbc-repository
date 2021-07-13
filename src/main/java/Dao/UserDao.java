package Dao;

import connection.jdbc.SingleConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connection  = null;

    public UserDao() {
        connection = SingleConnection.getConnection();
    }

    public void save(User user) {
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

    public List<User> list() throws Exception {
        List<User> listUser = new ArrayList<User>();

            String sql = "SELECT * FROM  useposjava";

            PreparedStatement listStatement = connection.prepareStatement(sql);
            ResultSet result = listStatement.executeQuery();

            while (result.next()) {
                User user = new User();

                user.setId(result.getLong("idtable"));
                user.setNome(result.getString("nome"));
                user.setEmail(result.getString("email"));

                listUser.add(user);
            }

        return listUser;
    }

}
