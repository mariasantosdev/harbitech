package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.course.CourseVisibility;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CourseDao {

    private final EntityManager em;

    public CourseDao(EntityManager em) {
        this.em = em;
    }

    public void save(Course course) {
        if (course.getId() == null) {
            this.em.persist(course);
        }
        this.em.merge(course);
    }

    public void delete(String codeUrl) {
        Query query = em.createQuery("DELETE FROM Course c WHERE c.codeUrl = :codeUrl")
                .setParameter("codeUrl", codeUrl);
        query.executeUpdate();
    }

    public void updateAllToPublicVisibility() {
        Query query = em.createQuery("UPDATE Course c SET c.visibility = :visibility")
                .setParameter("visibility", CourseVisibility.PUBLIC);
        query.executeUpdate();
    }

    public List<Course> searchAllWithPublicVisibility() {
      return em.createNamedQuery("Course.findAllWithVisibility",Course.class)
                .setParameter("visibility", CourseVisibility.PUBLIC).getResultList();
    }

    public List<Course> findAll(){
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }
}

