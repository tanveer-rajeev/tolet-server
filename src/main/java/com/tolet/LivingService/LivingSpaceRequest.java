package com.tolet.LivingService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivingSpaceRequest {

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
    private List<Image> imageURL_list;
}
