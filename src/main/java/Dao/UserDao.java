package Dao;

import connection.jdbc.SingleConnection;
import model.BeanUserFone;
import model.Telephone;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    //Criando uma variavel para a conexão no banco de dados
    private Connection connection = null;

    public UserDao() {
        connection = SingleConnection.getConnection();
    }

    public void save(User user) {
        try {
            // Adicionando o user
            String sql = "insert into useposjava (nome, email) values (?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            // assim que criar o id ele vai pegar o email e mandar pro banco
            //assim que criar o nome ele vai pegar o email e mandar pro banco
            insert.setString(1, user.getNome());
            //assim que criar o email ele vai pegar o email e mandar pro banco
            insert.setString(2, user.getEmail());
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

    public void savePhone(Telephone phone) {
        try {
            String sql = "insert into telefoneuser(numero, tipo, userpessoa) values (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, phone.getNumber());
            insert.setString(2, phone.getType());
            insert.setLong(3, phone.getUser());
            insert.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
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

    //Buscando o user atráves do id
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

    public List<BeanUserFone> beanUserFoneList(Long idUser) {
            List<BeanUserFone> beanUserFoneList = new ArrayList<BeanUserFone>();

            String sql = " select nome, numero, email from telefoneuser as fone ";
            sql += " inner join useposjava as userp ";
            sql += " on fone.userpessoa = userp.idtable ";
            sql += "where userp.idtable = " + idUser;

            try {
            PreparedStatement beanStatement = connection.prepareStatement(sql);
            ResultSet resultSet = beanStatement.executeQuery();

            while(resultSet.next()) {
                BeanUserFone userFone = new BeanUserFone();
                userFone.setEmail(resultSet.getString("email"));
                userFone.setName(resultSet.getString("nome"));
                userFone.setNumber(resultSet.getString("numero"));

                beanUserFoneList.add(userFone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanUserFoneList;
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

    public void delete(Long id) throws SQLException {
        try {
            //DELETANDO ATRÁVES DO BANCO
            String sql = "DELETE FROM useposjava WHERE idtable = " + id;
            PreparedStatement deleteStatement = connection.prepareStatement(sql);

            deleteStatement.execute();
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public void isDelete(Long id) {
        try {
            String sqlPhone = "DELETE FROM telefoneuser WHERE userpessoa = " + id;
            String sqlUser = "DELETE FROM useposjava where idtable = "+ id;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlPhone);
            preparedStatement.executeUpdate();
            connection.commit();

            preparedStatement = connection.prepareStatement(sqlUser);
            preparedStatement.executeUpdate();
            connection.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
