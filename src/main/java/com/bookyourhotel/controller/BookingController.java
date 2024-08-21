package com.bookyourhotel.controller;

import com.bookyourhotel.dto.BookingDto;
import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Bookings")
public class BookingController
{
    private BookingService bs;

    public BookingController(BookingService bs) {
        this.bs = bs;
    }

    @PostMapping("/bookProperty")
    public ResponseEntity<BookingDto> createBooking(@RequestParam String propertyId,
                                                    @AuthenticationPrincipal AppUserEntity user,
                                                    @RequestBody BookingDto booking)
    {
        BookingDto bookingDto = bs.bookProperty(propertyId, user, booking);
        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }
}
