package ir.maktab58.dataBaseAccess;

import ir.maktab58.model.Drivers;
import ir.maktab58.model.Passengers;

import java.sql.*;

public class PassengerDBAccess extends DBAccess{
    public PassengerDBAccess() throws SQLException, ClassNotFoundException {
    if (getGetConnection() != null) {
        DatabaseMetaData metaData = getGetConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, "passenger", null);
        if (tables.next()) {
            System.out.println("table passenger exist");

        } else {
            createTable();
        }
    }
}

    public void createTable() throws SQLException {
        Connection connection = getGetConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE passenger(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "  balance DOUBLE ," +
                "    PRIMARY KEY (id) " +
                "    );");
    }
    public Integer save(Passengers passengers) throws SQLException {
        Integer i=null;
        if (getGetConnection() != null) {
            Statement statement = getGetConnection().createStatement();
            String sqlQuery = String.format("insert into driver(name,family,user_name,phone,balance) " +
                            "values ('%s','%s','%d','%s','%s','%f')",passengers.getName(),passengers.getFamily()
                    ,passengers.getUserName(),passengers.getPhoneNumber(),passengers.getBalance());
            i=statement.executeUpdate(sqlQuery);
            System.out.println("Add employee successful");
        } else{
            System.out.println("----connection is empty----");
            i=null;
        }
        return i;
    }

    @Override
    public void updateBalance() {
        super.updateBalance();
    }
}
