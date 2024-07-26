package com.bookyourhotel.controller;

import com.bookyourhotel.dto.ReviewsDto;
import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.ReviewsEntity;
import com.bookyourhotel.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Reviews")
public class ReviewsController
{
    private ReviewService rs;

    public ReviewsController(ReviewService rs) {
        this.rs = rs;
    }

    @PostMapping("/addReview")
    public ResponseEntity<?> postReviews(@AuthenticationPrincipal AppUserEntity user,
                                                  @RequestParam Long propertyId,
                                                  @RequestBody ReviewsDto reviews)
    {
        ResponseEntity<?> reviewsDetails = rs.addReview(user, propertyId, reviews);
        return new ResponseEntity<>(reviewsDetails, HttpStatus.OK);
    }

    @GetMapping("/getReviewsByUser")
    public ResponseEntity<List<ReviewsEntity>> getReviewsByUser(@AuthenticationPrincipal AppUserEntity user)
    {
        List<ReviewsEntity> reviews = rs.getReviews(user);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
