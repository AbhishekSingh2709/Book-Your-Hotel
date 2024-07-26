package com.bookyourhotel.dto;

import com.bookyourhotel.entity.PropertyEntity;
import lombok.Data;

@Data
public class ImagesDto
{
    private long id;
    private String imageUrl;
    private PropertyEntity property;
}
