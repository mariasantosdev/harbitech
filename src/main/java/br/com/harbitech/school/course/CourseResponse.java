package br.com.harbitech.school.course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseResponse {

    private final String name;
    private final String codeUrl;
    private final int completionTimeInHours;
    private final String developedSkills;

    public CourseResponse(String name, String codeUrl, int completionTimeInHours, String developedSkills) {
        this.name = name;
        this.codeUrl = codeUrl;
        this.completionTimeInHours = completionTimeInHours;
        this.developedSkills = developedSkills;
    }

    public String getName() {
        return name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public int getCompletionTimeInHours() {
        return completionTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }
    
    public static List<CourseResponse> convert(List<Course> courses){
        return courses.stream().map(CourseResponse::convert).collect(Collectors.toList());
    }
    
    public static CourseResponse convert(Course course){
        return new CourseResponse(course.getName(),course.getCodeUrl(),course.getCompletionTimeInHours(),
                course.getDevelopedSkills());
    }
    
}
