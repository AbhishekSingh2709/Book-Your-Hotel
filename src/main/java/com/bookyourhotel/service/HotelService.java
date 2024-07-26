package com.bookyourhotel.service;

import com.bookyourhotel.dto.CountryDto;
import com.bookyourhotel.dto.LocationDto;
import com.bookyourhotel.dto.PropertyDto;
import com.bookyourhotel.entity.CountryEntity;
import com.bookyourhotel.entity.LocationEntity;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.repository.CountryRepository;
import com.bookyourhotel.repository.LocationRepository;
import com.bookyourhotel.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private PropertyRepository pr;
    private LocationRepository lr;
    private CountryRepository cr;

    public HotelService(PropertyRepository pr, LocationRepository lr, CountryRepository cr) {
        this.pr = pr;
        this.lr = lr;
        this.cr = cr;
    }

    public PropertyDto addProperty( Long locationId , Long countryId , PropertyDto dto)
    {
        Optional<LocationEntity> opLId = lr.findById(locationId);
        LocationEntity locationDetails = opLId.get();

        Optional<CountryEntity> opCId = cr.findById(countryId);
        CountryEntity countryDetails = opCId.get();

        PropertyEntity entity = new PropertyEntity();
        entity.setName(dto.getName());
        entity.setNoGuests(dto.getNoGuests());
        entity.setNoBedrooms(dto.getNoBedrooms());
        entity.setNoBathrooms(dto.getNoBathrooms());
        entity.setPrice(dto.getPrice());
        entity.setLocation(locationDetails);
        entity.setCountry(countryDetails);

        PropertyEntity savedProperty = pr.save(entity);

        PropertyDto dtoProperty = new PropertyDto();
        dtoProperty.setId(savedProperty.getId());
        dtoProperty.setName(savedProperty.getName());
        dtoProperty.setNoGuests(savedProperty.getNoGuests());
        dtoProperty.setNoBedrooms(savedProperty.getNoBedrooms());
        dtoProperty.setNoBathrooms(savedProperty.getNoBathrooms());
        dtoProperty.setPrice(savedProperty.getPrice());
        dtoProperty.setLocation(savedProperty.getLocation());
        dtoProperty.setCountry(savedProperty.getCountry());

        return dtoProperty;
    }


    public LocationDto addLocation(LocationDto dto)
    {
        LocationEntity entity = new LocationEntity();
        entity.setName(dto.getName());

        LocationEntity savedLocation = lr.save(entity);

        LocationDto dtoLocation = new LocationDto();
        dtoLocation.setId(savedLocation.getId());
        dtoLocation.setName(savedLocation.getName());

        return dtoLocation;
    }

    public CountryDto addCountry(CountryDto dto)
    {
        CountryEntity entity = new CountryEntity();
        entity.setName(dto.getName());
        CountryEntity savedCountry = cr.save(entity);

        CountryDto dtoCountry = new CountryDto();
        dtoCountry.setId(savedCountry.getId());
        dtoCountry.setName(savedCountry.getName());

        return dtoCountry;
    }

    public List<PropertyEntity> searchProperty(String name)
    {
        List<PropertyEntity> properties = pr.searchProperty(name);
        return properties;
    }

}
