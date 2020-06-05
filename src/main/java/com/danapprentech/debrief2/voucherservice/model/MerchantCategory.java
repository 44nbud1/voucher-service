package com.danapprentech.debrief2.voucherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "merchant_category")
public class MerchantCategory
{
    @Id
    @Column(name = "id_merchant_category")
    private Long idMerchantCategory;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "merchant_category")
    private String merchantCategory;

    @OneToMany(mappedBy = "merchantCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("merchants")
    private List<Merchant> merchants;
}
