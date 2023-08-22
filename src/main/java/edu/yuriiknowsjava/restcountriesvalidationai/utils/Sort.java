package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import java.util.Comparator;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;

public final class Sort {
    private Sort() {
    }

    public enum Order {
        ASD,
        DESC,
    }

    public static Comparator<CountryDto> countryNameComparator(Sort.Order sort) {
        return (countryA, countryB) -> {
            var nameIgnoreCaseComparator = Comparator.comparing(CountryFilter::getOfficialNameNullSafe, String::compareToIgnoreCase);
            if (sort == null || sort == Sort.Order.ASD) {
                return nameIgnoreCaseComparator.compare(countryA, countryB);
            }
            return nameIgnoreCaseComparator.reversed().compare(countryA, countryB);
        };
    }
}
