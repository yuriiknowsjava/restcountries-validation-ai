package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Filters {
    private Filters() {
    }

    public static List<Map<String, Object>> searchByName(List<Map<String, Object>> countries, String name) {
        if (name == null || name.isBlank()) {
            return List.of();
        }
        var expectedName = name.toLowerCase();
        return countries.stream()
                .filter(Objects::nonNull)
                .filter(map -> {
                    var actualName = map.get("name");
                    return actualName instanceof String && ((String) actualName).toLowerCase().contains(expectedName);
                })
                .collect(Collectors.toList());
    }

    /**
     *
     * @param countries - List of countries
     * @param populationUpperBound - upper bound, in millions, for instance, 10 means 10 million.
     * @return list of countries having less than populationUpperBound million people.
     */
    public static List<Map<String, Object>> searchByPopulationUpperBound(List<Map<String, Object>> countries, BigDecimal populationUpperBound) {
        if (populationUpperBound == null) {
            return List.of();
        }
        return countries.stream()
                .filter(Objects::nonNull)
                .filter(map -> {
                    var population = map.get("population");
                    if (population == null) {
                        return false;
                    }
                    var actualPopulation = new BigDecimal(String.valueOf(population)).divide(new BigDecimal(1_000_000), RoundingMode.HALF_EVEN);
                    return actualPopulation.compareTo(populationUpperBound) < 0;
                })
                .collect(Collectors.toList());
    }
}
