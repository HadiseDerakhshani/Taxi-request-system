package ir.maktab58.dataBaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private Connection getConnection =null;
    public DBAccess() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        getConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maktab58", "root", "SAMAseven@7");
    }

    public Connection getGetConnection() {
        return getConnection;
    }

    public void setGetConnection(Connection getConnection) {
        this.getConnection = getConnection;
    }
    public void updateBalance(){

    }

}
