package edu.yuriiknowsjava.restcountriesvalidationai.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Filters {
    private Filters() {
    }

    public static List<Map<String, Object>> searchByName(List<Map<String, Object>> maps, String name) {
        var expectedName = Optional.ofNullable(name).orElse("").toLowerCase();
        return maps.stream()
                .filter(Objects::nonNull)
                .filter(map -> {
                    var actualName = map.get("name");
                    return actualName instanceof String && ((String) actualName).toLowerCase().contains(expectedName);
                })
                .collect(Collectors.toList());
    }
}
