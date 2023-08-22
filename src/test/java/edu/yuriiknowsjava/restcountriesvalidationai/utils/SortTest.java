package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortTest {

    private CountryDto country1;
    private CountryDto country2;

    @BeforeEach
    void setUp() {
        country1 = new CountryDto();
        country2 = new CountryDto();

        var countryName1 = new CountryName();
        countryName1.setOfficial("Germany");

        var countryName2 = new CountryName();
        countryName2.setOfficial("France");

        country1.setName(countryName1);
        country2.setName(countryName2);
    }

    @Test
    void testCountryNameComparatorAscending() {
        Comparator<CountryDto> comparator = Sort.countryNameComparator(Sort.Order.ASC);

        int result = comparator.compare(country1, country2);

        assertEquals(1, result);  // Germany comes after France in alphabetical order
    }

    @Test
    void testCountryNameComparatorDescending() {
        Comparator<CountryDto> comparator = Sort.countryNameComparator(Sort.Order.DESC);

        int result = comparator.compare(country1, country2);

        assertEquals(-1, result);  // In descending order, Germany comes before France
    }

    @Test
    void testCountryNameComparatorNullSort() {
        Comparator<CountryDto> comparator = Sort.countryNameComparator(null);

        int result = comparator.compare(country1, country2);

        assertEquals(1, result);  // Default is ascending, so Germany comes after France
    }

    @Test
    void testCountryNameComparatorSameNames() {
        country2.getName().setOfficial("Germany");

        Comparator<CountryDto> comparator = Sort.countryNameComparator(Sort.Order.ASC);
        int result = comparator.compare(country1, country2);

        assertEquals(0, result);  // Names are the same, so result should be 0
    }

    @Test
    void testCountryNameComparatorIgnoreCase() {
        country1.getName().setOfficial("germany");
        country2.getName().setOfficial("FRANCE");

        Comparator<CountryDto> comparator = Sort.countryNameComparator(Sort.Order.ASC);
        int result = comparator.compare(country1, country2);

        assertEquals(1, result);  // Comparing should be case-insensitive, so germany still comes after France
    }

    @Test
    void testCountryNameComparatorIgnoreCaseSameCounty() {
        country1.getName().setOfficial("germany");
        country2.getName().setOfficial("GeRmAnY");

        Comparator<CountryDto> comparator = Sort.countryNameComparator(Sort.Order.ASC);
        int result = comparator.compare(country1, country2);

        assertEquals(0, result);  // Comparing should be case-insensitive, so germany still comes after France
    }

    // Further tests can include null name, or null official name.
}

