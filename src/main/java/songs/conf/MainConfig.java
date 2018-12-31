package songs.conf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.context.annotation.*;
import songs.ArtistJudge;

import java.util.List;

@Configuration
@ComponentScan("songs")
@PropertySource("classpath:user.properties")
public class MainConfig {
    @Bean
    public Broadcast<UserConfig> userConfigBroadcast(UserConfig userConfig, JavaSparkContext sc) {
        return sc.broadcast(userConfig);
    }

    @Bean
    public JavaSparkContext sc(SparkConf conf) {
        return new JavaSparkContext(conf);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        List<String> beatles = ctx.getBean(ArtistJudge.class).topX("beatles", 3);
        System.out.println(beatles);
    }
}
