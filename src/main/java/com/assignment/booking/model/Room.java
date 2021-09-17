package com.assignment.booking.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roomName;
    private Integer capacity;


    @Transient
    private List<Booking> booking;

    @Override
    public String toString() {
        return "Room{" + "roomName='" + roomName + '\'' + ", capacity=" + capacity + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
