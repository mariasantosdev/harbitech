package br.com.harbitech.school.validation;

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

    public static void validateMinimumTime(int time, String errorMessage){
        if(time < 1){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateMaximumTime(int time, String errorMessage){
        if(time > 20){
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
