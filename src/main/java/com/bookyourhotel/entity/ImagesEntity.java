package com.bookyourhotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class ImagesEntity
{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "image_url", nullable = false, length = 2000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "property_entity_id")
    private PropertyEntity propertyEntity;

}