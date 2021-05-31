package section;

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
    //TODO Lance exceções para valores que não são válidos. Favoreça o uso de exceções padrão do próprio Java
}