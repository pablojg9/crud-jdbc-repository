import Dao.UserDao;
import model.UserModel;
import org.junit.Test;

public class BankJdbc {

    @Test
    public void initBank() {
        UserDao userDao = new UserDao();
        UserModel userModel = new UserModel();

        userModel.setId(5L);
        userModel.setNome("keyla");
        userModel.setEmail("keyla@gmail.com");

        userDao.save(userModel);
    }

}
