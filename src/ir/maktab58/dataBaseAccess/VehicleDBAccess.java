package ir.maktab58.dataBaseAccess;

import ir.maktab58.model.Vehicles;

import java.sql.*;

public class VehicleDBAccess extends DBAccess{
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
    public void creatTable() throws ClassNotFoundException, SQLException {
        java.sql.Connection connection = getConnection();
        Statement statement = connection.createStatement();
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

    public void save(Vehicles vehicles) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into vehicles(name,color,model,plate,type) " +
                            "values ('%s','%s','%d','%s','%s')", vehicles.getName(), vehicles.getColor()
                    , vehicles.getModel(), vehicles.getPlateNumber(),
                    vehicles.getType().getType());
             statement.executeUpdate(sqlQuery);
            System.out.println("Add vehicles successful");
        } else {
            System.out.println("----connection is empty----");
        }
    }
}
