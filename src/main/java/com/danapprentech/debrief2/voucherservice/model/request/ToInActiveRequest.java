package com.danapprentech.debrief2.voucherservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToInActiveRequest
{
    private Boolean status;
    private Integer quota;
}
