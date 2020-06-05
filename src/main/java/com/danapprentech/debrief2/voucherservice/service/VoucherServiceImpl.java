package com.danapprentech.debrief2.voucherservice.service;

import com.danapprentech.debrief2.voucherservice.model.Voucher;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService
{
    VoucherRepository voucherRepository;

    @Override
    public Page<Voucher> findByMerchantCategoryContaining(String merchantCategory, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Voucher> findByIdVoucher(Long id, Pageable pageable)
    {
        return null;
    }

    @Override
    public Voucher findByIdVoucher(Long id)
    {
        return voucherRepository.findByIdVoucher(id);
    }

    @Override
    public Voucher save(Voucher voucher)
    {
        return voucherRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> findById(Long id)
    {
        return voucherRepository.findById(id);
    }

    @Override
    public Page<Voucher> findAll()
    {
        return (Page<Voucher>) voucherRepository.findAll();
    }
}
