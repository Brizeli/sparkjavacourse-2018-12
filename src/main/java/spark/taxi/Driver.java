package spark.taxi;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@ToString
public class Driver implements Serializable {
    int id;
    String name, address, email;

    public static Driver fromString(String s) {
        String[] sp = s.split(", ");
        return Driver.builder()
                .id(Integer.parseInt(sp[0]))
                .name(sp[1])
                .address(sp[2])
                .email(sp[3])
                .build();
    }
}
