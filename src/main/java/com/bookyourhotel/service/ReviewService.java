package com.bookyourhotel.service;

import com.bookyourhotel.dto.ReviewsDto;
import com.bookyourhotel.entity.AppUserEntity;
import com.bookyourhotel.entity.PropertyEntity;
import com.bookyourhotel.entity.ReviewsEntity;
import com.bookyourhotel.repository.PropertyRepository;
import com.bookyourhotel.repository.ReviewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService
{
    private ReviewsRepository rr;
    private PropertyRepository pr;

    public ReviewService(ReviewsRepository rr, PropertyRepository pr) {
        this.rr = rr;
        this.pr = pr;
    }

    public ResponseEntity<?> addReview(AppUserEntity user , Long id, ReviewsDto review )
    {
        Optional<PropertyEntity> opId = pr.findById(id);
        PropertyEntity propertyEntity = opId.get();

        if (rr.findReviewByUser(user, propertyEntity)==null) {
            ReviewsEntity re = new ReviewsEntity();
            re.setRatings(review.getRating());
            re.setDescription(review.getDescription());
            re.setAppUserEntity(user);
            re.setPropertyEntity(propertyEntity);
            ReviewsEntity save = rr.save(re);

            ReviewsDto rd = new ReviewsDto();
            rd.setId(save.getId());
            rd.setRating(save.getRatings());
            rd.setDescription(save.getDescription());
            rd.setAppUserEntity(save.getAppUserEntity());
            rd.setPropertyEntity(save.getPropertyEntity());
            return new ResponseEntity<>(rd,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User have already posted Review for this property " , HttpStatus.OK);
        }

    }
    public List<ReviewsEntity> getReviews(AppUserEntity user)
    {
        List<ReviewsEntity> reviews = rr.findByUserReviews(user);
        return (reviews);
    }
}
