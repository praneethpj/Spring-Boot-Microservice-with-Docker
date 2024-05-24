package com.praneethpj.reviewms.review;



import com.praneethpj.reviewms.review.messenging.ReviewMessageProducer;
import com.praneethpj.reviewms.review.model.Review;
import com.praneethpj.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

//
//    public CompanyController(CompanyService companyService){
//        this.companyService=companyService;
//    }

    @GetMapping("/getReviewById")
    public ResponseEntity<List<Review>> getReviewByCompanyId(@RequestParam Long companyId){
        return ResponseEntity.ok(reviewService.getAllReview(companyId));
    }

    @PostMapping("/createReview")
    ResponseEntity<Boolean> createReview(@RequestBody Review review){
        reviewMessageProducer.sendMessage(review);
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    @PutMapping("updateReview/{id}")
    ResponseEntity<Boolean> updateReview(@PathVariable Long id, @RequestBody Review review){
        return ResponseEntity.ok(reviewService.updateReview(id,review));
    }

    @DeleteMapping("deleteReview/{id}")
    ResponseEntity<Boolean> deleteJobs(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.deleteReviewById(id));
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList=reviewService.getAllReview(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average()
                .orElse(0.0);
    }


}
