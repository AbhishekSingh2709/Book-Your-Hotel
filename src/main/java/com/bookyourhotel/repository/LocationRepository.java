package com.bookyourhotel.repository;

import com.bookyourhotel.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, String> {
    boolean existsById(String id);
}