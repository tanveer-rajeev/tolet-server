package com.tolet.UserService;

import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.LivingService.LivingSpace;
import com.tolet.Utility.Validation;
import com.tolet.security.ApplicationUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;


@Service
@Qualifier("userServiceImplementation")
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, ModelMapper modelMapper,
                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User getUserById(Integer userId) {
        return userRepository
                .findById(userId)
                .stream()
                .filter(user -> user
                        .getId()
                        .equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("user not found: " + userId));
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByUsername(name);
        return user;
    }


    @Override
    public User signUpUser(User user) throws Exception {


        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new ResourceNotFoundException("- User name already exist: " + user.getUsername());
        }
        if (user.getUsername().length() < 3) {
            throw new ResourceNotFoundException("~ User name should be at least 3 characters ");
        }


        if (Validation.checkPasswordValidation(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else {
            throw new Exception("> Password should be 6 characters and contains with any symbol");
        }

        user = userRepository.save(user);
        return user;
    }

    @Override
    public List<LivingSpace> getAllUserTolets(Integer id) {
        return getUserById(id).getLivingSpaces();
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new ResourceNotFoundException("- User name not found in the system: ");
        }

        return new ApplicationUserDetails(user.getUsername(), user.getPassword(), new ArrayList<>(), true, true, true,
                true);

    }
}
