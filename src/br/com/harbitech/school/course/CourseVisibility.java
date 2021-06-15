package br.com.harbitech.school.course;

import java.util.Arrays;

public enum CourseVisibility {
    PUBLIC("PÚBLICA"), PRIVATE("PRIVADA");
    private final String description;

    CourseVisibility(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static CourseVisibility from(String text) {
        return Arrays.stream(CourseVisibility.values())
                .filter(visibility -> visibility.getDescription().equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Texto da categoria inválido: " + text));
    }
}
