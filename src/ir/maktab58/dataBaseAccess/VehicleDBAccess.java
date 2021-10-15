package ir.maktab58.dataBaseAccess;

import ir.maktab58.model.Vehicles;

import java.sql.*;

public class VehicleDBAccess extends DBAccess {
    public VehicleDBAccess() throws ClassNotFoundException, SQLException {
        if (getConnection() != null) {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet tables = metaData.getTables(null, null, "vehicles", null);
            if (!tables.next()) {
                creatTable();
            }
        }
    }

    @Override
    public void creatTable() throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE vehicles(" +
                "    id INT NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(25)," +
                "    color VARCHAR(25)," +
                "    model INT," +
                "    plate VARCHAR(25)," +
                "  type VARCHAR(25) ," +
                "    PRIMARY KEY (id) " +
                "    );");
    }
    public int save(Vehicles vehicles) throws SQLException {
        int id=0;
        if (getConnection() != null) {


            String sqlQuery = String.format("insert into vehicles(name,color,model,plate,type) " +
                            "values ('%s','%s',%d,'%s','%s')", vehicles.getName(), vehicles.getColor()
                    , vehicles.getModel(), vehicles.getPlateNumber(),
                    vehicles.getType().getType());


            PreparedStatement  statement = getConnection().prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
           statement.executeLargeUpdate();
           ResultSet rs = statement.getGeneratedKeys();
            if( rs.next()){
                id=rs.getInt(1);
            }
            System.out.println("Add vehicles successful");
        } else {
            System.out.println("----connection is empty----");
        }
        return id;
    }
}