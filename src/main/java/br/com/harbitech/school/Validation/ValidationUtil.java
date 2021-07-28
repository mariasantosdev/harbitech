package br.com.harbitech.school.Validation;

public class ValidationUtil {

    public static void validateNonBlankText(String text, String errorMessage) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateNonNullClass(Object object, String errorMessage){
        if (object == null){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateInterval(int value, int minimumValue, int maximumValue, String errorMessage){
        if(value < minimumValue || value > maximumValue){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateUrl(String codeUrl, String errorMessage) {
        boolean validacao = codeUrl.matches("[-a-z]+");
        if (!validacao) {
            throw new IllegalArgumentException(errorMessage);
        }
        System.out.println("Validado");
    }
}
