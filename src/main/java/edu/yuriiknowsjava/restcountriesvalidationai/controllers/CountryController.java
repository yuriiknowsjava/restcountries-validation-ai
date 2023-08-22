package edu.yuriiknowsjava.restcountriesvalidationai.controllers;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryFilterDto;
import edu.yuriiknowsjava.restcountriesvalidationai.services.CountryService;
import edu.yuriiknowsjava.restcountriesvalidationai.services.CountryServiceImpl;
import edu.yuriiknowsjava.restcountriesvalidationai.utils.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getCountries(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "population", required = false) BigDecimal population,
            @RequestParam(value = "order", required = false, defaultValue = "ASC") Sort.Order order,
            @Min(1) @Max(1000) @RequestParam(value = "page_size", required = false, defaultValue = "250") int pageSize
    ) {
        return ResponseEntity.ok(countryService.getCountries(new CountryFilterDto(name, population, order, pageSize)));
    }
}
