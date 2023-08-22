package edu.yuriiknowsjava.restcountriesvalidationai;

import java.net.http.HttpClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestcountriesValidationAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestcountriesValidationAiApplication.class, args);
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
