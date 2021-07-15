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

@WebServlet("/editaCategoria")
public class SearchCategoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paramId = request.getParameter("id");
        Long id = Long.valueOf(paramId);

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(id);

        request.setAttribute("category", category);

        RequestDispatcher rd = request.getRequestDispatcher("/formUpdateCategory.jsp");
        rd.forward(request, response);
    }
}
