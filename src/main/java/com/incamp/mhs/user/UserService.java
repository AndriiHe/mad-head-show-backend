package com.incamp.mhs.user;

import com.incamp.mhs.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    public List<User> findBy(UserSpecification userSpecification) {
        return userRepository.findBy(userSpecification);
    }

    @Transactional(readOnly = true)
    public User getByPk(Integer id) {
        return userRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findOneByUsername(username).orElseThrow(EntityNotFoundException::new);
    }


    @Transactional
    public void save(User user) {
        if (!isUsernameExists(user.getUsername())) {
            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);
//            user.setRoles(Collections.singletonList(roleService.findOneByName("ROLE_ADMIN")));
            userRepository.persist(user);
        } else {
            throw new UserAlreadyExistsException("Username already exists");
        }
    }

    private boolean isUsernameExists(String username) {
        try {
            User userByUsername = getUserByUsername(username);
            return Objects.equals(username, userByUsername.getUsername());
        } catch (EntityNotFoundException ignored) {/* Exception handling not need for this situation */}
        return false;
    }
}
