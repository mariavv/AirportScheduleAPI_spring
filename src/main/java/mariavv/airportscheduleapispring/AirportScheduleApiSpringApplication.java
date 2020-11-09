package mariavv.airportscheduleapispring;

import mariavv.airportscheduleapispring.dao.AirportDao;
import mariavv.airportscheduleapispring.entity.AirportEntity;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AirportScheduleApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportScheduleApiSpringApplication.class, args);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AirportApplicationContextConfiguration.class);
		AirportDao airportDao = ctx.getBean(AirportDao.class);
		AirportEntity airport1 = airportDao.findById(1);
		PGSimpleDataSource dataSource = ctx.getBean(PGSimpleDataSource.class);
	}
}