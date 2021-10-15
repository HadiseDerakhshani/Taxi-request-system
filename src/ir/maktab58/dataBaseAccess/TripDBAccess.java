package ir.maktab58.dataBaseAccess;


import ir.maktab58.enums.StatusTravel;
import ir.maktab58.enums.StatusTrip;
import ir.maktab58.model.Drivers;
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

     /*   Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE trip(" +
                "    id_trip INT NOT NULL AUTO_INCREMENT," +
                "   origin VARCHAR(25)," +
                "    destination VARCHAR(25)," +
                "  status VARCHAR(25)," +
                "    driver INT," +
                "    passenger INT," +
                " FOREIGN KEY (driver) REFERENCES driver(id)," +
                " FOREIGN KEY (passenger) REFERENCES passenger(id)," +
                "    PRIMARY KEY (id_trip) " +
                "    );");
*/
    }
    public Integer save(Trip trip) throws SQLException {
        Integer id=null;
        if (getConnection() != null) {
            String sqlQuery = String.format("insert into trip(origin,destination,status,driver,passenger) " +
                            "values ('%s','%s','%s',%d,%d)",trip.getOrigin(),trip.getDestination(),
                    trip.getStatusTrip().getTrip(),trip.getDrivers(),trip.getPassengers());
            PreparedStatement  statement = getConnection().prepareStatement(sqlQuery,
                    Statement.RETURN_GENERATED_KEYS);
            statement.executeLargeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if( rs.next()){
                id=rs.getInt(1);
            }
            System.out.println("Add passenger successful");
        } else{
            System.out.println("----connection is empty----");
            id=null;
        }
        return id;
    }
    public void updateStatus(int id, StatusTrip statusTrip) throws SQLException, ClassNotFoundException {
        Trip trip= findByTripNumber(id);
        String sqlQuery = String.format("update trip set status=? where id_trip=? ");
        PreparedStatement update = getConnection().prepareStatement(sqlQuery);
        update.setString(1, statusTrip.getTrip());
        update.setInt(2, trip.getTripNumber());
        update.executeUpdate();
    }

    private Trip findByTripNumber(int id) throws SQLException, ClassNotFoundException {
        Trip trip;
        String sqlQuery = String.format("select * from trip where  id_trip= ?");
        PreparedStatement findId = getConnection().prepareStatement(sqlQuery);
        findId.setInt(1, id);
        ResultSet resultSet = findId.executeQuery();
        while (resultSet.next()) {

         return  setTrip(resultSet);
        }
        return null;
    }
    public Trip setTrip(ResultSet resultSet) throws SQLException, ClassNotFoundException {
       DriverDBAccess driverDao=new DriverDBAccess();
       PassengerDBAccess passengerDao=new PassengerDBAccess();
        Trip trip=new Trip();
        while (resultSet.next()) {
            trip.setTripNumber(resultSet.getInt(1));
           trip.setOrigin(resultSet.getString(2));
           trip.setDestination(resultSet.getString(3));
           trip.setDrivers(driverDao.findByUserName(resultSet.getInt(5)));
            trip.setPassengers(passengerDao.findByUserName(resultSet.getInt(6)));
            if (resultSet.getString(4).equals(StatusTrip.DOING.getTrip())) {
                trip.setStatusTrip(StatusTrip.DOING);
            } else if (resultSet.getString(4).equals(StatusTrip.ENDED.getTrip())) {
                trip.setStatusTrip(StatusTrip.ENDED);
            }
        }

        return trip;
    }
}
