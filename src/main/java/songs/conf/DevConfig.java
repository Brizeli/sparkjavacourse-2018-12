package songs.conf;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@Dev
@EnableAspectJAutoProxy
public class DevConfig {
    @Bean
    public SparkConf conf(){
        return new SparkConf().setAppName("songs").setMaster("local[*]");
    }
}
