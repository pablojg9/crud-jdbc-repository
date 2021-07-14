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

            // Adicionando o user
            String sql = "insert into useposjava (idtable, nome, email) values (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            // assim que criar o id ele vai pegar o email e mandar pro banco
            insert.setLong(1, user.getId());
            //assim que criar o nome ele vai pegar o email e mandar pro banco
            insert.setString(2, user.getNome());
            //assim que criar o email ele vai pegar o email e mandar pro banco
            insert.setString(3, user.getEmail());
            insert.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                //RollBack caso ele tenha algum erro.
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
            // Preprando o PreparedStatement para a variavel sql;
            PreparedStatement listStatement = connection.prepareStatement(sql);
            ResultSet result = listStatement.executeQuery();

            while (result.next()) {
                User user = new User();
                //Enquanto for verdadeiro o result que está na tabela vai executar o while e add o user.
                user.setId(result.getLong("idtable"));
                user.setNome(result.getString("nome"));
                user.setEmail(result.getString("email"));

                listUser.add(user);
            }

        return listUser;
    }

    //Busanco o user atráves do id
    public User search(Long id) throws Exception {
        User userReturn = new User();

        String sql = "SELECT * FROM  useposjava WHERE idtable = " + id;

        PreparedStatement listStatement = connection.prepareStatement(sql);
        ResultSet result = listStatement.executeQuery();

        while (result.next()) { // Retornar apenas 1 ou nem nenhum

            // Após encontrar o id ele retorna o id, nome, email
            userReturn.setId(result.getLong("idtable"));
            userReturn.setNome(result.getString("nome"));
            userReturn.setEmail(result.getString("email"));

        }

        return userReturn;
    }

    public void update(User user) throws SQLException {
        try {
            // Fazendo o update do nome e email, usando SET NOME = ? e SET email = ?
            String sql = "UPDATE useposjava SET nome = ?, email = ? WHERE idtable = " + user.getId();

            PreparedStatement updateStatement = connection.prepareStatement(sql);

            updateStatement.setString(1, user.getNome());
            updateStatement.setString(2, user.getEmail());
            //Colocando para que execute o sql
            updateStatement.execute();

            connection.commit();

        } catch (Exception e ) {
            //Fazendo um catch caso tenha algum erro.
            connection.rollback();
            e.printStackTrace();
        }
    }
}
