package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.repository.dao.CourseDao;
import br.com.harbitech.school.util.JPAUtil;

import javax.persistence.EntityManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

public class CourseHtmlPageGenarator {
    public static void main(String[] args) throws SQLException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CourseDao courseDao = new CourseDao(em);
        List<Course> courses = courseDao.searchAllWithPublicVisibility();
        CourseHtmlPageGenarator courseHtmlPageGenarator = new CourseHtmlPageGenarator();
        courseHtmlPageGenarator.generate("Relatório-cursos.html");

    }

    public void generate(String filePath) throws IOException {

        EntityManager em = JPAUtil.getEntityManager();
        CourseDao courseDao = new CourseDao(em);
        List<Course> courses = courseDao.searchAllWithPublicVisibility();

        try (OutputStream outputStream = new FileOutputStream(filePath);
             PrintStream printStream = new PrintStream(outputStream)){

             writePage(courses, printStream);
        }
    }

    private void writePage(List<Course> courses, PrintStream printStream) {
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

        for (Course c : courses) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getId() + "</td>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getCompletionTimeInHours() + "</td>");
            printStream.println("<td>" + c.getVisibility() + "</td>");
            printStream.println("<td>" + c.getTargetAudience() + "</td>");
            printStream.println("<td>" + c.getInstructor() + "</td>");
            printStream.println("<td>" + c.getDescription() + "</td>");
            printStream.println("<td>" + c.getDevelopedSkills() + "</td>");
            printStream.println("<td>" + c.getSubCategory().getId() + "</td>");
            printStream.println("<td>");

            printStream.println("</td>");
            printStream.println("</tr>");
        }
        printStream.println("</table>");
        printStream.println("</body>");
        printStream.println("</html>");
    }
}

