package songs;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import songs.conf.MainConfig;
import songs.service.TopWordsService;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MainConfig.class})
@ActiveProfiles("dev")
public class TopWordsServiceImplTest {
    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private TopWordsService tws;

    @Test
    public void topX() {
        List<String> strings = asList("java java java", "scala java", "java");
        JavaRDD<String> javaRDD = sc.parallelize(strings);
        List<String> ans = tws.topX(javaRDD, 1);
        Assert.assertEquals(1, ans.size());
        assertEquals("java", ans.get(0));
    }
}