package songs.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import songs.conf.UserConfig;

import java.io.Serializable;
import java.util.List;
@Service
public class TopWordsServiceImpl implements TopWordsService, Serializable {
    @Autowired
//    private UserConfig userConfig;
    private Broadcast<UserConfig> userConfig;

    @Override
    public List<String> topX(JavaRDD<String> lines, int x) {
        lines.filter(s -> !userConfig.value().getGarbageWords().contains(s));
        return null;
    }
}
