package br.com.harbitech.school.servlet;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryDto;
import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.util.JPAUtil;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns="/api/todascategorias")
public class CategoryService extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);
        List<Category> categories = categoryDao.findAll();

        em.getTransaction().commit();
        em.close();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.convert(categories);

        String valueOfHeader = request.getHeader("Accept");

        System.out.println(valueOfHeader);

        if (valueOfHeader.contains("xml")) {
            XStream xstream = new XStream();
            xstream.alias("categories", Category.class);
            String xml = xstream.toXML(categoryDto);

            response.setContentType("application/xml");
            response.getWriter().print(xml);

        } else if (valueOfHeader.contains("json")) {
            Gson gson = new Gson();
            String json = gson.toJson(categoryDto);

            response.setContentType("application/json");
            response.getWriter().print(json);
        } else {
            response.setContentType("application/json");
            response.getWriter().print("{'message':'no content'}");
        }
    }
}