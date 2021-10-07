package com.tolet.CommercialService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tolet.BookingService.Booking;
import com.tolet.LivingService.Image;
import com.tolet.UserService.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CommercialSpace {

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
    private Integer perDayHourlyRent;
    private Integer advanceRent;
    private Integer serviceCharge;
    private boolean isWaterGasBillIncluded;
    private boolean isAvailableGarage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "commercialSpace", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Booking> bookings;

    @OneToMany(targetEntity = Image.class, orphanRemoval = true)
    @JoinColumn(name = "space_id",referencedColumnName = "id")
    private List<Image> imageURL_list;
}
