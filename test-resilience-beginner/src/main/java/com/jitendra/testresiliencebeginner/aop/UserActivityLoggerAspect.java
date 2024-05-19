package com.jitendra.testresiliencebeginner.aop;

//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Aspect
@Component
public class UserActivityLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserActivityLoggerAspect.class);

    // Create a fixed-size pool of ExecutorService
    private final ExecutorService executor = Executors.newFixedThreadPool(5); // Adjust pool size as needed

//    @Before("execution(* com.jitendra.testresiliencebeginner..*(..))")
//    public void logUserActivityAsync(JoinPoint joinPoint) {
//        executor.submit(() -> {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            String methodName = joinPoint.getSignature().getName();
//            Object[] args = joinPoint.getArgs();
//            String username = ""; // retrieve username from your security context or method arguments
//            logger.info("User '{}' invoked method '{}'", username, methodName);
//            // You can log more details as needed based on your requirements
//        });
//    }
}
