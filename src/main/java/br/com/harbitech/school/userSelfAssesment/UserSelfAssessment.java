package br.com.harbitech.school.userSelfAssesment;

import br.com.harbitech.school.subcategory.Subcategory;
import br.com.harbitech.school.user.User;

import javax.persistence.*;

@Entity
public class UserSelfAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Subcategory subcategory;

    @Deprecated
    public UserSelfAssessment() {
    }

    public UserSelfAssessment(User user, Subcategory subcategory) {
        this.user = user;
        this.subcategory = subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
