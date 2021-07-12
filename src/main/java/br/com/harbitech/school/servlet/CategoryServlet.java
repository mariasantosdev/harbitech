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
import java.util.List;

@WebServlet(urlPatterns="/listaCategorias")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.findAll();

        request.setAttribute("categories", categories);

        RequestDispatcher rd = request.getRequestDispatcher("/listaCategorias.jsp");
        rd.forward(request, response);

    }
}

