package aop;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ShakespearQuoter implements Quoter {
    @Override
    @Scheduled(fixedDelay = 1000)
    @Profiling
    public void sayQuote() {
        System.out.println("2b || !2b");
    }
}
