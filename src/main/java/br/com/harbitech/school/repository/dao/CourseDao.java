package br.com.harbitech.school.repository.dao;

import br.com.harbitech.school.course.Course;

import javax.persistence.EntityManager;

public class CourseDao {

    private EntityManager em;

    public CourseDao(EntityManager em){
        this.em = em;
    }

    public void save(Course course) {
        this.em.persist(course);
    }

}

