package br.com.harbitech.school.subcategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDao {
    private EntityManager em;

    public SubcategoryDao(EntityManager em){
        this.em = em;
    }

    public List<Subcategory> searchAllActive() {
        return em.createNamedQuery("Subcategory.allActive", Subcategory.class)
                .setParameter("status", SubCategoryStatus.ACTIVE).getResultList();
    }

    public List<String> searchAllWithoutDescription(){
        return em.createNamedQuery("Subcategory.AllWithoutDescription", String.class).getResultList();
    }

}
