package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class FiltersTest {

    @Test
    void searchByName() {
        var spain = "Spain";
        var estonia = "Estonia";
        List<Map<String, Object>> testData = List.of(
                Map.of("name", spain),
                Map.of("name", estonia)
        );
        verifySingleCountry(spain, Filters.searchByName(testData, "Sp"));
        verifySingleCountry(estonia, Filters.searchByName(testData, "st"));
        verifySingleCountry(estonia, Filters.searchByName(testData, "sT"));
    }

    private void verifySingleCountry(String expectedCountry, List<Map<String, Object>> actualResult) {
        assertEquals(1, actualResult.size());
        assertEquals(expectedCountry, actualResult.get(0).get("name"));
    }
}
