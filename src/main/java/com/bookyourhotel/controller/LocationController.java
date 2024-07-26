package com.bookyourhotel.controller;

import com.bookyourhotel.dto.LocationDto;
import com.bookyourhotel.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Location")
public class LocationController {

    private HotelService hs;

    public LocationController(HotelService hs) {
        this.hs = hs;
    }
    @PostMapping("/addLocation")
    public ResponseEntity<LocationDto> addLocationUser(@RequestBody LocationDto dto)
    {
        LocationDto locationDto = hs.addLocation(dto);
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }
}
