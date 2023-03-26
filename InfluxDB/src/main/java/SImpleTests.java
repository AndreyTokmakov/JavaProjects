//import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SImpleTests {

    @Measurement(name = "memory")
    private final static class DataPoint {

        @Column(name = "time")
        private Instant time;

        @Column(name = "location")
        private String location;

        @Column(name = "water_level")
        private Double water_level;
    }

    public static void main(String ... params)
    {
        System.out.println("App started.");

        final String databaseURL = "http://demo.knowi.com:8086";
        final String userName = "admin";
        final String password = "admin";

        InfluxDB influxDB = InfluxDBFactory.connect(databaseURL, userName, password);
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

        Pong response = influxDB.ping();
        if (response.getVersion().equalsIgnoreCase("unknown")) {
            System.err.println("Error pinging server.");
            return;
        }

        Query query = new Query("select \"time\", \"location\", \"water_level\" from h2o_feet limit 1000",
                "NOAA_water_database");
        final QueryResult queryResult = influxDB.query(query, TimeUnit.SECONDS);
        // System.out.println(queryResult);


        /*
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<DataPoint> dataPoints = resultMapper.toPOJO(queryResult, DataPoint.class);
        */

        final List<QueryResult.Result> results = queryResult.getResults();

        /*
        System.out.println("Size = " + results.size());
        for (final QueryResult.Result result : results) {
            System.out.println(result.toString());
        }*/

        final List<QueryResult.Series> seriesList = results.get(0).getSeries();

        /*
        for (final QueryResult.Series series : seriesList) {
            System.out.println(series.toString());
        }*/

        final QueryResult.Series series = seriesList.get(0);

        System.out.println(series.getName());
        System.out.println(series.getColumns());
        System.out.println(series.getTags());
        for (List<Object> objs: series.getValues()) {
            System.out.println(objs);
        }
    }
}
