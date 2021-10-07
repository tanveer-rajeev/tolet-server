package com.tolet.UserService;
import com.tolet.LivingService.LivingSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/tolets/{userId}")
    public List<LivingSpace> getAllUserTolets(@PathVariable Integer userId){
        return userService.getAllUserTolets(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        return userService.signUpUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
