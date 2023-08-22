package edu.yuriiknowsjava.restcountriesvalidationai.controllers;

import java.util.List;
import java.util.Map;

import edu.yuriiknowsjava.restcountriesvalidationai.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Map<String, Object>>> getCountries() throws Exception {
        return ResponseEntity.ok(countryService.getCountries());
    }
}
