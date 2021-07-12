package br.com.harbitech.school.servlet;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.repository.dao.CategoryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns="/listaCategorias")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        CategoryDao categoryDao = new CategoryDao();

        List<Category> categories = categoryDao.findAll();

//        request.setAttribute("categories", categories);

        PrintWriter printStream = response.getWriter();
        printStream.println("<html>");
        printStream.println("<body>");
        for (Category c : categories) {
            printStream.println("<tr align =center>");
            printStream.println("<td>" + c.getName() + "</td>");
            printStream.println("<td>" + c.getDescription() + "</td>");
            printStream.println("<td>" + c.getIconPath() + "</td>");
            printStream.println("<td>" + c.getHtmlHexColorCode() + "</td>");
            printStream.println("<td>" + c.totalCourses() + "</td>");
            printStream.println("<td>" + c.totalTimeInHoursOfCourse() + "</td>");
            printStream.println("<td>");
            printStream.println("<br>");
            printStream.println("<br>");
            printStream.println("</body>");
            printStream.println("</html>");
        }
        printStream.println("</tr>");
        printStream.println("<br>");
        printStream.println("<br>");
    }
}
