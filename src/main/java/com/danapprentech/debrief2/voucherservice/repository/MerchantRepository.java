package com.danapprentech.debrief2.voucherservice.repository;

import com.danapprentech.debrief2.voucherservice.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>
{
    Merchant findByIdMerchant(Long id);
    Merchant findByMerchantNameContainingIgnoreCase(String name);
    Page<Merchant> findByMerchantNameContainingIgnoreCase(String name, Pageable pageable);
    Merchant findByMerchantNameContaining(String name);
}
