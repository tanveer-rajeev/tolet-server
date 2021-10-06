package com.tolet.controller;

import com.tolet.DTO.UserDTO;
import com.tolet.model.User;
import com.tolet.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
         user = User.builder().id(1).username("Rajeeb").password("123").build();
    }

    @Test
    void createUser() throws Exception {
        User inputUser = User.builder()
                .username("Rajeeb")
                .password("123").build();
        Mockito.when(userService.signUpUser(inputUser))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"Rajeeb\",\n" +
                        "    \"password\":\"123\"\n" +
                        "}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}