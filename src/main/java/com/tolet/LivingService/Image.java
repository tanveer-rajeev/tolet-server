package com.tolet.LivingService;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tolet.CommercialService.CommercialSpace;
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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String imageURL;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "spaceId", referencedColumnName = "id")
//    private LivingSpace livingSpace;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "comSpaceId", referencedColumnName = "id")
//    private CommercialSpace commercialSpace;
}
