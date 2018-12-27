package spark.taxi;

import scala.Tuple2;

import java.io.Serializable;
import java.util.Comparator;

public class MyComp implements Comparator<Tuple2<Integer, Tuple2<Integer, Driver>>>, Serializable {

    @Override
    public int compare(Tuple2<Integer, Tuple2<Integer, Driver>> o1, Tuple2<Integer, Tuple2<Integer, Driver>> o2) {
        return o1._2()._1() - o2._2()._1();
    }
}
