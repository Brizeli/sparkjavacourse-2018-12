package aop;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilerAspect {
    @Before("@annotation(Profiling)")
    public void handleMethodsAnnotatedWithProfiling(JoinPoint jp) {
        System.out.println(jp.getTarget().getClass().getSimpleName() + " is going to run method");
        System.out.println(jp.getSignature().getName() + ":");
    }

    //    @Around("execution(* aop..*(..))")
    @SneakyThrows
    public void allMethods(ProceedingJoinPoint jp) {
        System.out.println("before");
        jp.proceed();
        System.out.println("after");
    }
}
