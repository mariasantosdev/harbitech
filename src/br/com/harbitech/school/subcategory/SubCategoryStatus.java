package br.com.harbitech.school.subcategory;

public enum SubCategoryStatus {
    INACTIVE, ACTIVE;

    public static SubCategoryStatus from(String text) {
        if ("ATIVA".equals(text)) {
            return ACTIVE;
        } else if ("INATIVA".equals(text)) {
            return INACTIVE;
        }
        throw new IllegalArgumentException("Texto da sub-categoria inválido: " + text);
    }
}
