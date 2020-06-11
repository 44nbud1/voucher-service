package com.danapprentech.debrief2.voucherservice.repository;

import com.danapprentech.debrief2.voucherservice.model.MerchantCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCategoryRepository extends JpaRepository<MerchantCategory,Long>
{
    MerchantCategory findByIdMerchantCategory(Long id);
    Page<MerchantCategory> findByMerchantCategoryContaining(String merchant, Pageable pageable);
    MerchantCategory findByMerchantCategoryContaining(String merchant);
}
