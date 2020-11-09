package mariavv.airportscheduleapispring;

import mariavv.airportscheduleapispring.dao.AirportDao;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AirportApplicationContextConfiguration {

    @Bean
    public PGSimpleDataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        //ds.setServerNames(new String[]{"localhost"});
        //ds.setDatabaseName("air_s");
        dataSource.setURL("jdbc:postgresql://localhost:8089/air_s");
        dataSource.setUser("user");
        dataSource.setPassword("123");
        return dataSource;
    }

//    @Bean
//    public AirportDao airportDao() {
//        return new AirportDao(dataSource());
//    }
}
