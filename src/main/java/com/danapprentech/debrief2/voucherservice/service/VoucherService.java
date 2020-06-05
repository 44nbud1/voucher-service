package com.danapprentech.debrief2.voucherservice.service;

import com.danapprentech.debrief2.voucherservice.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VoucherService
{
    Page<Voucher> findByMerchantCategoryContaining(String merchantCategory, Pageable pageable);
    Page<Voucher> findByIdVoucher(Long id, Pageable pageable);
    Voucher findByIdVoucher(Long id);
    Voucher save(Voucher voucher);
    Optional<Voucher>findById(Long id);
    Page<Voucher> findAll();
}
