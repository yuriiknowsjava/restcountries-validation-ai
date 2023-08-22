package edu.yuriiknowsjava.restcountriesvalidationai.services;

import java.util.List;

import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryDto;
import edu.yuriiknowsjava.restcountriesvalidationai.dto.CountryFilterDto;

public interface CountryService {
    List<CountryDto> getCountries(CountryFilterDto countryFilterDto);
}
