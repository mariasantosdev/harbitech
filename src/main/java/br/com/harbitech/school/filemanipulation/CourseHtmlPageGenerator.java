package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseDto;
import br.com.harbitech.school.repository.dao.CourseDAO;
import br.com.harbitech.school.repository.factory.ConnectionFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseHtmlPageGenerator {

    public static void main(String[] args) throws SQLException, IOException {
        CourseHtmlPageGenerator courseHtmlPageGenerator = new CourseHtmlPageGenerator();
        courseHtmlPageGenerator.generate("Relatório-cursos.html");
    }

    public void generate(String filePath) throws IOException, SQLException {

        try (OutputStream outputStream = new FileOutputStream(filePath);
             PrintStream printStream = new PrintStream(outputStream);
             Connection connection = new ConnectionFactory().retrieveConnection()) {
            CourseDAO courseDAO = new CourseDAO(connection);
            List<CourseDto> courses = courseDAO.searchAllWithPublicVisibility();
            writePage(courses, printStream);
        }
    }

    private void writePage(List<CourseDto> courses, PrintStream printStream) {
        printStream.println("<html>");
        printStream.println("<head>");
        printStream.println("</head>");
        printStream.println("<body>");
        printStream.println("<table border=1 frame=void rules=rows>");
        printStream.println("<tr>");
        printStream.println("<th>" + "Id curso" + "</th>");
        printStream.println("<th>" + "Nome" + "</th>");
        printStream.println("<th>" + "Duração" + "</th>");
        printStream.println("<th>" + "Id da subcategoria" + "</th>");
        printStream.println("<th>" + "Nome da subcategoria" + "</th>");
        printStream.println("</tr>");

        for (CourseDto c : courses) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getId() + "</td>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getCompletionTimeInHours() + "</td>");
            printStream.println("<td>" + c.getSubcategoryDto().getId() + "</td>");
            printStream.println("<td>" + c.getSubcategoryDto().getName() + "</td>");
            printStream.println("<td>");

            printStream.println("</td>");
            printStream.println("</tr>");
        }
        printStream.println("</table>");
        printStream.println("</body>");
        printStream.println("</html>");
    }
}
