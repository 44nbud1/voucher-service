//package com.danapprentech.debrief2.voucherservice.service;
//
////import com.danapprentech.debrief2.voucherservice.model.Company;
////import com.danapprentech.debrief2.voucherservice.repository.CompanyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CompanyServiceImpl implements CompanyService
//{
//    @Autowired
//    CompanyRepository companyRepository;
//
//    @Override
//    public Page<Company> findByCompanyName(Boolean CompanyName, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public Page<Company> findByIdCompany(String name, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public Boolean existsByIdCompany(Long idCompany) {
//        return companyRepository.existsByIdCompany(idCompany);
//    }
//
//    @Override
//    public Company save(Company company) {
//        return companyRepository.save(company);
//    }
//
//    @Override
//    public Optional<Company> findById(Long id) {
//        return companyRepository.findById(id);
//    }
//}
