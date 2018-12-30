package spark.taxi;

import scala.Tuple2;

public class TaxiFunctions {
    public static Tuple2<Integer, String> lineToPair(java.lang.String s) {
        String[] sp = s.split(", ");
        return new Tuple2<>(Integer.parseInt(sp[0]),sp[1]);
    }
}
