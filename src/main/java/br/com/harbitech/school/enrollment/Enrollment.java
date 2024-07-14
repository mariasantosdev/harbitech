package br.com.harbitech.school.enrollment;


import br.com.harbitech.school.course.Course;
import br.com.harbitech.school.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Deprecated
    protected Enrollment() {
    }

    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    User getUser() {
        return user;
    }

    Course getCourse() {
        return course;
    }

}
