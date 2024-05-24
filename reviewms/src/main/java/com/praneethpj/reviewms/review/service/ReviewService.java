package com.praneethpj.reviewms.review.service;


import com.praneethpj.reviewms.review.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReview(Long companyId);

    boolean createReview(Review review);


    boolean deleteReviewById(Long id);

    boolean updateReview(Long id,Review updatedReview);
}
