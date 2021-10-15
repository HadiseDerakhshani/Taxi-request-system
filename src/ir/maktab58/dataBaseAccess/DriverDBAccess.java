package ir.maktab58.dataBaseAccess;

import ir.maktab58.enums.StatusTravel;
import ir.maktab58.model.Drivers;
import ir.maktab58.model.Passengers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void creatTable() throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE driver(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "  balance INT," +
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
            if (search(driver.getUserName()) == null) {
                Statement statement = getConnection().createStatement();
                String sqlQuery = String.format("insert into driver(name,family,user_name,phone,vehicl,balance,status,location) " +
                                "values ('%s','%s',%d,'%s',%d,%d,'%s','%s')", driver.getName(), driver.getFamily()
                        , driver.getUserName(), driver.getPhoneNumber(),
                        driver.getVehicles().getId(), driver.getBalance(), driver.getStatus().getStatus());
                i = statement.executeUpdate(sqlQuery);
                System.out.println("Add driver successful");
            }
        } else {
            System.out.println("----connection is empty----");
            i = null;
        }
        return i;
    }

    @Override
    public void updateBalance(int id, int balance) throws SQLException {
        if (getConnection() != null) {
            Drivers drivers=findByUserName(id);
            String sqlQuery = String.format("update driver set balance=balance + ? where user_name=? ");
            PreparedStatement update = getConnection().prepareStatement(sqlQuery);
            update.setInt(1, balance);
            update.setInt(2, drivers.getUserName());
            update.executeUpdate();
        } else
            System.out.println("----connection is empty----");
    }

    @Override
    public Integer search(int id) throws SQLException {
        String sqlQuery = String.format("select user_name from  driver  where user_name = ?");
        PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
        findId.setInt(1, id);
        ResultSet resultSet = findId.executeQuery();
        while (resultSet.next()) {
         return    resultSet.getInt(1);
        }
        return null;
    }

    public List<Drivers> showList() throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = String.format("select * from driver ");
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            List<Drivers> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                    arrayList.add(setDrivers(resultSet));
            }
           return arrayList;
        } else
            System.out.println("----Connection Is Empty----");
        return null;
    }

    public void showDriver(int id) throws SQLException {

        System.out.println(findByUserName(id));
    }

    public Drivers findByUserName(int id) throws SQLException {
        String sqlQuery = String.format("select * from  driver where user_name = ?");
        PreparedStatement find = getConnection().prepareStatement(sqlQuery);
        find.setInt(1, id);
        ResultSet resultSet=find.executeQuery(sqlQuery);
        Drivers drivers= setDrivers(resultSet);
        return drivers;
    }

    public Drivers findByLocation(String locationOrigin) throws SQLException {
        int amountTrip;
        int amountDriver;
        String location;
        String sqlQuery = String.format("select * from  driver ");
        PreparedStatement findLocation = getConnection().prepareStatement(sqlQuery);
        ResultSet resultSet = findLocation.executeQuery();
        ArrayList<Drivers> arrayList = new ArrayList<>();
        ArrayList<Drivers> arrayListFind = new ArrayList<>();
        Drivers drivers ;
        while (resultSet.next()) {
            if (resultSet.getString("status").equals(StatusTravel.WAITING)) {
                location = resultSet.getString("location");
                String[] splitLocationDriver = location.split(",");
                amountDriver = 1000 * (Integer.parseInt(splitLocationDriver[0]) + Integer.parseInt(splitLocationDriver[1]));
                drivers = new Drivers(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("family"),
                        resultSet.getInt("user_name"),
                        resultSet.getString("phone"), resultSet.getInt("balance")
                        , StatusTravel.WAITING, resultSet.getString("location"), amountDriver);
                arrayList.add(drivers);
            }
        }


        String[] splitLocationOrigin = locationOrigin.split(",");
        amountTrip = 1000 * (Integer.parseInt(splitLocationOrigin[0]) + Integer.parseInt(splitLocationOrigin[1]));
        for (Drivers dri : arrayList) {
            if ((dri.getAmount() - amountTrip) >= 0) {
                dri.setAmount((dri.getAmount() - amountTrip));
                arrayListFind.add(dri);
            }
        }
        drivers = null;
        Drivers drivers1 = arrayListFind.get(0);
        int min = drivers1.getAmount();
        for (Drivers driFind : arrayListFind) {
            if (driFind.getAmount() < min) {
                min = driFind.getAmount();
                drivers = driFind;
            }
        }
        if (drivers == null)
            drivers = drivers1;
        return drivers;
    }
    public Drivers setDrivers(ResultSet resultSet) throws SQLException {
            Drivers drivers = new Drivers();

        while (resultSet.next()) {
            drivers.setName(resultSet.getString("name"));
            drivers.setFamily(resultSet.getString("family"));
            drivers.setUserName( resultSet.getInt("user_name"));
            drivers.setPhoneNumber(resultSet.getString("phone"));
            drivers.setBalance(resultSet.getInt("balance"));

            if (resultSet.getString("status").equals(StatusTravel.WAITING.getStatus())) {
                drivers.setStatus(StatusTravel.WAITING);
            } else if (resultSet.getString("status").equals(StatusTravel.DOING.getStatus())) {
                drivers.setStatus(StatusTravel.DOING);
            }

        }
        return drivers;
    }
public void updateStatus( int id,StatusTravel statusTravel) throws SQLException {
    Drivers drivers=findByUserName(id);
    String sqlQuery = String.format("update driver set status=? where user_name=? ");
    PreparedStatement update = getConnection().prepareStatement(sqlQuery);
    update.setString(1, statusTravel.getStatus());
    update.setInt(2, drivers.getUserName());
    update.executeUpdate();
    }

    public void updateLocation( int id,String location) throws SQLException {
        Drivers drivers=findByUserName(id);
        String sqlQuery = String.format("update driver set location=? where user_name=? ");
        PreparedStatement update = getConnection().prepareStatement(sqlQuery);
        update.setString(1, location);
        update.setInt(2, drivers.getUserName());
        update.executeUpdate();

}
}

