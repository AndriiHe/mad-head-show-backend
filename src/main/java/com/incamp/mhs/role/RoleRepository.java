package com.incamp.mhs.role;

import com.incamp.mhs.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository extends BaseRepository<Role, Integer> {

    public Optional<Role> findOneByName(String name) {
        RoleSpecification roleSpecification = new RoleSpecification();
        roleSpecification.setOName(Optional.of(name));
        List<Role> role = findBy(roleSpecification);
        if (role.size() > 1) throw new IllegalStateException("role name is unique property");
        if (role.isEmpty()) return Optional.empty();
        return Optional.of(role.get(0));
    }
}
