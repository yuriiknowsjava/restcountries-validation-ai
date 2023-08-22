package edu.yuriiknowsjava.restcountriesvalidationai.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public CountryService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public List<Map<String, Object>> getCountries() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://restcountries.com/v3.1/all"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch countries. HTTP Status Code: " + response.statusCode());
        }
        return objectMapper.readValue(response.body(), new TypeReference<>() {
        });
    }
}
