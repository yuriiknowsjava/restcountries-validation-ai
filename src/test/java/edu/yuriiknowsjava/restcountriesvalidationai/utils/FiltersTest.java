package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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

    @Test
    void searchByPopulationUpperBound() {
        var eightMillion = 8_000_000;
        List<Map<String, Object>> testData = List.of(
                Map.of("population", 11_000_000),
                Map.of("population", 8_000_000)
        );
        var result = Filters.searchByPopulationUpperBound(testData, new BigDecimal(10));
        assertEquals(1, result.size());
        assertEquals(eightMillion, result.get(0).get("population"));
    }
}
