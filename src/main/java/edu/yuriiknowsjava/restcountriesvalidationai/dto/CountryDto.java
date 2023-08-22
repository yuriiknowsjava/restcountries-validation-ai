package edu.yuriiknowsjava.restcountriesvalidationai.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CountryDto {
    private CountryName name;
    private List<String> tld;
    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private Boolean independent;
    private String status;
    private Boolean unMember;
    private List<String> capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private Map<String, String> languages;
    private List<BigDecimal> latlng;
    private Boolean landlocked;
    private List<String> borders;
    private BigDecimal area;
    private String flag;
    private Map<String, String> maps;
    private BigDecimal population;
    private Map<String, BigDecimal> gini;
    private String fifa;
    private List<String> timezones;
    private List<String> continents;
    private String startOfWeek;
}
