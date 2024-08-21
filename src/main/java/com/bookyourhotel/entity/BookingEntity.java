package com.bookyourhotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "mobile", nullable = false, unique = true, length = 18)
    private String mobile;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "app_user_entity_id")
    private AppUserEntity appUserEntity;

    @ManyToOne
    @JoinColumn(name = "property_entity_id")
    private PropertyEntity propertyEntity;

    @Column(name = "total_nights")
    private Integer totalNights;

}