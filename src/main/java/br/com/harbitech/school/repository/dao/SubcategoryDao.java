package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.subcategory.SubCategoryStatus;
import br.com.harbitech.school.subcategory.Subcategory;

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

    public List<Subcategory> searchAllWithoutDescription(){
        return em.createNamedQuery("Subcategory.AllWithoutDescription", Subcategory.class).getResultList();
//                .setParameter("status", SubCategoryStatus.ACTIVE).getResultList();
    }

}
