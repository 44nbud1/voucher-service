package com.danapprentech.debrief2.voucherservice;

import com.danapprentech.debrief2.voucherservice.model.Merchant;
import com.danapprentech.debrief2.voucherservice.model.MerchantCategory;
import com.danapprentech.debrief2.voucherservice.repository.MerchantCategoryRepository;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@SpringBootApplication
@EnableSwagger2
public class VoucherServiceApplication {


	public static void main(String[] args) {


		SpringApplication.run(VoucherServiceApplication.class, args);

	}

	@Bean
	public CommandLineRunner init(MerchantRepository repository, MerchantCategoryRepository merchantCategoryRepository) {
		return args -> {

//			Merchant merchantList1 = repository.findByIdMerchant(1001L);
			MerchantCategory merchantCategory1 = new MerchantCategory();
			merchantCategory1.setMerchantCategory("F&B");
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
			merchant1.setMerchantName("KFC");
			merchant1.setMerchantCategory(merchantCategories1);
			repository.save(merchant1);

			MerchantCategory merchantCategories2 = merchantCategoryRepository.findByIdMerchantCategory(2002L);
			merchant2.setCreatedAt(new Date());
			merchant2.setUpdatedAt(new Date());
			merchant2.setIdMerchant(1002L);
			merchant2.setMerchantCategory(merchantCategories2);
			merchant2.setMerchantName("Telkom");
			repository.save(merchant2);

		};


	}
}
