import Dao.UserDao;
import model.User;
import org.junit.Test;

public class BankJdbc {

    @Test
    public void initBank() {
        UserDao userDao = new UserDao();
        User userModel = new User();

        userModel.setId(5L);
        userModel.setNome("keyla");
        userModel.setEmail("keyla@gmail.com");

        userDao.save(userModel);
    }

}
