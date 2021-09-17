package com.assignment.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String imageURL;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "spaceId",referencedColumnName = "id")
    private Space space;
}
