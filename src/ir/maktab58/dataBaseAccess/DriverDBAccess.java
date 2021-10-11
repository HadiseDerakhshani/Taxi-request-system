package ir.maktab58.dataBaseAccess;

import ir.maktab58.enums.StatusTravel;
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
    public void creatTable() throws  SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE driver(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "  balance VARCHAR(25)," +
                "  status VARCHAR(25)," +
                "  location VARCHAR(25)," +
                "  vehicl INT," +
                " FOREIGN KEY (vehicl) REFERENCES vehicles(id)," +
                "    PRIMARY KEY (id) " +
                "    );");
    }

    public Integer save(Drivers driver) throws SQLException {
        Integer i = null;
        if (getConnection() != null) {
            if(search(driver.getUserName())==null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into driver(name,family,user_name,phone,vehicl,balance,status,location) " +
                            "values ('%s','%s',%d,'%s',%d,'%s','%s','%s')", driver.getName(), driver.getFamily()
                    , driver.getUserName(), driver.getPhoneNumber(),
                    driver.getVehicles().getId(), driver.getBalance(),driver.getStatus().getStatus());
            i = statement.executeUpdate(sqlQuery);
            System.out.println("Add driver successful");}
        }
        else {
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
                if (resultSet.getString("status").equals(StatusTravel.WAITING)) {
                    Drivers drivers = new Drivers(resultSet.getString("name"), resultSet.getString("family"),
                            resultSet.getInt("user_name"),
                            resultSet.getString("phone"), Double.parseDouble(resultSet.getString("balance")
                    ), StatusTravel.WAITING);
                    arrayList.add(drivers);
                }else
                  if (resultSet.getString("status").equals(StatusTravel.DOING)) {
                    Drivers drivers = new Drivers(resultSet.getString("name"), resultSet.getString("family"),
                            resultSet.getInt("user_name"),
                            resultSet.getString("phone"), Double.parseDouble(resultSet.getString("balance")
                    ), StatusTravel.DOING);
                    arrayList.add(drivers);
                }

            }
            for (Drivers dri : arrayList) {

                System.out.println(dri.toString());
            }
        } else
            System.out.println("----Connection Is Empty----");
    }

}
