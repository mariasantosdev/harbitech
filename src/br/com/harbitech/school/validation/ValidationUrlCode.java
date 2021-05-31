package br.com.harbitech.school.validation;

public interface ValidationUrlCode {
    public abstract void setCodeUrl(String codeUrl);
    public abstract void validateUrl(String codeUrl);
}
