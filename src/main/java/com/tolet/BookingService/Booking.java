package com.tolet.BookingService;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.tolet.CommercialService.CommercialSpace;
import com.tolet.LivingService.LivingSpace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String phoneNumber;
    private String bookingDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "spaceId")
    private LivingSpace livingSpace;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "comSpaceId")
    private CommercialSpace commercialSpace;

}
