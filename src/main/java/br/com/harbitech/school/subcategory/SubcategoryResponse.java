package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.course.CourseResponse;

import java.util.List;
import java.util.stream.Collectors;

public class SubcategoryResponse {
    private final String name;
    private final String codeUrl;
    private final String studyGuide;
    private final List<CourseResponse> course;

    public SubcategoryResponse(String name, String codeUrl, String studyGuide, List<CourseResponse> courseResponse) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.studyGuide = studyGuide;
        this.course = courseResponse;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getStudyGuide() {
        return studyGuide;
    }
    
    public List<CourseResponse> getCourse() {
        return course;
    }

    public int totalCourses() {
        return course.size();
    }

    public static List<SubcategoryResponse> convert(List<Subcategory> subcategories) {
        return subcategories.stream()
                .filter(s -> s.getStatus().equals(SubCategoryStatus.ACTIVE))
                .map(SubcategoryResponse::convert)
                .collect(Collectors.toList());
    }

    public static SubcategoryResponse convert(Subcategory subcategory) {
        return new SubcategoryResponse(subcategory.getName(), subcategory.getCodeUrl(), subcategory.getStudyGuide(), 
                CourseResponse.convert(subcategory.getCourses()));
    }
}
