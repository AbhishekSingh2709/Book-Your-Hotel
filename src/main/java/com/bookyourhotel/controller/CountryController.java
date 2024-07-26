package com.bookyourhotel.controller;


import com.bookyourhotel.dto.CountryDto;
import com.bookyourhotel.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Country")
public class CountryController
{
    private HotelService hs;

    public CountryController(HotelService hs) {
        this.hs = hs;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountryUser(@RequestBody CountryDto dto)
    {
        CountryDto countryDto = hs.addCountry(dto);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }
}
