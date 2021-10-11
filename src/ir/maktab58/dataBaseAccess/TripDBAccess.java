package ir.maktab58.dataBaseAccess;

import ir.maktab58.model.Passengers;
import ir.maktab58.model.Trip;

import java.sql.*;

public class TripDBAccess extends DBAccess {
    public TripDBAccess() throws ClassNotFoundException, SQLException {
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "trip", null);
            if (!tables.next()) {
                creatTable();
            }
        }
    }

    @Override
    public void creatTable() throws  SQLException {

        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE trip(" +
                "    id_trip INT NOT NULL AUTO_INCREMENT," +
                "   origin VARCHAR(25)," +
                "    destination VARCHAR(25)," +
                "  status VARCHAR(25)," +
                "    driver INT," +
                "    passenger INT," +
                " FOREIGN KEY (driver) REFERENCES driver(id)," +
                " FOREIGN KEY (passenger) REFERENCES passenger(id)," +
                "    PRIMARY KEY (id) " +
                "    );");

    }
    public Integer save(Trip trip) throws SQLException {
        Integer i=null;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into passenger(origin,destination,status,driver,passenger) " +
                            "values ('%s','%s','%s',%d,%d)",trip.getOrigin(),trip.getDestination(),
                    trip.getStatusTrip().getTrip(),trip.getDrivers(),trip.getPassengers());
            i=statement.executeUpdate(sqlQuery);
            System.out.println("Add passenger successful");
        } else{
            System.out.println("----connection is empty----");
            i=null;
        }
        return i;
    }

}
