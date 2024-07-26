package com.bookyourhotel.repository;

import com.bookyourhotel.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long>
{
    @Query(" select p from PropertyEntity p JOIN LocationEntity l on p.location=l.id JOIN CountryEntity c on p.country=c.id where l.name=:locationName or c.name=:locationName")
    List<PropertyEntity> searchProperty(@Param("locationName") String locationName);
}