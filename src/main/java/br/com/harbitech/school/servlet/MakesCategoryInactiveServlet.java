package br.com.harbitech.school.servlet;

import br.com.harbitech.school.repository.dao.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inativaCategoria")
public class MakesCategoryInactiveServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codeUrl = request.getParameter("codeUrl");

        CategoryDao categoryDao = new CategoryDao();
        categoryDao.changeStatusToInactive(codeUrl);

        response.sendRedirect("listaCategorias");

    }
}

