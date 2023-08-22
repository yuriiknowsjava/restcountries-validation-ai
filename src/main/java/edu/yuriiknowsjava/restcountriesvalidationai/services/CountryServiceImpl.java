package edu.yuriiknowsjava.restcountriesvalidationai.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.ServiceUnavailableException;
import edu.yuriiknowsjava.restcountriesvalidationai.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String restcountriesBaseUrl;

    @Autowired
    public CountryServiceImpl(HttpClient httpClient, ObjectMapper objectMapper, @Value("${rescountries.api.base.url}") String restcountriesBaseUrl) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.restcountriesBaseUrl = restcountriesBaseUrl;
    }

    @Override
    public List<Map<String, Object>> getCountries() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(restcountriesBaseUrl + "/all"))
                .GET()
                .build();
        HttpResponse<String> response = Utils.sendRequest(httpClient, request);
        if (response.statusCode() != 200) {
            log.error("Failed to fetch countries. HTTP Status Code: {}; response body: {}", response.statusCode(), response.body());
            throw new ServiceUnavailableException();
        }
        return Utils.readListOfObjects(objectMapper, response.body());
    }
}
