package ir.maktab58.dataBaseAccess;

import com.sun.deploy.security.SelectableSecurityManager;
import ir.maktab58.StatusTravel;
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
                "  balance VARCHAR(25)," +
                "  status VARCHAR(25)," +
                "  vehicl INT ," +
                "    PRIMARY KEY (id) " +
                "    );");
    }

    /*CREATE TABLE `employee` (
      // "KEY FK_idx (vehicl),"+
            //    "CONSTRAINT FK_idx FOREIGN KEY (vehicl) REFERENCES vehicles (id)"+
            `id` int NOT NULL AUTO_INCREMENT,
  `name_employ` varchar(45) DEFAULT NULL,
  `family_employ` varchar(45) DEFAULT NULL,
  `id_personnel` int DEFAULT NULL,
            `date_birth` date DEFAULT NULL,
            `work_unit` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_idx` (`work_unit`),
    CONSTRAINT `FK` FOREIGN KEY (`work_unit`) REFERENCES `workunit` (`id_unit`)
    */









    public Integer save(Drivers driver) throws SQLException {
        Integer i = null;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into driver(name,family,user_name,phone,vehicl,balance,status) " +
                            "values ('%s','%s',%d,'%s',%d,'%s','%s')", driver.getName(), driver.getFamily()
                    , driver.getUserName(), driver.getPhoneNumber(),
                    driver.getVehicles().getModel(), driver.getBalance(),driver.getStatus().getStatus());
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
                if (resultSet.getString("status").equals(StatusTravel.ABSENT)) {
                    Drivers drivers = new Drivers(resultSet.getString("name"), resultSet.getString("family"),
                            resultSet.getInt("user_name"),
                            resultSet.getString("phone"), Double.parseDouble(resultSet.getString("balance")
                    ), StatusTravel.ABSENT);
                    arrayList.add(drivers);
                }else
                  if (resultSet.getString("status").equals(StatusTravel.PRESENCE)) {
                    Drivers drivers = new Drivers(resultSet.getString("name"), resultSet.getString("family"),
                            resultSet.getInt("user_name"),
                            resultSet.getString("phone"), Double.parseDouble(resultSet.getString("balance")
                    ), StatusTravel.PRESENCE);
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
