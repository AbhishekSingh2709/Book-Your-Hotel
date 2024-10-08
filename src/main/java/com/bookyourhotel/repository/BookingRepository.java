package com.bookyourhotel.repository;

import com.bookyourhotel.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
}