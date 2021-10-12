package ir.maktab58.dataBaseAccess;

import ir.maktab58.enums.StatusTravel;
import ir.maktab58.model.Passengers;

import java.sql.*;
import java.util.ArrayList;

public class PassengerDBAccess extends DBAccess{
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
    public void creatTable() throws  SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE passenger(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    family VARCHAR(25)," +
                "    user_name INT," +
                "    phone VARCHAR(25)," +
                "  balance VARCHAR(25) ," +
                "    PRIMARY KEY (id) " +
                "    );");
    }
    public Integer save(Passengers passengers) throws SQLException {
        Integer i=null;
        if (getConnection() != null) {
            if(search(passengers.getUserName())==null) {
                Statement statement = getConnection().createStatement();
                String sqlQuery = String.format("insert into passenger(name,family,user_name,phone,balance) " +
                                "values ('%s','%s','%d','%s','%s')", passengers.getName(), passengers.getFamily()
                        , passengers.getUserName(), passengers.getPhoneNumber(), passengers.getBalance());
                i = statement.executeUpdate(sqlQuery);
                System.out.println("Add passenger successful");
            }
        } else{
            System.out.println("----connection is empty----");
            i=null;
        }
        return i;
    }

    @Override
    public void updateBalance(int id,double balance) throws SQLException {
        if (getConnection() != null) {
             double balanceOld = 0;
            String sqlQuery = String.format("select balance from  passenger where user_name = ?");
            PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
            findId.setInt(1, id);
            ResultSet resultSet = findId.executeQuery();

            while (resultSet.next()) {
               balance=Double.parseDouble(resultSet.getString(1));
            }


            sqlQuery = String.format("update passenger set balance = ? where user_name = ?");
            PreparedStatement updateBalance = getConnection().prepareStatement(sqlQuery);
            updateBalance.setString(1,String.valueOf(balance+balanceOld) );
            updateBalance.setInt(2, id);
            updateBalance.executeUpdate();
            System.out.println("Update successful");
        } else
            System.out.println("----connection is empty----");
    }

    @Override
    public Integer search(int id) throws SQLException {
        Integer userId=null;
        String sqlQuery = String.format("select user_name from  passenger where user_name = ?");
        PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
        findId.setInt(1, id);
        ResultSet resultSet = findId.executeQuery();

        while (resultSet.next()) {
            userId=resultSet.getInt(1);
        }
        return userId;
    }
    public void showList() throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = String.format("select * from passenger ");
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<Passengers> arrayList = new ArrayList<>();

            while (resultSet.next()) {

                if (resultSet.getString("status").equals(StatusTravel.WAITING)) {
                    Passengers passengers = new Passengers(resultSet.getString(2), resultSet.getString(3)
                            , resultSet.getInt(4), resultSet.getString(5)
                            , Double.parseDouble(resultSet.getString(6)),StatusTravel.WAITING);
                    arrayList.add(passengers);
                }else
                if (resultSet.getString("status").equals(StatusTravel.DOING)) {
                    Passengers passengers = new Passengers(resultSet.getString(2), resultSet.getString(3)
                            , resultSet.getInt(4), resultSet.getString(5)
                            , Double.parseDouble(resultSet.getString(6)),StatusTravel.DOING);
                    arrayList.add(passengers);
                }
            }
            for (Passengers pass : arrayList) {
                System.out.println(pass.toString());
            }
        } else
            System.out.println("----Connection Is Empty----");
    }
   public void showPassenger(int id) throws SQLException {
       System.out.println(findByUserName(id));
   }
   public Passengers findByUserName(int id) throws SQLException {
       String sqlQuery = String.format("select * from  passenger where user_name = ?");
       PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
       findId.setInt(1, id);
       ResultSet resultSet = findId.executeQuery();
       Passengers passengers=new Passengers();
       while (resultSet.next()) {
           if (resultSet.getString("status").equals(StatusTravel.WAITING)) {
               passengers = new Passengers(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3)
                       , resultSet.getInt(4), resultSet.getString(5)
                       , Double.parseDouble(resultSet.getString(6)),StatusTravel.WAITING);
           }else
           if (resultSet.getString("status").equals(StatusTravel.DOING)) {
               passengers = new Passengers(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3)
                       , resultSet.getInt(4), resultSet.getString(5)
                       , Double.parseDouble(resultSet.getString(6)),StatusTravel.DOING);
           }

       }
       return passengers;
   }
   public boolean checkBalance(Passengers passengers,int amount){
        if(passengers.getBalance()>=amount)
            return true;
        return false;
   }
}
