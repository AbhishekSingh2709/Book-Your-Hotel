package com.bookyourhotel.controller;

import com.bookyourhotel.dto.PropertyDto;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.repository.CountryRepository;
import com.bookyourhotel.repository.LocationRepository;
import com.bookyourhotel.repository.PropertyRepository;
import com.bookyourhotel.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Property")
public class PropertyController
{

    private HotelService hs;
    private PropertyRepository pr;
    private CountryRepository cr;
    private LocationRepository lr;

    public PropertyController(HotelService hs, PropertyRepository pr, CountryRepository cr, LocationRepository lr) {
        this.hs = hs;
        this.pr = pr;
        this.cr = cr;
        this.lr = lr;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> addPropertyUser(@RequestParam String locationId ,
                                             @RequestParam String countryId,
                                             @RequestBody PropertyDto dto)
    {
            PropertyDto propertyDto = hs.addProperty(locationId , countryId ,dto );
            return new ResponseEntity<>(propertyDto, HttpStatus.CREATED);
    }

    @GetMapping("/searchProperty")
    public ResponseEntity<List<PropertyEntity>> searchProperty(@RequestParam String name)
    {
        List<PropertyEntity> propertyDetails = hs.searchProperty(name);
        return new ResponseEntity<>(propertyDetails,HttpStatus.OK);
    }
}
