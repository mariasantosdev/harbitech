package br.com.harbitech.school.profile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM")
    private ProfileType type = ProfileType.ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return type.name();
    }
}
