package br.com.harbitech.school.category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategoryDao {

    private final EntityManager em;

    public CategoryDao(EntityManager em){
        this.em = em;
    }

    public List<Category> searchAllActive() {
        return em.createNamedQuery("Category.allWithStatus", Category.class)
                .setParameter("status", CategoryStatus.ACTIVE).getResultList();
    }

    public List<Category> findAll(){
        List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        return categories;
    }

    public void changeStatusToInactive(String codeUrl) {
        Query query = em.createQuery("UPDATE Category SET status = 'INACTIVE' WHERE codeUrl = :codeUrl")
                .setParameter("codeUrl", codeUrl);
        query.executeUpdate();
    }

    public void save(Category category) {
        this.em.persist(category);
    }

    public void update(Category category) {
        em.merge(category);
    }

    public Category findByCodeUrl(String codeUrl){
        Category category = em.createQuery("SELECT c FROM Category c WHERE c.codeUrl = :codeUrl", Category.class)
                .setParameter("codeUrl", codeUrl)
                .getSingleResult();
        return category;
    }

    public Category findById(Long id){
        Category category = em.createQuery("SELECT c FROM Category c WHERE c.id = :id", Category.class)
                .setParameter("id", id)
                .getSingleResult();
        return category;
    }
}
