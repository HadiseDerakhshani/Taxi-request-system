package ir.maktab58.dataBaseAccess;

import ir.maktab58.enums.StatusTravel;
import ir.maktab58.model.Drivers;
import ir.maktab58.model.Passengers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDBAccess extends DBAccess {
    public PassengerDBAccess() throws SQLException, ClassNotFoundException {
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "passenger", null);
            if (!tables.next()) {
                creatTable();
            }
        }
    }

    @Override
    public void creatTable() throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE passenger(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "    status VARCHAR(25)," +
                "  balance INT ," +
                "    PRIMARY KEY (id) " +
                "    );");
    }

    public Integer save(Passengers passengers) throws SQLException {
        Integer i = null;
        if (getConnection() != null) {
            if (search(passengers.getUserName()) == null) {
                Statement statement = getConnection().createStatement();
                String sqlQuery = String.format("insert into passenger(name,family,user_name,phone,status,balance) " +
                                "values ('%s','%s','%d','%s','%s',%d)", passengers.getName(), passengers.getFamily()
                        , passengers.getUserName(), passengers.getPhoneNumber(),
                        passengers.getStatus().getStatus(), passengers.getBalance());
                i = statement.executeUpdate(sqlQuery);
                System.out.println("Add passenger successful");
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
            String sqlQuery = String.format("update driver set balance=balance + ? where user_name=? ");
            PreparedStatement update = getConnection().prepareStatement(sqlQuery);
            update.setInt(1, balance);
            update.setInt(2, id);
            update.executeUpdate();
        } else
            System.out.println("----connection is empty----");
    }

    @Override
    public Integer search(int id) throws SQLException {
        Integer userId = null;
        String sqlQuery = String.format("select user_name from  passenger where user_name = ?");
        PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
        findId.setInt(1, id);
        ResultSet resultSet = findId.executeQuery();
        while (resultSet.next()) {
            userId = resultSet.getInt(1);
        }
        return userId;
    }

    public List<Passengers> showList() throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = String.format("select * from passenger");
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            List<Passengers> passengersList = new ArrayList<>();
            while (resultSet.next()) {
                passengersList.add(setPassengers(resultSet));
            }
            return passengersList;
        } else
            System.out.println("----Connection Is Empty----");
        return  null;
    }


    public void showPassenger(int id) throws SQLException {
        System.out.println(findByUserName(id));
    }

    public Passengers findByUserName(int id) throws SQLException {
        String sqlQuery = String.format("select * from  passenger where user_name = ?");
        PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
        findId.setInt(1, id);
        ResultSet resultSet = findId.executeQuery();
        Passengers passengers= setPassengers(resultSet);
        return passengers;
    }

    public Passengers setPassengers(ResultSet resultSet) throws SQLException {

        Passengers passengers = new Passengers();
        while (resultSet.next()) {
            passengers.setName(resultSet.getString(2));
            passengers.setFamily(resultSet.getString(3));
            passengers.setUserName(resultSet.getInt(4));
            passengers.setPhoneNumber(resultSet.getString(5));
            passengers.setBalance(resultSet.getInt(7));
            if (resultSet.getString(6).equals(StatusTravel.WAITING.getStatus())) {
                passengers.setStatus(StatusTravel.WAITING);
            } else if (resultSet.getString(6).equals(StatusTravel.DOING.getStatus())) {
                passengers.setStatus(StatusTravel.DOING);
            }
        }
        return passengers;
    }

    public boolean checkBalance(Passengers passengers, int amount) {
        if (passengers.getBalance() >= amount)
            return true;
        return false;
    }

    public void decreaseBalance(int id, int amount) throws SQLException {
        Passengers passengers = findByUserName(id);
        String sqlQuery = String.format("update passenger set balance=balance - ? where user_name=? ");
        PreparedStatement update = getConnection().prepareStatement(sqlQuery);
        update.setInt(1, amount);
        update.setInt(2, passengers.getId());
        update.executeUpdate();
    }
}
