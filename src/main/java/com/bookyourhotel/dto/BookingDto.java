package com.bookyourhotel.dto;

import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.PropertyEntity;
import lombok.Data;

@Data
public class BookingDto {

    private String id;
    private String name;
    private String emailId;
    private String mobile;
    private int price;
    private int totalNights;
    private PropertyEntity entity;
    private AppUserEntity user;
}
