package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.Predicate;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryName;

public final class CountryFilter {
    private CountryFilter() {
    }

    public static Predicate<CountryDto> countryNamePredicate(String countryName) {
        if (countryName == null || countryName.isBlank()) {
            return country -> true;
        }
        Predicate<CountryDto> isOfficialName = country -> {
            var actualName = getOfficialNameNullSafe(country);
            return actualName.toLowerCase().contains(countryName.toLowerCase());
        };
        Predicate<CountryDto> isCommonName = country -> {
            var actualName = getCommonNameNullSafe(country);
            return actualName.toLowerCase().contains(countryName.toLowerCase());
        };
        return isOfficialName.or(isCommonName);
    }

    public static String getOfficialNameNullSafe(CountryDto countryDto) {
        return Optional.ofNullable(countryDto)
                .map(CountryDto::getName)
                .map(CountryName::getOfficial)
                .orElse("");
    }

    public static String getCommonNameNullSafe(CountryDto countryDto) {
        return Optional.ofNullable(countryDto)
                .map(CountryDto::getName)
                .map(CountryName::getOfficial)
                .orElse("");
    }

    /**
     *
     * @param populationUpperBound - upper bound, in millions, for instance, 10 means 10 million.
     * @return {@link edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto} containing country information.
     */
    public static Predicate<CountryDto> populationPredicate(BigDecimal populationUpperBound) {
        if (populationUpperBound == null) {
            return country -> true;
        }
        return countryDto -> {
            var population = countryDto.getPopulation();
            if (population == null) {
                return false;
            }
            var actualPopulation = population.divide(new BigDecimal(1_000_000), 2, RoundingMode.HALF_DOWN);
            return actualPopulation.compareTo(populationUpperBound) < 0;
        };
    }
}
