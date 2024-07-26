package com.bookyourhotel.repository;

import com.bookyourhotel.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
}