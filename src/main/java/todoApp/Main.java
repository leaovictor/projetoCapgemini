package todoApp;

import util.ConnectionFactory;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection c = ConnectionFactory.getConnection();

        ConnectionFactory.closeConnection(c);
    }
}
