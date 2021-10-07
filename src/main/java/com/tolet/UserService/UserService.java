package com.tolet.UserService;

import com.tolet.LivingService.LivingSpace;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    User getUserById(Integer integer);

    User getUserByName(String name);

    User signUpUser(User user) throws Exception;

    List<LivingSpace> getAllUserTolets(Integer id);
}
