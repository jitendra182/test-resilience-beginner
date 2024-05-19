package com.jitendra.testresiliencebeginner.controlle;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private RestTemplate restTemplate;

    final List<String> students = List.of("Ram", "Shyam", "Ashok");

    @GetMapping()
    public ResponseEntity<?> getStudent() {
        return ResponseEntity.ok(students);
    }


    @GetMapping("/with-api")
    public ResponseEntity<?> getStudentsByApi() {
        var data = restTemplate.getForEntity("/", ArrayList.class);
        return ResponseEntity.ok(data.getBody());
    }

    @GetMapping("/with-api-fallback")
    @CircuitBreaker(name = "studentApiCB", fallbackMethod = "getStudentsByApiFallback")
    public ResponseEntity<?> getStudentsByApiFallback() {
        var data = restTemplate.getForEntity("/", ArrayList.class);
        return ResponseEntity.ok(data.getBody());
    }


    public ResponseEntity<?> getStudentsByApiFallback(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.ok("Default CircuitBreaker");
    }


    @GetMapping("/with-api-retry")
    @Retry(name = "studentApiRetry", fallbackMethod = "getStudentsByApiRetry")
    public ResponseEntity<?> getStudentsByApiRetry() {
        System.out.println("Making request "+ LocalTime.now());
        var data = restTemplate.getForEntity("/", ArrayList.class);
        return ResponseEntity.ok(data.getBody());
    }


    public ResponseEntity<?> getStudentsByApiRetry(Exception ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.ok("Default Retry");
    }


    @GetMapping("/with-api-ratelimiter")
    @RateLimiter(name = "studentApiRateLimiter", fallbackMethod = "getStudentsByApiRateLimiter")
    public ResponseEntity<?> getStudentsByApiRateLimiter() {

        var data = restTemplate.getForEntity("/", ArrayList.class);
        return ResponseEntity.ok(data.getBody());
    }


    public ResponseEntity<?> getStudentsByApiRateLimiter(Exception ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.ok("Default RateLimiter");
    }



    @GetMapping("/with-api-timelimiter")
    @TimeLimiter(name = "studentApiTimeLimiter", fallbackMethod = "getStudentsByApiTimeLimiter")
    public CompletableFuture<ResponseEntity<?>> getStudentsByApiTimeLimiter() {

        return CompletableFuture.supplyAsync(()->{
            var data = restTemplate.getForEntity("/slow-response", ArrayList.class);
            return ResponseEntity.ok(data.getBody());
        });
    }


    public CompletableFuture<ResponseEntity<?>> getStudentsByApiTimeLimiter(Exception ex) {
        System.out.println(ex.getMessage());
        return CompletableFuture.supplyAsync( ()->  ResponseEntity.ok("Default TimeLimiter"));
    }


    @GetMapping("/with-api-bulkhead")
    @Bulkhead(name = "studentApiBulkHeadThreadPool", fallbackMethod = "getStudentsByApiBulkHead", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<ResponseEntity<?>> getStudentsByApiBulkHead() {

        return CompletableFuture.supplyAsync(()->{
            var data = restTemplate.getForEntity("/slow-response", ArrayList.class);
            return ResponseEntity.ok(data.getBody());
        });
    }


    public CompletableFuture<ResponseEntity<?>> getStudentsByApiBulkHead(Exception ex) {
        System.out.println(ex.getMessage());
        return CompletableFuture.supplyAsync( ()->  ResponseEntity.ok("Default BulkHeadThreadPool"));
    }

}
