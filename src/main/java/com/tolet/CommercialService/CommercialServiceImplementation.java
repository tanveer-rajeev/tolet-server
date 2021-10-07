package com.tolet.CommercialService;

import com.tolet.Exception.ResourceNotFoundException;
import com.tolet.UserService.User;
import com.tolet.UserService.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommercialServiceImplementation implements CommercialService{


    private final CommercialSpaceRepository commercialSpaceRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommercialServiceImplementation(CommercialSpaceRepository commercialSpaceRepository, UserRepository userRepository) {
        this.commercialSpaceRepository = commercialSpaceRepository;
        this.userRepository = userRepository;
    }

    public User getUserById(Integer userId){
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
    public CommercialSpace createCommercialSpace(CommercialSpace commercialSpace ,Integer userId) {

        User user = getUserById(userId);
        commercialSpace.setOwner(user);
        commercialSpaceRepository.save(commercialSpace);
        return commercialSpace;
    }

    @Override
    public CommercialSpace updateCommercialSpace(CommercialSpace commercialSpace) {
        return null;
    }

    @Override
    public void deleteCommercialSpace(Integer spaceId) {

    }
}
