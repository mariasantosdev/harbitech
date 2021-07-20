package br.com.harbitech.school.servlet;

import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.util.JPAUtil;

import javax.persistence.EntityManager;
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

        EntityManager em = JPAUtil.getEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();
        categoryDao.changeStatusToInactive(codeUrl);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect("listaCategorias");

    }
}

