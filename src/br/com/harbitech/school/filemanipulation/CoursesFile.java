package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;

import java.io.*;
import java.util.Scanner;

public class CoursesFile {
    public static void main(String[] args) throws IOException {

        try (InputStream inputStream = new FileInputStream("planilha-dados-escola - Curso.csv");
             Scanner scanner = new Scanner(inputStream, "UTF-8");
             OutputStream outputStream = new FileOutputStream("planilha-dados-escola - Curso.csv",true);
             PrintStream printStream = new PrintStream(outputStream)) {

            int contador = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (contador > 0) {
                    String[] courseData = line.split(",");
                    String courseName = courseData[0];
                    String courseCodeUrl = courseData[1];
                    String courseCompletionTimeInHours = courseData[2];
                    String courseVisibility = courseData[3];
                    String courseTargetAudience = courseData[4];
                    String courseInstructor = courseData[5];
                    String courseDescription = courseData[6];
                    String courseDevelopedSkills = courseData[7];

                    int order = -1;
                    if (!courseCompletionTimeInHours.isBlank()) {
                        order = Integer.parseInt(courseCompletionTimeInHours);
                    }

                    Course course = new Course(courseName, courseCodeUrl, order,
                            courseTargetAudience, courseInstructor, courseDescription, courseDevelopedSkills);
                    System.out.println("oie");
                }
            }
        }
    }
}
