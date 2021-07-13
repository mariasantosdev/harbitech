package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CategoryDao {

//    private EntityManager em;
   private EntityManager em = JPAUtil.getEntityManager();

    public CategoryDao(EntityManager em){
        this.em = em;
    }
    public CategoryDao(){
    }

    public List<Category> searchAllActive() {
        return em.createNamedQuery("Category.allActive", Category.class)
                .setParameter("status", CategoryStatus.ACTIVE).getResultList();
    }

    public List<Category> findAll(){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        transaction.commit();
        return categories;
    }

    public void changeStatusToInactive(String codeUrl) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query query = em.createQuery("UPDATE Category SET status = 'INACTIVE' WHERE codeUrl = :codeUrl")
                .setParameter("codeUrl", codeUrl);
        query.executeUpdate();
        transaction.commit();
    }

    public void save(Category category) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        this.em.persist(category);
        transaction.commit();
    }

    public Category findByCode(String codeUrl){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Category category = em.createQuery("SELECT c FROM Category c WHERE c.codeUrl = :codeUrl", Category.class)
                .setParameter("codeUrl", codeUrl)
                .getSingleResult();
        transaction.commit();
        return category;
    }
}
