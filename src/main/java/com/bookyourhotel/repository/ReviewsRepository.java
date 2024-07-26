package com.bookyourhotel.repository;

import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {

    @Query("select r from ReviewsEntity r where r.appUserEntity=:user and r.propertyEntity=:property")
    ReviewsEntity findReviewByUser(@Param("user") AppUserEntity user ,@Param("property") PropertyEntity property);

    @Query("select r from ReviewsEntity r where r.appUserEntity=:user")
    List<ReviewsEntity> findByUserReviews(@Param("user") AppUserEntity user);
}