package db.migration;

import com.incamp.mhs.role.RoleService;
import com.incamp.mhs.user.User;
import com.incamp.mhs.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MigrationUserService {

    private static UserRepository userRepository;

    private static RoleService roleService;

    MigrationUserService(UserRepository userRepository, RoleService roleService) {
        MigrationUserService.userRepository = userRepository;
        MigrationUserService.roleService = roleService;
    }

    public static void create(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Collections.singletonList(roleService.findOneByName("ROLE_ADMIN")));
        userRepository.persist(user);
    }
}
