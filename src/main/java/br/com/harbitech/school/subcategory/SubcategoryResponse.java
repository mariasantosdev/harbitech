package br.com.harbitech.school.subcategory;

import br.com.harbitech.school.course.CourseResponse;

import java.util.List;
import java.util.stream.Collectors;

public record SubcategoryResponse(
        String name,
        String codeUrl,
        String studyGuide,
        List<CourseResponse> course) {

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



