package com.tolet.repository;
import com.tolet.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        User user = User.builder().username("Rajeeb").password("123").id(1).build();
        testEntityManager.persist(user);
    }

    @Test
    public void whenFindById_ThenReturnUser(){
        User user = userRepository.findById(1).get();
        assertEquals(user.getUsername(),"Rajeeb");
    }
}