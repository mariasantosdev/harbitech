package br.com.harbitech.school.filemanipulation;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.repository.dao.CourseDao;
import br.com.harbitech.school.repository.dao.SubcategoryDao;
import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.util.JPAUtil;

import br.com.harbitech.school.category.Category;

import javax.persistence.EntityManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class CourseHtmlPageGenarator {
    public static void main(String[] args) throws IOException {
        CourseHtmlPageGenarator courseHtmlPageGenarator = new CourseHtmlPageGenarator();
        courseHtmlPageGenarator.generate("Relatório-cursos.html");
    }

    public void generate(String filePath) throws IOException {

        EntityManager em = JPAUtil.getEntityManager();

        CourseDao courseDao = new CourseDao(em);
        List<Course> courses = courseDao.searchAllWithPublicVisibility();

        CategoryDao categoryDao = new CategoryDao(em);
        List<Category> categories = categoryDao.searchAllActive();

        SubcategoryDao subcategoryDao = new SubcategoryDao(em);
        List<Subcategory> subcategories = subcategoryDao.searchAllActive();
//        List<Subcategory> subcategoriesWithOutDescription = subcategoryDao.searchAllWithoutDescription();

        try (OutputStream outputStream = new FileOutputStream(filePath);
             PrintStream printStream = new PrintStream(outputStream)){

             writePage(courses,categories,subcategories, printStream);
        }
    }

    private void writePage(List<Course> courses, List<Category> categories,List<Subcategory> subcategories,
                           PrintStream printStream) {
        printStream.println("<html>");
        printStream.println("<head>");
        printStream.println("</head>");
        printStream.println("<body>");
        printStream.println("<center><h1>" + "Cursos" + "</h1></center>");
        printStream.println("<table border=1 frame=void rules=rows>");
        printStream.println("<tr>");
        printStream.println("<th>" + "Id" + "</th>");
        printStream.println("<th>" + "Nome" + "</th>");
        printStream.println("<th>" + "Código" + "</th>");
        printStream.println("<th>" + "Tempo em horas" + "</th>");
        printStream.println("<th>" + "Visibilidade" + "</th>");
        printStream.println("<th>" + "Publico alvo" + "</th>");
        printStream.println("<th>" + "Instrutor" + "</th>");
        printStream.println("<th>" + "Descrição" + "</th>");
        printStream.println("<th>" + "Habilidades desenvolvidas" + "</th>");
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
            printStream.println("<td>");
            printStream.println("</td>");
            printStream.println("</tr>");

        }
        printStream.println("</table>");

        printStream.println("<center><h1>" + "Categorias" + "</h1></center>");
        printStream.println("<table border=1 frame=void rules=rows>");
        printStream.println("<tr>");
        printStream.println("<th>" + "Id" + "</th>");
        printStream.println("<th>" + "Nome" + "</th>");
        printStream.println("<th>" + "Código" + "</th>");
        printStream.println("<th>" + "Descrição" + "</th>");
        printStream.println("<th>" + "Guia de estudos" + "</th>");
        printStream.println("<th>" + "Status" + "</th>");
        printStream.println("<th>" + "Ordem de visualização" + "</th>");
        printStream.println("<th>" + "Caminho do icone" + "</th>");
        printStream.println("<th>" + "Código cor/html" + "</th>");
        printStream.println("</tr>");

        for (Category c : categories) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getId() + "</td>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getCodeUrl() + "</td>");
            printStream.println("<td>" + c.getDescription() + "</td>");
            printStream.println("<td>" + c.getStudyGuide() + "</td>");
            printStream.println("<td>" + c.getStatus() + "</td>");
            printStream.println("<td>" + c.getOrderVisualization() + "</td>");
            printStream.println("<td>" + c.getIconPath() + "</td>");
            printStream.println("<td>" + c.getHtmlHexColorCode() + "</td>");
            printStream.println("<td>");
            printStream.println("</td>");
            printStream.println("</tr>");

        }
        printStream.println("</table>");

        printStream.println("<center><h1>" + "Subcategoria" + "</h1></center>");
        printStream.println("<table border=1 frame=void rules=rows>");
        printStream.println("<tr>");
        printStream.println("<th>" + "Id" + "</th>");
        printStream.println("<th>" + "Nome" + "</th>");
        printStream.println("<th>" + "Código" + "</th>");
        printStream.println("<th>" + "Descrição" + "</th>");
        printStream.println("<th>" + "Guia de estudos" + "</th>");
        printStream.println("<th>" + "Status" + "</th>");
        printStream.println("<th>" + "Ordem de visualização" + "</th>");
        printStream.println("</tr>");

        for (Subcategory c : subcategories) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getId() + "</td>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getCodeUrl() + "</td>");
            printStream.println("<td>" + c.getDescription() + "</td>");
            printStream.println("<td>" + c.getStudyGuide() + "</td>");
            printStream.println("<td>" + c.getStatus() + "</td>");
            printStream.println("<td>" + c.getOrderVisualization() + "</td>");
            printStream.println("<td>");
            printStream.println("</td>");
            printStream.println("</tr>");

        }
        printStream.println("</table>");

        printStream.println("</body>");
        printStream.println("</html>");
    }
}

