package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.Subcategory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CourseFileReader {

    public List<Course> readCoursesFromFile(String filePath, Map<String, Subcategory> subCategoryMap) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        List<Course> courses = new LinkedList<Course>();
        try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] courseData = line.split(",", -1);
                String courseName = courseData[0];
                String courseCodeUrl = courseData[1].trim();
                String courseCompletionTimeInHours = courseData[2];
                String courseVisibility = courseData[3];
                String courseTargetAudience = courseData[4];
                String courseInstructor = courseData[5];
                String courseDescription = courseData[6];
                String courseDevelopedSkills = courseData[7];
                String subCategoryUrlCode = courseData[8];

                int order = -1;

                if (!courseCompletionTimeInHours.isBlank()) {
                    order = Integer.parseInt(courseCompletionTimeInHours);
                }

                Subcategory subCategory = Optional.ofNullable(subCategoryMap.get(subCategoryUrlCode))
                        .orElseThrow(() -> new IllegalArgumentException("Código da subcategoria não encontrado" + subCategoryUrlCode));

                Course course = new Course(courseName, courseCodeUrl, order, CourseVisibility.from(courseVisibility),
                        courseTargetAudience, courseInstructor, courseDescription, courseDevelopedSkills, subCategory);
                courses.add(course);
            }
            System.out.println("Exibindo cursos:\n");
        }
        return courses;
    }
}


