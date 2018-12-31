package songs.conf;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Prod
public class ProdConfig {
    @Bean
    public SparkConf conf(){
        return new SparkConf();
    }
}
