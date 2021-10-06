package com.tolet.service;

import com.tolet.model.User;
import com.tolet.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder().username("Rajeeb").password("123").id(1).build();
        Mockito.when(userRepository.findByUsername("Rajeeb")).thenReturn(user);
    }

    @Test
    void whenValidUserName_thenUserShouldFound() {
        String name = "Rajeeb";
        User user = userService.getUserByName(name);
        assertEquals(name, user.getUsername());
    }
}