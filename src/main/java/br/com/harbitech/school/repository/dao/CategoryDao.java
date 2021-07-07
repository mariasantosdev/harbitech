package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em){
        this.em = em;
    }

    public List<Category> searchAllActive() {
        return em.createNamedQuery("Category.allActive", Category.class)
                .setParameter("status", CategoryStatus.ACTIVE).getResultList();
    }

}
