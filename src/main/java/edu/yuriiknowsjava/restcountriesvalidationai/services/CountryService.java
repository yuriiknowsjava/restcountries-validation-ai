package edu.yuriiknowsjava.restcountriesvalidationai.services;

import java.util.List;
import java.util.Map;

public interface CountryService {
    List<Map<String, Object>> getCountries() throws Exception;
}
