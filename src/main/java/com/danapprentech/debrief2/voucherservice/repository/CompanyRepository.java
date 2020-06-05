//package com.danapprentech.debrief2.voucherservice.repository;
//
//import com.danapprentech.debrief2.voucherservice.model.Company;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CompanyRepository extends JpaRepository<Company, Long> {
////    @Query("select c from user_companies c where lower(c.company_name) like lower(concat('%', ?1,'%'))")
//    //idCompany
//    Page<Company> findByCompanyName(Boolean CompanyName, Pageable pageable);
//    Page<Company> findByIdCompany(Long name, Pageable pageable);
//    Boolean existsByIdCompany(Long idCompany);
//}