import connection.jdbc.SingleConnection;
import org.junit.Test;

public class BankJdbc {

    @Test
    public void initBank() {
        SingleConnection.getConnection();
    }

}
