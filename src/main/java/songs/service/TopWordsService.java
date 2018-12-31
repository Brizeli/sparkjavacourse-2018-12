package songs.service;

import org.apache.spark.api.java.JavaRDD;

import java.util.List;

public interface TopWordsService {
    List<String> topX(JavaRDD<String> lines, int x);
}
