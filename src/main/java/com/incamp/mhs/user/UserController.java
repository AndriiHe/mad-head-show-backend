package com.incamp.mhs.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.incamp.mhs.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @JsonView(User.MinimalView.class)
    public List<User> getAll(UserSpecification userSpecification) {
        return userService.findBy(userSpecification);
    }

    @GetMapping("{userId}")
    @JsonView(User.MinimalView.class)
    public User getById(@PathVariable int userId) {
        return userService.getByPk(userId);
    }

    @GetMapping("currentUser")
    @JsonView(User.MinimalView.class)
    public User getCurrentUser(@CurrentUser User user) {
        return user;
    }
}
