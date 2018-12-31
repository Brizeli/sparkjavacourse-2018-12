package spark.taxi;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
public class Main {

    private static final String BOSTON = "boston";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("taxi").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> tripRawData = sc.textFile("data/taxi_order.txt");
        long count = tripRawData.count();
//        System.out.println("count = " + count);
//        tripRawData.collect().forEach(System.out::println);
        JavaRDD<Trip> tripRdd = tripRawData.map(String::toLowerCase).map(Trip::fromString);
        tripRdd.persist(StorageLevel.MEMORY_AND_DISK());
        System.out.println("All trips to Boston over 10 km");
        Accumulator<Integer> accumulator = sc.accumulator(0, "5 km");
        JavaRDD<Trip> bostonRDD = tripRdd
                .filter(t -> {
                    accumulator.add(1);
                    return t.getDestination().equals(BOSTON);
                });
        JavaRDD<Trip> bostonOverTenKm = bostonRDD
                .filter(t -> t.distance >= 10);
        System.out.println(bostonOverTenKm);
//        bostonOverTenKm.forEach(System.out::println);
        System.out.println("Total km travelled to Boston");
        Double bostonTotalKm = bostonRDD
                .mapToDouble(Trip::getDistance)
                .sum();
        System.out.println(bostonTotalKm);
        JavaRDD<String> driverRawData = sc.textFile("data/drivers.txt");
        JavaRDD<Driver> driverRdd = driverRawData.map(Driver::fromString);
        System.out.println("Top 3 drivers travelled maximum");
        List<Tuple2<Integer, Driver>> topThreeDrivers = tripRdd
                .mapToPair(t -> new Tuple2<>(t.getDriverId(), t.getDistance()))
                .reduceByKey(Integer::sum)
                .join(driverRdd.mapToPair(d -> new Tuple2<>(d.getId(), d)))
                .mapToPair(Tuple2::_2)
                .top(3,
//                        (Comparator<Tuple2> & Serializable) (Comparator.comparingInt(Tuple2::_1)));
                        new MyComp());
  /* instead of .top() to avoid Comparator
                .sortByKey(false)
                .take(3);*/
        topThreeDrivers.forEach(System.out::println);
        System.out.println("another solution");
        JavaPairRDD<Integer, Integer> idToKm = tripRdd.mapToPair(t -> new Tuple2<>(t.getDriverId(), t.getDistance()))
                .reduceByKey(Integer::sum);
        JavaPairRDD<Integer, String> driversRdd = driverRawData.mapToPair(TaxiFunctions::lineToPair);
        driversRdd.join(idToKm)
                .mapToPair(t -> t._2().swap())
                .sortByKey(false)
                .take(3)
                .forEach(System.out::println);

    }
}
