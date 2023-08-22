package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Sort {
    private Sort() {
    }

    public enum Order {
        ASD,
        DESC,
    }

    public static List<Map<String, Object>> sortByName(List<Map<String, Object>> countries, Sort.Order sort) {
        return countries.stream()
                .filter(Objects::nonNull)
                .sorted(nameComparator(sort))
                .collect(Collectors.toList());
    }

    private static Comparator<Map<String, Object>> nameComparator(Sort.Order sort) {
        return (countryA, countryB) -> {
            var nameIgnoreCaseComparator = Comparator.comparing(Filters::getNameNullSafe, String::compareToIgnoreCase);
            if (sort == null || sort == Sort.Order.ASD) {
                return nameIgnoreCaseComparator.compare(countryA, countryB);
            }
            return nameIgnoreCaseComparator.reversed().compare(countryA, countryB);
        };
    }
}
