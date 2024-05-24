package com.praneethpj.companyms.company.messenging;

import com.praneethpj.companyms.company.dto.ReviewMessage;
import com.praneethpj.companyms.company.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private CompanyService companyService;

    public  ReviewMessageConsumer(CompanyService companyService){
    this.companyService=companyService;
    }

    @RabbitListener(queues = "CompanyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        System.out.println("ReviewMessage "+reviewMessage.getRating());
        companyService.updateCompanyReview(reviewMessage);
    }
}
