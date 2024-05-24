package com.praneethpj.reviewms.review.impl;

import com.praneethpj.reviewms.review.model.Review;
import com.praneethpj.reviewms.review.respository.ReviewRepository;
import com.praneethpj.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReview(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Review review) {
     try{
           reviewRepository.save(review);
           return true;
       }catch (Exception e){
           return false;
       }

    }

    @Override
    public boolean deleteReviewById(Long id) {
        try{
            reviewRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateReview(Long id, Review updatedReview) {
        try{
            Review review=reviewRepository.findById(id).get();
            review.setDescription(updatedReview.getDescription());
            review.setTitle(updatedReview.getTitle());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);

            return true;
        }catch (Exception e){
            return false;
        }

    }
}
