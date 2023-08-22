package edu.yuriiknowsjava.restcountriesvalidationai.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.List;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryFilterDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.mock.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
public class CountryServiceImplIT {

    @Container
    public MockServerContainer mockServer = new MockServerContainer(DockerImageName.parse("mockserver/mockserver:5.14.0"));

    @Autowired
    private CountryServiceImpl countryService;

    @Test
    public void testGetCountries() {
        // Setup mock server response
        var expectedJson = "[{ \"name\": {\"common\":\"American Samoa\",\"official\":\"American Samoa\"} }, "
                + "{ \"name\": {\"common\":\"Non existent Samoa\",\"official\":\"Non existent Samoa\"} }]";

        try (var client = new MockServerClient(mockServer.getHost(), mockServer.getServerPort())) {
            Expectation[] getAllCountries = client.when(request("/all").withMethod("GET"))
                    .respond(response().withStatusCode(200).withBody(expectedJson));

            // Use the mockServer URL as the base URL for HTTP requests in CountryServiceImpl
            ReflectionTestUtils.setField(countryService, "restcountriesBaseUrl", mockServer.getEndpoint());

            CountryFilterDto filterDto = new CountryFilterDto();
            filterDto.setName("Samoa");
            filterDto.setPageSize(2);

            // Test
            List<CountryDto> result = countryService.getCountries(filterDto);

            // Assert
            assertEquals(2, result.size());
            assertEquals("American Samoa", result.get(0).getName().getCommon());
            for (var expectation : getAllCountries) {
                client.clear(expectation.getId());
            }
        }
    }
}
