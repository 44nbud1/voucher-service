package com.danapprentech.debrief2.voucherservice.exception;

import java.util.Date;

public interface Validation
{
    boolean NumberOnlyValidator (Integer number);
    boolean voucherName (String voucherName);
    boolean aplhabetOnly(String voucher);
    boolean dateExp(Date date);

    //LengthUsername
    //NumberOnlyValidator
}
