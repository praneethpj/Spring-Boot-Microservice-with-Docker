package com.praneethpj.companyms.company.service;


import com.praneethpj.companyms.company.dto.ReviewMessage;
import com.praneethpj.companyms.company.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompany();

    boolean createCompany(Company company);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);

    boolean updateCompany(Long id,Company updatedCompany);

    void updateCompanyReview(ReviewMessage reviewMessage);
}
