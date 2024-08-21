package com.bookyourhotel.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "reviews")
public class ReviewsEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "ratings", nullable = false)
    private Integer ratings;

    @Column(name = "description", length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "app_user_entity_id")
    private AppUserEntity appUserEntity;

    @ManyToOne
    @JoinColumn(name = "property_entity_id")
    private PropertyEntity propertyEntity;

}