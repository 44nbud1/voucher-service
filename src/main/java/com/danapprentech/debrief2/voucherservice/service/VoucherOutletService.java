package com.danapprentech.debrief2.voucherservice.service;

import com.danapprentech.debrief2.voucherservice.model.Merchant;

import java.util.Optional;

public interface VoucherOutletService
{
    Optional<Merchant> findById(Long id);
    Merchant save(Merchant merchantId);
}
