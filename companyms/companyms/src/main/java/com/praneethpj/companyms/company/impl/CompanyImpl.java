package com.praneethpj.companyms.company.impl;


import com.praneethpj.companyms.company.clients.ReviewClient;
import com.praneethpj.companyms.company.dto.ReviewMessage;
import com.praneethpj.companyms.company.model.Company;
import com.praneethpj.companyms.company.respository.CompanyRepository;
import com.praneethpj.companyms.company.service.CompanyService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    ReviewClient reviewClient;

    public CompanyImpl(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }
    @Override
    public List<Company> getAllCompany() {
        return this.companyRepository.findAll();
    }

    @Override
    public boolean createCompany(Company company) {
        try{
            companyRepository.save(company);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        try{
            Optional<Company> isCompany =  companyRepository.findById(id);
            if(isCompany.isPresent()){
                Company company=isCompany.get();
                company.setDescription(company.getDescription());
                company.setJobsId(company.getJobsId());
                company.setName(company.getName());

                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void updateCompanyReview(ReviewMessage reviewMessage) {
        System.out.println("Description "+reviewMessage.getTitle());

        Company company =companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(()->new NotFoundException("Company not found "+reviewMessage.getCompanyId()));

        double averageRating =reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());

        company.setRating(averageRating);

        companyRepository.save(company);

    }


}
