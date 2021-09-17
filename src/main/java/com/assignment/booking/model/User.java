package com.assignment.booking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<Space> spaces;

}
