package com.danapprentech.debrief2.voucherservice;

import com.danapprentech.debrief2.voucherservice.model.Merchant;
import com.danapprentech.debrief2.voucherservice.model.MerchantCategory;
import com.danapprentech.debrief2.voucherservice.model.Voucher;
import com.danapprentech.debrief2.voucherservice.repository.MerchantCategoryRepository;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@EnableSwagger2
public class VoucherServiceApplication {


	public static void main(String[] args) {


		SpringApplication.run(VoucherServiceApplication.class, args);

	}

	@Bean
	public CommandLineRunner init(MerchantRepository repository,
								  MerchantCategoryRepository merchantCategoryRepository,
								  VoucherRepository voucherRepository) {
		return args -> {

//			Merchant merchantList1 = repository.findByIdMerchant(1001L);
			MerchantCategory merchantCategory1 = new MerchantCategory();
			merchantCategory1.setMerchantCategory("fnb");
			merchantCategory1.setIdMerchantCategory(2001L);
			merchantCategory1.setCreatedAt(new Date());
			merchantCategory1.setUpdatedAt(new Date());
			merchantCategoryRepository.save(merchantCategory1);

//			Merchant merchantList1 = repository.findByIdMerchant(1001L);
			MerchantCategory merchantCategory2 = new MerchantCategory();
			merchantCategory2.setMerchantCategory("onlineTransaction");
			merchantCategory2.setIdMerchantCategory(2002L);
			merchantCategory2.setCreatedAt(new Date());
			merchantCategory2.setUpdatedAt(new Date());
			merchantCategoryRepository.save(merchantCategory2);

			Merchant merchant1 = new Merchant();
			Merchant merchant2 = new Merchant();

			MerchantCategory merchantCategories1 = merchantCategoryRepository.findByIdMerchantCategory(2001L);
			merchant1.setCreatedAt(new Date());
			merchant1.setUpdatedAt(new Date());
			merchant1.setIdMerchant(1001L);
			merchant1.setMerchantName("kfc");
			merchant1.setMerchantCategory(merchantCategories1);
			repository.save(merchant1);

			MerchantCategory merchantCategories2 = merchantCategoryRepository.findByIdMerchantCategory(2002L);
			merchant2.setCreatedAt(new Date());
			merchant2.setUpdatedAt(new Date());
			merchant2.setIdMerchant(1002L);
			merchant2.setMerchantCategory(merchantCategories2);
			merchant2.setMerchantName("telkom");
			repository.save(merchant2);

			Voucher voucher = new Voucher();
			voucher.setStatus(Boolean.TRUE);
			voucher.setQuota(1000);
			voucher.setExpiredDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
					.parse("2021-06-15 15:30:14.332"));
			voucher.setMaxDiscount(5000D);
			voucher.setIdUser("11002");
			voucher.setCreateAt(new Date());
			voucher.setUpdateAt(new Date());
			voucher.setDiscount(10D);
			voucher.setMerchant(merchant2);
			voucher.setVoucherName("Bayar Indiehome");
			voucher.setVoucherPrice(10000D);
			voucherRepository.save(voucher);

			Voucher voucher2 = new Voucher();
			voucher2.setStatus(Boolean.TRUE);
			voucher2.setQuota(1000);
			voucher2.setExpiredDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
					.parse("2021-06-15 15:30:14.332"));
			voucher2.setMaxDiscount(5000D);
			voucher2.setIdUser("11002");
			voucher2.setCreateAt(new Date());
			voucher2.setUpdateAt(new Date());
			voucher2.setDiscount(10D);
			voucher2.setMerchant(merchant1);
			voucher2.setVoucherName("Crazy Deals");
			voucher2.setVoucherPrice(10000D);
			voucherRepository.save(voucher2);

		};


	}
}
