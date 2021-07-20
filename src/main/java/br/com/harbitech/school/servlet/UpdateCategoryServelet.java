package br.com.harbitech.school.servlet;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryDto;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alteraCategoria")
public class UpdateCategoryServelet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paramiId = request.getParameter("id");
        Long id = Long.valueOf(paramiId);
        String name = request.getParameter("name");
        String codeUrl = request.getParameter("codeUrl");
        String description = request.getParameter("description");
        String studyGuide = request.getParameter("studyGuide");
        String paramStatus = request.getParameter("status");
        CategoryStatus categoryStatus = CategoryStatus.valueOf(paramStatus);
        String paramOrderVisualization = request.getParameter("orderVisualization");
        Integer orderVisualization = Integer.valueOf(paramOrderVisualization);
        String iconPath = request.getParameter("iconPath");
        String htmlHexColorCode = request.getParameter("htmlHexColorCode");

        EntityManager em = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();

        Category category = categoryDao.findById(id);

        category.setName(name);
        category.setHtmlHexColorCode(htmlHexColorCode);
        category.setCodeUrl(codeUrl);
        category.setStudyGuide(studyGuide);
        category.setStatus(categoryStatus);
        category.setIconPath(iconPath);
        category.setDescription(description);
        category.setOrderVisualization(orderVisualization);

        categoryDao.update(category);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect("listaCategorias");
    }
}
