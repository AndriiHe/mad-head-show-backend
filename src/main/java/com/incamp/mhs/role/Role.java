package com.incamp.mhs.role;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @NonNull
    @JsonView(MinimalView.class)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = Collections.emptyList();


    @Override
    public String getAuthority() {
        return name;
    }

    public interface MinimalView {}
}
