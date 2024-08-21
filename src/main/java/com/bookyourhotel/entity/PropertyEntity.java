package com.bookyourhotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "property")
public class PropertyEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "no_guests", nullable = false)
    private Integer noGuests;

    @Column(name = "no_bedrooms", nullable = false)
    private Integer noBedrooms;

    @Column(name = "no_bathrooms", nullable = false)
    private Integer noBathrooms;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}