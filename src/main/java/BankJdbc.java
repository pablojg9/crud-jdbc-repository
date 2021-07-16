import Dao.UserDao;
import model.BeanUserFone;
import model.Telephone;
import model.User;
import org.junit.Test;

import java.util.ArrayList;
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
            //Setando qual o usuario quer deletar atraves do id
            userDao.delete(10L);
            System.out.println("Usuario deletado");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void initInsertPhone() {
        try {
            Telephone telephone = new Telephone();
            UserDao userDao = new UserDao();

            telephone.setNumber("(11) 4765-9899");
            telephone.setType("celular");
            telephone.setUser(13L);

            userDao.savePhone(telephone);

            System.out.println("Salvado no banco de dados o telefone!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadPhoneUser() {
        try {
            UserDao userDao = new UserDao();
            List<BeanUserFone> beanUserFoneList = userDao.beanUserFoneList(13L);

            for (BeanUserFone beanUserFone: beanUserFoneList) {
                System.out.println(beanUserFone);
                System.out.println("==========================================================================");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
