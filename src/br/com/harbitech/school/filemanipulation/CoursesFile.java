package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CoursesFile {
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(new File("planilha-dados-escola - Curso.csv"),
                    "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
            scanner.close();
        } catch (
                FileNotFoundException e) {
            e.getMessage();
        }

        Course course = new Course("Começando com Spring MVC", "spring-mvc",
                12, "Nico");

        var courses = new ArrayList<Course>();

        courses.add(course);

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream
                    ("planilha-dados-escola - Curso.csv", true));

            output.writeObject("\n" + course.toString());
            output.close();
        } catch (
                IOException e) {
            e.getMessage();
        }
    }
}

