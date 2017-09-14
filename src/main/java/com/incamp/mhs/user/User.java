package com.incamp.mhs.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.role.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @NonNull
    @JsonView(MinimalView.class)
    private String fullName;

    @NonNull
    @Column(nullable = false)
    @JsonView(MinimalView.class)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @JsonView(MinimalView.class)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = Collections.emptyList();

    public interface MinimalView {}
}
