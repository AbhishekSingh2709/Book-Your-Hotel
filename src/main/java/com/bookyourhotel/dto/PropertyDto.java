package com.bookyourhotel.dto;

import com.bookyourhotel.entity.CountryEntity;
import com.bookyourhotel.entity.LocationEntity;
import lombok.Data;

@Data
public class PropertyDto
{
    private String id;
    private String name;
    private Integer noGuests;
    private Integer noBedrooms;
    private Integer noBathrooms;
    private Integer price;
    private LocationEntity location;
    private CountryEntity country;
}
