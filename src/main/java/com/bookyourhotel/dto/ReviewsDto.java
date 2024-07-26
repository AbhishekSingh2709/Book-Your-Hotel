package com.bookyourhotel.dto;

import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.PropertyEntity;
import lombok.Data;

@Data
public class ReviewsDto {

    private Long id;
    private Integer rating;
    private String description;
    private PropertyEntity propertyEntity;
    private AppUserEntity appUserEntity;
}
