package br.com.harbitech.school.course;

import java.util.List;
import java.util.stream.Collectors;

public record CourseResponse(
        String name,
        String codeUrl,
        int completionTimeInHours,
        String developedSkills) {

    public static List<CourseResponse> convert(List<Course> courses) {
        return courses.stream()
                .filter(c -> c.getVisibility().equals(CourseVisibility.PUBLIC))
                .map(CourseResponse::convert)
                .collect(Collectors.toList());
    }

    public static CourseResponse convert(Course course) {
        return new CourseResponse(course.getName(), course.getCodeUrl(), course.getCompletionTimeInHours(),
                course.getDevelopedSkills());
    }
}
