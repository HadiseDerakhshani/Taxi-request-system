package ir.maktab58.dataBaseAccess;

import java.sql.*;

public class DriverDBAccess extends DBAccess{
    public DriverDBAccess() throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "driver", null);
            if (tables.next()) {
                System.out.println("table driver exist");

            } else {
                createTable();
            }
        }
    }

    public void createTable() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE driver(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "    PRIMARY KEY (id) " +
                "    );");
    }
}
