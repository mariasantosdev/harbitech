package br.com.harbitech.school.category;

import java.util.Arrays;

public enum CategoryStatus {
    INACTIVE("INATIVA"), ACTIVE("ATIVA");

    private String description;

    CategoryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static final CategoryStatus from(String text) {
        return Arrays.stream(CategoryStatus.values())
                .filter(status -> status.getDescription().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Texto da categoria inválido: " + text));
    }
}