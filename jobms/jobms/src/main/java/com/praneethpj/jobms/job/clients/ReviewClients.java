package com.praneethpj.jobms.job.clients;

import com.praneethpj.jobms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMS", url = "${review-service.url}")
public interface ReviewClients {

    @GetMapping("review/getReviewById")
    List<Review> getReviewsByCompanyId(@RequestParam Long companyId);

}
