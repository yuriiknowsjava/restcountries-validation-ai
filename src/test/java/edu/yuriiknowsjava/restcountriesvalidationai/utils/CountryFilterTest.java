package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CountryFilterTest {

    private CountryDto country;

    @BeforeEach
    void setUp() {
        country = new CountryDto();
        var countryName = new CountryName();
        countryName.setCommon("Germany");
        countryName.setOfficial("Federal Re of Germany");
        country.setName(countryName);
        country.setPopulation(new BigDecimal(83_100_000)); // 83.1 million
    }

    @Test
    void testCountryNamePredicateMatchingOfficialName() {
        assertTrue(CountryFilter.countryNamePredicate("Federal").test(country));
    }

    @Test
    void testCountryNamePredicateNotMatching() {
        assertFalse(CountryFilter.countryNamePredicate("Italy").test(country));
    }

    @Test
    void testCountryNamePredicateBlank() {
        assertTrue(CountryFilter.countryNamePredicate("").test(country));
    }

    @Test
    void testCountryNamePredicateNull() {
        assertTrue(CountryFilter.countryNamePredicate(null).test(country));
    }

    @Test
    void testGetOfficialNameNullSafe() {
        assertEquals("Federal Re of Germany", CountryFilter.getOfficialNameNullSafe(country));
    }

    @Test
    void testGetOfficialNameNullSafeWithNullCountry() {
        assertEquals("", CountryFilter.getOfficialNameNullSafe(null));
    }

    @Test
    void testGetCommonNameNullSafe() {
        assertEquals("Germany", CountryFilter.getCommonNameNullSafe(country));
    }

    @Test
    void testGetCommonNameNullSafeWithNullCountry() {
        assertEquals("", CountryFilter.getCommonNameNullSafe(null));
    }

    @Test
    void testPopulationPredicateWithinUpperBound() {
        assertTrue(CountryFilter.isPopulationSizeLessThan(new BigDecimal("100")).test(country)); // 100 million as upper bound
    }

    @Test
    void testPopulationPredicateOutsideUpperBound() {
        assertFalse(CountryFilter.isPopulationSizeLessThan(new BigDecimal("50")).test(country)); // 50 million as upper bound
    }

    @Test
    void testPopulationPredicateRounding() {
        assertFalse(CountryFilter.isPopulationSizeLessThan(new BigDecimal(83)).test(country));
        assertTrue(CountryFilter.isPopulationSizeLessThan(new BigDecimal(84)).test(country));
    }

    @Test
    void testPopulationPredicateNullUpperBound() {
        assertTrue(CountryFilter.isPopulationSizeLessThan(null).test(country));
    }

    @Test
    void testPopulationPredicateCountryWithNullPopulation() {
        country.setPopulation(null);
        assertFalse(CountryFilter.isPopulationSizeLessThan(new BigDecimal("100")).test(country));
    }
}
