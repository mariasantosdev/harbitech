package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.course.Course;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CourseDao {

    private EntityManager em;

    public CourseDao(EntityManager em){
        this.em = em;
    }

    public void save(Course course) {
        this.em.persist(course);
    }

    public void delete(String codeUrl) {
        Query query = em.createQuery("DELETE FROM Course AS c WHERE c.codeUrl = :codeUrl")
                .setParameter("codeUrl", codeUrl);
        query.executeUpdate();
    }

}

