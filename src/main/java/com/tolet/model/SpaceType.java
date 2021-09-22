package com.tolet.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SpaceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String spaceType;

    @OneToMany(mappedBy = "spaceType")
    private List<Space> spaceList;
}
