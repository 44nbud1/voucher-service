package com.danapprentech.debrief2.voucherservice.service;

import com.danapprentech.debrief2.voucherservice.model.Merchant;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoucherOutletServiceImpl implements VoucherOutletService
{
    @Autowired
    MerchantRepository outletRepository;

    @Override
    public Optional<Merchant> findById(Long id) {
        return outletRepository.findById(id);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return outletRepository.save(merchant);
    }
}
