package songs.conf;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

import static java.util.Arrays.asList;

@Component
public class UserConfig implements Serializable {
    @Getter
    private List<String> garbageWords;

    @Value("${garbage}")
    public void setGarbageWords(String[] garbageWords) {
        this.garbageWords = asList(garbageWords);
    }


}
