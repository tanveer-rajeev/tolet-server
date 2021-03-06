package com.tolet.security;

import com.tolet.SpringApplicationContext;

import com.tolet.UserService.User;
import com.tolet.UserService.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentLoggedInUser {


        public static User getUserEntity() {
            Authentication authentication = SecurityContextHolder
                    .getContext().getAuthentication();
            String username = authentication.getName();

            UserService employeeService =
                    (UserService) SpringApplicationContext.getBean("userServiceImplementation");
            return employeeService.getUserByName(username);

        }


}
