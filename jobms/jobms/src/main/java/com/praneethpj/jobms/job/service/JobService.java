package com.praneethpj.jobms.job.service;


import com.praneethpj.jobms.job.dto.JobWithCompanyDTO;
import com.praneethpj.jobms.job.model.Job;

import java.util.List;

public interface JobService {




    List<JobWithCompanyDTO> findAllJobs();

    boolean createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id,Job updatedJob);

}
