package com.bookyourhotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "favourite")
public class FavouriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "app_user_entity_id")
    private AppUserEntity appUserEntity;

    @ManyToOne
    @JoinColumn(name = "property_entity_id")
    private PropertyEntity propertyEntity;

}