package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;
import br.com.harbitech.school.subcategory.SubCategory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CourseFileReader {
    public static void main(String[] args) throws IOException {

            InputStream inputStream = new FileInputStream("planilha-dados-escola - Curso.csv");
            List<Course> courses = new ArrayList<Course>();
            try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
                scanner.nextLine();

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    String[] courseData = line.split(",");
//                    courseData = new String[9];
                    String courseName = courseData[0];
                    String courseCodeUrl = courseData[1];
                    String courseCompletionTimeInHours = courseData[2];
                    String courseVisibility = courseData[3];
                    String courseTargetAudience = courseData[4];
                    String courseInstructor = courseData[5];
                    String courseDescription = courseData[6];
                    String courseDevelopedSkills = courseData[7];
                    String subcategoryName = courseData[8];

                    int order = -1;

                    if (!courseCompletionTimeInHours.isBlank()) {
                        order = Integer.parseInt(courseCompletionTimeInHours);
                    }

                    SubCategory subCategory = new SubCategory(subcategoryName);

                    Course course = new Course(courseName,courseCodeUrl,order, CourseVisibility.from(courseVisibility),
                            courseTargetAudience,courseInstructor,courseDescription, courseDevelopedSkills, subCategory);
                    courses.add(course);

                }
                courses.forEach(System.out::println);
            }
        }
    }

