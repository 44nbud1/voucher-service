package com.danapprentech.debrief2.voucherservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherOutletResponse
{
    private Long idVoucherOutlets;
    private String outletName;
    private Integer idOutlet;
    private Double lat;
    private Double ion;
}
