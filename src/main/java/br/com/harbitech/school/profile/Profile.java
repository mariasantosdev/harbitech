package br.com.harbitech.school.profile;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private ProfileType type = ProfileType.ROLE_STUDENT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfileType getType() {
        return type;
    }

    public void setType(ProfileType type) {
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return type.name();
    }
}
