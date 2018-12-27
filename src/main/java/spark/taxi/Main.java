package spark.taxi;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("taxi").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> tripRawData = sc.textFile("data/taxi_order.txt");
        long count = tripRawData.count();
//        System.out.println("count = " + count);
//        tripRawData.collect().forEach(System.out::println);
        JavaRDD<Trip> tripRdd = tripRawData.map(Trip::fromString);
        System.out.println("All trips to Boston over 10 km");
        List<Trip> bostonOverTenKm = tripRdd
                .filter(t -> t.getDestination().equalsIgnoreCase("boston") && t.distance >= 10)
                .collect();
        bostonOverTenKm.forEach(System.out::println);
        System.out.println("Total km travelled to Boston");
        Double bostonTotalKm = tripRdd
                .filter(t -> t.getDestination().equalsIgnoreCase("boston"))
                .mapToDouble(Trip::getDistance)
                .sum();
        System.out.println(bostonTotalKm);
        JavaRDD<String> driverRawData = sc.textFile("data/drivers.txt");
        JavaRDD<Driver> driverRdd = driverRawData.map(Driver::fromString);
        System.out.println("Top 3 drivers travelled maximum");
        List<Tuple2<Integer, Driver>> topThreeDrivers = tripRdd.mapToPair(t -> new Tuple2<>(t.getDriverId(), t.getDistance()))
                .reduceByKey((d1, d2) -> d1 + d2)
//                .mapToPair(Tuple2::swap)
//                .sortByKey(false)
//                .mapToPair(Tuple2::swap)
                .join(driverRdd.mapToPair(d -> new Tuple2<>(d.getId(), d)))
                .mapToPair(t -> new Tuple2<>(t._2()._1(), t._2()._2()))
//                .top(3, new MyComp())
                .sortByKey(false)
                .take(3);
        topThreeDrivers.forEach(System.out::println);
    }
}
