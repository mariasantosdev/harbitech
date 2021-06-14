package br.com.harbitech.school.course;

public enum CourseVisibility {
    PUBLIC, PRIVATE;

    public static CourseVisibility from(String text) {
        if ("PÚBLICA".equals(text)) {
            return PUBLIC;
        } else if ("PRIVADA".equals(text)) {
            return PRIVATE;
        }
        throw new IllegalArgumentException("Texto da categoria inválido: " + text);
    }
}
