package edu.yuriiknowsjava.restcountriesvalidationai.controllers;

import java.math.BigDecimal;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryFilterDto;
import edu.yuriiknowsjava.restcountriesvalidationai.services.CountryService;
import edu.yuriiknowsjava.restcountriesvalidationai.services.CountryServiceImpl;
import edu.yuriiknowsjava.restcountriesvalidationai.utils.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<?> getCountries(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "population", required = false) BigDecimal population,
            @RequestParam(value = "order", required = false) Sort.Order order,
            @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        return ResponseEntity.ok(countryService.getCountries(new CountryFilterDto(name, population, order, pageSize)));
    }
}
