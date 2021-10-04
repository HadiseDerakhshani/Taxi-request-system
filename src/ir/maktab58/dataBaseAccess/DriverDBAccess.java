package ir.maktab58.dataBaseAccess;

import ir.maktab58.model.Drivers;

import java.sql.*;

public class DriverDBAccess extends DBAccess{
    public DriverDBAccess() throws SQLException, ClassNotFoundException {
        if (getGetConnection() != null) {
            DatabaseMetaData metaData = getGetConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "driver", null);
            if (tables.next()) {
                System.out.println("table driver exist");

            } else {
                createTable();
            }
        }
    }

    public void createTable() throws SQLException {
        Connection connection = getGetConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE driver(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "  vehicles VARCHAR(25)," +
                "  balance DOUBLE ," +
                "    PRIMARY KEY (id) " +
                "    );");
    }

    public Integer save(Drivers driver) throws SQLException {
        Integer i=null;
        if (getGetConnection() != null) {
            Statement statement = getGetConnection().createStatement();
            String sqlQuery = String.format("insert into driver(name,family,user_name,phone,vehicles,balance) " +
                            "values ('%s','%s','%d','%s','%s','%f')",driver.getName(),driver.getFamily()
                    ,driver.getUserName(),driver.getPhoneNumber(),driver.getVehicles().getType(),driver.getBalance());
            i=statement.executeUpdate(sqlQuery);
            System.out.println("Add employee successful");
        } else{
            System.out.println("----connection is empty----");
            i=null;
        }
        return i;
    }

}
