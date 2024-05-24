package com.praneethpj.jobms.job;

import com.praneethpj.jobms.job.dto.JobWithCompanyDTO;
import com.praneethpj.jobms.job.external.Company;
import com.praneethpj.jobms.job.model.Job;
import com.praneethpj.jobms.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/getAlljobs")
    ResponseEntity<List<JobWithCompanyDTO>> getAllJobs(){
//
//        List<Job> jobs=jobService.findAllJobs();
//        List<JobWithCompanyDTO> jobWithCompanyDTOList=new ArrayList<>();
//
//
//
//        RestTemplate restTemplate=new RestTemplate();
////        Company company1=restTemplate.getForObject("http://localhost:8081/companies/getByIdComapny/1", Company.class);
////
//        for(Job job:jobs ){
//            JobWithCompanyDTO jobWithCompanyDTO=new JobWithCompanyDTO();
//            jobWithCompanyDTO.setJob(job);
//
//            System.out.println("JO1 "+job.getCompanyId());
//            System.out.println("Jo2 "+job.getTitle());
//
//            Company company=restTemplate.getForObject("http://localhost:8081/companies/getByIdComapny/"+job.getCompanyId(), Company.class);
//            System.out.println("Com "+company.getName());
//
//            jobWithCompanyDTO.setCompany(company);
//
//
//        }
//
//        return ResponseEntity.ok(jobWithCompanyDTOList);

        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @PostMapping("/createJob")
    ResponseEntity<Boolean> createJob(@RequestBody Job job){
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @PutMapping("updateJob/{id}")
    ResponseEntity<Boolean> updateJobs(@PathVariable Long id, @RequestBody Job job){
        return ResponseEntity.ok(jobService.updateJob(id,job));
    }

    @DeleteMapping("deleteJob/{id}")
    ResponseEntity<Boolean> deleteJobs(@PathVariable Long id){
        return ResponseEntity.ok(jobService.deleteJobById(id));
    }

    @GetMapping("getByIdJob/{id}")
    ResponseEntity<Job> getById(@PathVariable Long id){
        return ResponseEntity.ok(jobService.getJobById(id));
    }

}
