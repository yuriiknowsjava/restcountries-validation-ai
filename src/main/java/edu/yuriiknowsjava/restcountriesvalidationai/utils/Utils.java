package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.InternalServerException;
import edu.yuriiknowsjava.restcountriesvalidationai.exceptions.ServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {
    private Utils() {
    }

    public static URI createUri(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            log.error(String.format("Malformed URI %s", str), e);
            throw new InternalServerException();
        }
    }

    public static HttpResponse<String> sendRequest(HttpClient httpClient, HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error("Cannot make a request to {} {}", request.method(), request.uri());
            Thread.currentThread().interrupt();
            throw new ServiceUnavailableException();
        }
    }

    public static List<Map<String, Object>> readListOfObjects(ObjectMapper objectMapper, String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Cannot part {}", jsonStr);
            throw new InternalServerException();
        }
    }
}
