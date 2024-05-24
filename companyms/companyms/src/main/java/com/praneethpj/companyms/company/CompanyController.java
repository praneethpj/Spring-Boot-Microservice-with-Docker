package com.praneethpj.companyms.company;


import com.praneethpj.companyms.company.model.Company;
import com.praneethpj.companyms.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
//
//    public CompanyController(CompanyService companyService){
//        this.companyService=companyService;
//    }

    @GetMapping("/getAllComapny")
    ResponseEntity<List<Company>> getAllJobs(){
        return ResponseEntity.ok(companyService.getAllCompany());
    }


    @PostMapping("/createComapny")
    ResponseEntity<Boolean> createJob(@RequestBody Company company){
        return ResponseEntity.ok(companyService.createCompany(company));
    }

    @PutMapping("updateComapny/{id}")
    ResponseEntity<Boolean> updateJobs(@PathVariable Long id, @RequestBody Company company){
        return ResponseEntity.ok(companyService.updateCompany(id,company));
    }

    @DeleteMapping("deleteComapny/{id}")
    ResponseEntity<Boolean> deleteJobs(@PathVariable Long id){
        return ResponseEntity.ok(companyService.deleteCompanyById(id));
    }

    @GetMapping("getByIdComapny/{id}")
    ResponseEntity<Company> getById(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }


}
