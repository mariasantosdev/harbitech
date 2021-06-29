package br.com.harbitech.school.subcategory;

import java.util.Arrays;

public enum SubCategoryStatus {
    INACTIVE("INATIVA"), ACTIVE("ATIVA");

    private String description;

    SubCategoryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static final SubCategoryStatus from(String text) {
        return Arrays.stream(SubCategoryStatus.values())
                .filter(status -> status.getDescription().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Texto da sub-categoria inválido: " + text));
    }
}
