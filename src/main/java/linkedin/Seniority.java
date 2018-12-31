package linkedin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Seniority {
    JUNIOR(0, 1000, "צ"), MIDDLE(1001, 2000, "ע"), SENIOR(2001, Integer.MAX_VALUE, "מ");
    private final int min;
    private final int max;
    private final String hebDesc;

    public static Seniority bySalary(long salary) {
        return Arrays.stream(values())
                .filter(s -> s.min < salary)
                .filter(s -> s.max >= salary)
                .findAny().orElse(JUNIOR);//get()
    }
}
