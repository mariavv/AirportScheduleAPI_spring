package mariavv.airportscheduleapispring.dao;

import mariavv.airportscheduleapispring.entity.AirportEntity;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AirportDao {

    private final PGSimpleDataSource dataSource;

    @Autowired
    public AirportDao(PGSimpleDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AirportEntity findById(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("select * from airports where id = ?");
            selectStatement.execute();
            return new AirportEntity();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
