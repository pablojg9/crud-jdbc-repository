import Dao.UserDao;
import model.User;
import org.junit.Test;

import java.util.List;

public class BankJdbc {

    //Usando o junit anotação @test para executar
    @Test
    public void init() {
        UserDao userDao = new UserDao();
        User userModel = new User();


        //Setando e adicionando o user no banco de dados
        userModel.setNome("pabloGuti");
        userModel.setEmail("pabloGuti@gmail.com");

        userDao.save(userModel);
    }

    @Test
    public void initList() {

        try {
            // Criando um uma lista para pegar o id, nome, email
            UserDao userDao = new UserDao();
            List<User> list = userDao.list();

            //Mostrando no console em forma de toString() o id, nome, email
            for(User lista : list) {
                System.out.println(lista);
                System.out.println("==============================");
            }

            //Caso tenha erro, irá imprimir
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initSearch() throws Exception {
        UserDao userDao = new UserDao();

        //Buscando o user atraves do id, usando o método criado no Dao
        User user = userDao.search(5L);
        //Mostrando no console o usuario encontrado
        System.out.println(user);

    }

    @Test
    public void initUpdate(){

        try {
            UserDao userDao = new UserDao();

            //Encotrando o usuario
            User objBank = userDao.search(6L);

            //Caso tenha encontrando irá fazer um update
            objBank.setNome("cesar");
            objBank.setEmail("cesar@gmail.com");
            /*Assim fazer o update chamar o método criado update no Dao e no paramentro
            colocar o que foi atualizado*/
            userDao.update(objBank);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initDelete() {
        try {
            UserDao userDao = new UserDao();
            userDao.delete(10L);
            System.out.println("Usuario deletado");


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
