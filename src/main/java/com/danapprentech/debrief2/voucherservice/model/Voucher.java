package com.danapprentech.debrief2.voucherservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "voucher")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Voucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long idVoucher;

	@Column(name = "id_user") // --------1
	@NotNull
	@JsonIgnore
	private String idUser;

	@Column(name = "voucher_name") // --------b
	@NotNull
	private String voucherName;

	@Column(name = "voucher_price") // --------2
	@NotNull
	private Double voucherPrice;

	@Column(name = "discount") // --------d
	@NotNull
	private Double discount;

	@Column(name = "max_discount") // --------e
	@NotNull
	private Double maxDiscount;

	@Column(name = "quota")// --------g
	@NotNull
	private Integer quota;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "expired_date")
	@NotNull
	private Date expiredDate; // -------- f

	@Column(name = "status")  // -------- a
	@NotNull
	private Boolean status;

	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@NotNull
	private Date createAt;

	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name = "update_at")
	@NotNull
	private Date updateAt;

	// mapping to relational database
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_merchant", nullable = false) // --------9
	@JsonIgnore
	@NotNull
	private Merchant merchant;

}
