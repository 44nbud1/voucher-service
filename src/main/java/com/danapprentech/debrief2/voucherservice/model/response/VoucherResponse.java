package com.danapprentech.debrief2.voucherservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherResponse
{
    private String voucherName;
    private Double voucherPrice;
    private Double discount;
    private Double maxDiscount;
    private Integer quota;
    private Date expiredDate;
    private Boolean status;
    private Long merchantId;

}
