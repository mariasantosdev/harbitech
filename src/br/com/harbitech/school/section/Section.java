package br.com.harbitech.school.section;

import br.com.harbitech.school.course.Course;

public class Section {

    private String name;
    private String codeUrl;
    private int orderVisualization;
    private TypeIndicationSection indicationSection;
    private TypeIndicationTest indicationTest;
    private Course course;

    public Section(String name, String codeUrl, int orderVisualization, TypeIndicationSection indicationSection,
                   TypeIndicationTest indicationTest, Course course) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.orderVisualization = orderVisualization;
        this.indicationSection = indicationSection;
        this.indicationTest = indicationTest;
        this.course = course;
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

    public static void validateUrl(String codeUrl) {
        boolean validacao = codeUrl.matches("[a-z]*");
        if (!validacao) {
            throw new RuntimeException("Não validado");
        }
        System.out.println("Validado");
    }
}