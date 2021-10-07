package ir.maktab58.dataBaseAccess;

import ir.maktab58.model.Drivers;

import java.sql.*;
import java.util.ArrayList;

public class DriverDBAccess extends DBAccess {
    public DriverDBAccess() throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "driver", null);
            if (!tables.next()) {
                creatTable();
            }
        }
    }

    @Override
    public void creatTable() throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE driver(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "  vehicles VARCHAR(25)," +
                "  balance VARCHAR(25)," +
                "    PRIMARY KEY (id) " +
                "    );");
    }

    public Integer save(Drivers driver) throws SQLException {
        Integer i = null;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into driver(name,family,user_name,phone,vehicles,balance) " +
                            "values ('%s','%s',%d,'%s','%s','%s')", driver.getName(), driver.getFamily()
                    , driver.getUserName(), driver.getPhoneNumber(),
                    driver.getVehicles().getType().getType(), driver.getBalance());
            i = statement.executeUpdate(sqlQuery);
            System.out.println("Add driver successful");
        } else {
            System.out.println("----connection is empty----");
            i = null;
        }
        return i;
    }

    @Override
    public void updateBalance(int id, double balance) throws SQLException {
        if (getConnection() != null) {

            double balanceOld = 0;
            String sqlQuery = String.format("select balance from  driver where user_name = ?");
            PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
            findId.setInt(1, id);
            ResultSet resultSet = findId.executeQuery();

            while (resultSet.next()) {
                balance = Double.parseDouble(resultSet.getString(1));
            }

            sqlQuery = String.format("update driver set balance = ? where user_name = ?");
            PreparedStatement updateBalance = getConnection().prepareStatement(sqlQuery);
            updateBalance.setString(1, String.valueOf(balance + balanceOld));
            updateBalance.setInt(2, id);
            updateBalance.executeUpdate();
            System.out.println("Update successful");
        } else
            System.out.println("----connection is empty----");
    }

    @Override
    public Integer search(int id) throws SQLException {
        Integer userId = null;
        String sqlQuery = String.format("select user_name from  driver  where user_name = ?");
        PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
        findId.setInt(1, id);
        ResultSet resultSet = findId.executeQuery();

        while (resultSet.next()) {
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    public void showList() throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = String.format("select * from driver ");
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<Drivers> arrayList = new ArrayList<>();

            while (resultSet.next()) {
                Drivers drivers = new Drivers(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getString(5), Double.parseDouble(resultSet.getString(7)));

                arrayList.add(drivers);
            }
            for (Drivers dri : arrayList) {

                System.out.println(dri.toString());
            }
        } else
            System.out.println("----Connection Is Empty----");
    }

}
