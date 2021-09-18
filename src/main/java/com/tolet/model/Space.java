package com.tolet.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String district;
    private String area;
    private String location;
    private Integer floorNumber;
    private Integer squareFeet;
    private Integer perSquareRate;
    private Integer monthlyRent;
    private Integer perDayHourlyRent;
    private Integer advanceRent;
    private String spaceType;
    private Integer serviceCharge;
    private boolean isWaterGasBillIncluded;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "space", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Booking> bookings;

    @JsonIgnore
    @OneToMany(mappedBy = "space")
    private Set<Image> imageURL_list;
}