package com.praneethpj.jobms.job.impl;

import com.praneethpj.jobms.job.clients.CompanyClients;
import com.praneethpj.jobms.job.clients.ReviewClients;
import com.praneethpj.jobms.job.dto.JobWithCompanyDTO;
import com.praneethpj.jobms.job.external.Company;
import com.praneethpj.jobms.job.external.Review;
import com.praneethpj.jobms.job.model.Job;
import com.praneethpj.jobms.job.repository.JobRepository;
import com.praneethpj.jobms.job.service.JobService;
import com.praneethpj.jobms.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobrepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CompanyClients companyClients;

    @Autowired
    ReviewClients reviewClients;

    int attempt=0;

    public JobServiceImpl(JobRepository jobRepository) {
         this.jobrepository=jobRepository;
    }

    @Override
//    @CircuitBreaker(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    //@Retry(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobWithCompanyDTO> findAllJobs() {
        System.out.println("ATTEMPT : "+ ++attempt);
        List<Job> jobList= jobrepository.findAll();



        return jobList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list=new ArrayList<String>();
        list.add("DUMMY VALUE");
        return list;
    }

    @Override
    public boolean createJob(Job job) {
     try{
         jobrepository.save(job);
         return true;
     }catch (Exception e){
         return false;
     }
    }

    private JobWithCompanyDTO convertToDto(Job job){
//        JobWithCompanyDTO jobWithCompanyDTO=new JobWithCompanyDTO();
////        jobWithCompanyDTO.setJob(job);
//
//
//
    // Company company=restTemplate.getForObject("http://COMPANYMS:8081/companies/getByIdComapny/"+job.getCompanyId(), Company.class);



//     ResponseEntity<List<Review>> reviewResponse= restTemplate.exchange("http://REVIEWMS:8083/review/getReviewById?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//        });

        Company company=companyClients.getCompany(job.getCompanyId());

     List<Review> reviews=reviewClients.getReviewsByCompanyId(job.getCompanyId());

     JobWithCompanyDTO jobWithCompanyDTO= JobMapper.mapToJobCompanyDto(job,company,reviews);

//        jobWithCompanyDTO.setCompany(company);

        return  jobWithCompanyDTO;
    }

    @Override
    public Job getJobById(Long id) {
      return jobrepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobrepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return  false;
        }

    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
     try{
         Optional<Job> isJob =  jobrepository.findById(id);
         if(isJob.isPresent()){
             Job job=isJob.get();
             job.setDescription(updatedJob.getDescription());
             job.setLocation(updatedJob.getLocation());
             job.setTitle(updatedJob.getTitle());
             job.setMinSalary(updatedJob.getMinSalary());
             job.setMaxSalary(updatedJob.getMaxSalary());
             return true;
         }else{
             return false;
         }

     }catch (Exception e){
         return false;
        }

    }
}
