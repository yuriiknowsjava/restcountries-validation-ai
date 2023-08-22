package edu.yuriiknowsjava.restcountriesvalidationai.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorDto {
    private int code;
    private List<String> errors;
}
