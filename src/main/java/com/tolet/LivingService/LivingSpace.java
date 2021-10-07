package com.tolet.LivingService;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tolet.BookingService.Booking;
import com.tolet.UserService.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LivingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String spaceName;
    private String district;
    private String area;
    private String location;
    private Integer floorNumber;
    private Integer squareFeet;
    private Integer perSquareRate;
    private Integer monthlyRent;
    private Integer advanceRent;
    private Integer serviceCharge;
    private boolean isWaterGasBillIncluded;
    private Integer numOfMasterBed;
    private Integer numOfBedRoom;
    private Integer numOfDiningRoom;
    private Integer numOfBathRoom;
    private boolean isDuplex;
    private String spaceType;
    private Integer numOfCorridor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "livingSpace", orphanRemoval = true)
    @ToString.Exclude
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<Image> imageURL_list = new ArrayList<>();
}
