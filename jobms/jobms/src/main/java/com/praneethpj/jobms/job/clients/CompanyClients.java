package com.praneethpj.jobms.job.clients;

import com.praneethpj.jobms.job.external.Company;
import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "COMPANYMS",url = "${company-service.url}")
public interface CompanyClients {

    @GetMapping("/companies/getByIdComapny/{id}")
    Company getCompany(@PathVariable Long id);
}
