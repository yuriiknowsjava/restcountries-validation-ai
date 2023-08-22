package edu.yuriiknowsjava.restcountriesvalidationai.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryFilterDto;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.ServiceUnavailableException;
import edu.yuriiknowsjava.restcountriesvalidationai.utils.CountryFilter;
import edu.yuriiknowsjava.restcountriesvalidationai.utils.Sort;
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
    public List<CountryDto> getCountries(CountryFilterDto countryFilterDto) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(Utils.createUri(restcountriesBaseUrl + "/all"))
                .GET()
                .build();
        HttpResponse<String> response = Utils.sendRequest(httpClient, request);
        if (response.statusCode() != 200) {
            log.error("Failed to fetch countries. HTTP Status Code: {}; response body: {}", response.statusCode(), response.body());
            throw new ServiceUnavailableException();
        }
        return Utils.readListOfObjects(objectMapper, response.body())
                .stream()
                .filter(CountryFilter.countryNamePredicate(countryFilterDto.getName()))
                .filter(CountryFilter.populationPredicate(countryFilterDto.getPopulation()))
                .sorted(Sort.countryNameComparator(countryFilterDto.getOrder()))
                .limit(countryFilterDto.getPageSize())
                .collect(Collectors.toList());
    }
}
