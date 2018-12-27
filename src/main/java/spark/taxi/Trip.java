package spark.taxi;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Builder
@Getter
@ToString
public class Trip implements Serializable {
    int driverId;
    String destination;
    int distance;
    LocalDateTime date;

    public static Trip fromString(String t) {
        String[] s = t.split(" ",4);
        return Trip.builder()
                .driverId(Integer.parseInt(s[0]))
                .destination(s[1])
                .distance(Integer.parseInt(s[2]))
                .date(LocalDateTime.parse(s[3],
                        DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss zzz yyyy", Locale.US)))
                .build();
    }

    public static void main(String[] args) {
        Trip trip = fromString("119 boston 2 Sat Feb 20 12:00:06 IST 2016");
        System.out.println(trip);
    }
}
