package br.com.harbitech.school.validation;

public class ValidationUtilUrlCode {

    private String codeUrl;

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public void validateUrl(String codeUrl) {
        boolean validacao = this.codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}
