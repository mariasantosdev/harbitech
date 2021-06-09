package br.com.harbitech.school.category;

public enum CategoryStatus {
    INACTIVE, ACTIVE;

    public static CategoryStatus from(String text) {
        if ("ATIVA".equals(text)) {
            return ACTIVE;
        } else if ("INATIVA".equals(text)) {
            return INACTIVE;
        }
        throw new IllegalArgumentException("Texto da categoria inválido: " + text);
    }

}
