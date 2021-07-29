package br.com.harbitech.school.validation;

public class ValidationUtil {

    public static void validateUrl(String codeUrl, String errorMessage) {
        boolean validacao = codeUrl.matches("[-a-z]+");
        if (!validacao) {
            throw new IllegalArgumentException(errorMessage);
        }
        System.out.println("Validado");
    }
}
