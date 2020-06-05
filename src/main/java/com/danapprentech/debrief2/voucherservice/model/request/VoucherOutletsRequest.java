package com.danapprentech.debrief2.voucherservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherOutletsRequest
{
    private Long idVoucherOutlets;
    private String outletName;
    private Integer idOutlet;
    private Double lat;
    private Double ion;
    private Date createdAt;
    private Date updatedAt;
}
