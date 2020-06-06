package com.danapprentech.debrief2.voucherservice.repository;

import com.danapprentech.debrief2.voucherservice.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long>
{

    Page<Voucher> findByIdVoucher(Long id, Pageable pageable);
    Voucher findByIdVoucher(Long id);
    Page<Voucher> findByStatus(Boolean status, Pageable pageable);
    Page<Voucher> findByVoucherNameContainingIgnoreCase(String voucherName, Pageable pageable);
    Voucher findByVoucherName(String voucherName);
    //voucherName
}
