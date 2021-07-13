import Dao.UserDao;
import model.User;
import org.junit.Test;

import java.util.List;

public class BankJdbc {

    @Test
    public void init() {
        UserDao userDao = new UserDao();
        User userModel = new User();

        userModel.setId(6L);
        userModel.setNome("keyla");
        userModel.setEmail("keyla@gmail.com");

        userDao.save(userModel);
    }

    @Test
    public void initList() {
        try {
            UserDao userDao = new UserDao();
            List<User> list = userDao.list();

            for(User lista : list) {
                System.out.println(lista);
                System.out.println("==============================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
