package ir.maktab58.dataBaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBAccess {
    private Connection Connection = null;

    public DBAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maktab58", "root",
                "SAMAseven@7");
    }

    public Connection getConnection() {
        return Connection;
    }

    public void setConnection(Connection getConnection) {
        this.Connection = getConnection;
    }

    public abstract void creatTable() throws ClassNotFoundException, SQLException;

    public void updateBalance(int id, int balance) throws SQLException {

    }

    public Integer search(int id) throws SQLException {
        return null;
    }

}
