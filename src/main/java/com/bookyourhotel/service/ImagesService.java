package com.bookyourhotel.service;

import com.bookyourhotel.dto.ImagesDto;
import com.bookyourhotel.entity.ImagesEntity;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.repository.ImagesRepository;
import com.bookyourhotel.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ImagesService
{
    private ImagesRepository ir;
    private PropertyRepository pr;
    private BucketService bs;

    public ImagesService(ImagesRepository ir, PropertyRepository pr, BucketService bs) {
        this.ir = ir;
        this.pr = pr;
        this.bs = bs;
    }

    public ImagesDto uploadImages(MultipartFile file , String bucketName , String id)
    {
        PropertyEntity property = pr.findById(id).get();
        String imageUrl = bs.uploadFile(file, bucketName);

        ImagesEntity entity = new ImagesEntity();
        String imageId = UUID.randomUUID().toString();
        entity.setId(imageId);
        entity.setPropertyEntity(property);
        entity.setImageUrl(imageUrl);

        ImagesEntity savedImage = ir.save(entity);

        ImagesDto dto = new ImagesDto();
        dto.setId(savedImage.getId());
        dto.setImageUrl(savedImage.getImageUrl());
        dto.setProperty(savedImage.getPropertyEntity());
        return dto;

    }
}
