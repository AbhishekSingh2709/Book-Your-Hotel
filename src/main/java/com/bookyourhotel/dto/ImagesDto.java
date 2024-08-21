package com.bookyourhotel.dto;

import com.bookyourhotel.entity.PropertyEntity;
import lombok.Data;

@Data
public class ImagesDto
{
    private String id;
    private String imageUrl;
    private PropertyEntity property;
}
