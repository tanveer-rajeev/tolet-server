package com.tolet.Exception;

import com.tolet.BookingService.BookedInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingException {

    private Date timestamp;
    private String message;
    private String details;
    private BookedInfo bookedInfo;

}
