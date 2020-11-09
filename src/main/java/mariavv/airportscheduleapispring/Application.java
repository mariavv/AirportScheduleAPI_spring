package mariavv.airportscheduleapispring;

import org.postgresql.ds.PGSimpleDataSource;

public enum Application {

    INSTANCE;

    private PGSimpleDataSource dataSource;

    public PGSimpleDataSource dataSource() {
        if (dataSource ==null) {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            //ds.setServerNames(new String[]{"localhost"});
            //ds.setDatabaseName("air_s");
            ds.setURL("jdbc:postgresql://localhost:8081/air_s");
            ds.setUser("user");
            ds.setPassword("123");
            dataSource = ds;
        }
        return dataSource;
    }
}
