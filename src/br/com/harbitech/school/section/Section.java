package br.com.harbitech.school.section;

import br.com.harbitech.school.validation.ValidationUrlCode;
import br.com.harbitech.school.validation.ValidationUtilUrlCode;
import br.com.harbitech.school.course.Course;

public class Section implements ValidationUrlCode {

    private Long id;
    private String name;
    private String codeUrl;
    private int orderVisualization;
    private TypeIndicationSection indicationSection;
    private TypeIndicationTest indicationTest;
    private Course course;

    private ValidationUtilUrlCode validateUtil;

    public Section(){
        this.validateUtil = new ValidationUtilUrlCode();
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getCodeUrl() {
        return codeUrl;
    }

    int getOrderVisualization() {
        return orderVisualization;
    }

    TypeIndicationSection getIndicationSection() {
        return indicationSection;
    }

    TypeIndicationTest getIndicationTest() {
        return indicationTest;
    }

    Course getCourse() {
        return course;
    }

    @Override
    public void setCodeUrl(String codeUrl){
        this.validateUtil.setCodeUrl(codeUrl);
    }

    @Override
    public void validateUrl(String codeUrl){
        this.validateUtil.validateUrl(codeUrl);
    }
}