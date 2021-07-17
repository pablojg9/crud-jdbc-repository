package connection.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    //Colocando o url, user, senha do banco de dados
    private static String url = "jdbc:postgresql://localhost:5432/posjava";
    private static String user = "postgres";
    private static String password = "arma1234";

    //Criando uma variavel na conexão static sem precisar instaciar.
    private static Connection connection = null;

    //colocando o método static
    static {
        connect();
    }

    public SingleConnection(){
        connect();
    }

    private static void connect() {
        try {
            //Fazendo um if para que ele caia no if, sempre irá começar com o null a conexão.
            if (connection == null) {
                //Dizendo que o banco é o postgresql
                Class.forName("org.postgresql.Driver");
                //Conectando com o banco onde foi passado a url, user, senha.
                connection = DriverManager.getConnection(url, user, password);
                //Fazendo um autoCommit para false para que ele não commit sozinho
                connection.setAutoCommit(false);
                System.out.println("O banco conectou com sucesso!");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Fazendo um get para pegar a conexão que era private e que foi encapsulada.
    public static Connection getConnection(){
        return connection;
    }
}
