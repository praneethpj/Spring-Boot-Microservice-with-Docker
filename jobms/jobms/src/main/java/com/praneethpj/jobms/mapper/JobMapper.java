package com.praneethpj.jobms.mapper;

import com.praneethpj.jobms.job.dto.JobWithCompanyDTO;
import com.praneethpj.jobms.job.external.Company;
import com.praneethpj.jobms.job.external.Review;
import com.praneethpj.jobms.job.model.Job;

import java.util.List;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobCompanyDto(Job job, Company company, List<Review> reviews){
        JobWithCompanyDTO jobWithCompanyDTO=new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());

        jobWithCompanyDTO.setReviews(reviews);
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
