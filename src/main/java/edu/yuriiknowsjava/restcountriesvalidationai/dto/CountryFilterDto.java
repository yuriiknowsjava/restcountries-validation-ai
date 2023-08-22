package edu.yuriiknowsjava.restcountriesvalidationai.dto;

import java.math.BigDecimal;

import edu.yuriiknowsjava.restcountriesvalidationai.utils.Sort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryFilterDto {
    private String name;
    private BigDecimal population;
    private Sort.Order order;
    private Integer pageSize;
}
