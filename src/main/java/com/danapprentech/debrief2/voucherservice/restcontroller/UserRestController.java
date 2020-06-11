package com.danapprentech.debrief2.voucherservice.restcontroller;

import com.danapprentech.debrief2.voucherservice.model.Merchant;
import com.danapprentech.debrief2.voucherservice.model.MerchantCategory;
import com.danapprentech.debrief2.voucherservice.model.Voucher;
import com.danapprentech.debrief2.voucherservice.model.response.MessageResponse;
import com.danapprentech.debrief2.voucherservice.repository.MerchantCategoryRepository;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController
{
    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    MerchantCategoryRepository merchantCategoryRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @GetMapping("/show-all-voucher")
    public ResponseEntity<?> getAllVoucher(
            @RequestParam Optional<Integer> page,
            @RequestParam(defaultValue = "voucherName") String sortBy)
    {
        Page<Voucher> vouchers = voucherRepository.findAll(
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<Voucher>> voucherResponses = new ArrayList<>();
        voucherResponses.add(vouchers);

        Map vouchersRes = new HashMap<>();
        vouchersRes.put("data",voucherResponses);
        vouchersRes.put("message","Successfully");
        vouchersRes.put("status","200");
        return ResponseEntity.ok(vouchersRes);
    }

    // /api/user/filter-voucher?merchantCategory={category}&page={page}
    @GetMapping("/filter-voucher")
    public ResponseEntity<?> filterByMerchantCategory(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> merchantCategory,
            @RequestParam(defaultValue = "merchantCategory") String sortBy)
    {
        Page<MerchantCategory> vouchers = merchantCategoryRepository.findByMerchantCategoryContaining(merchantCategory.orElse("_"),
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<MerchantCategory>> voucherResponses = new ArrayList<>();
        voucherResponses.add(vouchers);

        Map vouchersRes = new HashMap<>();
        vouchersRes.put("data",voucherResponses);
        vouchersRes.put("message","Successfully");
        vouchersRes.put("status","200");
        return ResponseEntity.ok(vouchersRes);

    }

    ///api/user/search-voucher?nameVoucher={name}&page={page}
    @GetMapping("/findByMerchantName-voucher")
    public ResponseEntity<?> findByMerchantName(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> merchantName,
            @RequestParam(defaultValue = "merchantName") String sortBy)
    {

        Page<Merchant> merchants = merchantRepository.findByMerchantNameContainingIgnoreCase(merchantName.orElse(""),
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<Merchant>> merchantsResponse = new ArrayList<>();
        merchantsResponse.add(merchants);

        Map merchantResp = new HashMap<>();
        merchantResp.put("data",merchantsResponse);
        merchantResp.put("message","Successfully");
        merchantResp.put("status","200");

        return ResponseEntity.ok(merchantResp);
    }

    ///api/user/sort-voucher?sortBy={name}&page={page} //
    // discount voucherPrice
    @GetMapping("/sort-voucher")
    public ResponseEntity<?> SortByMerchantName(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy)
    {
        if (sortBy == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill sort criteria","400"),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRepository.findAll() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Voucher not found","400"),
                    HttpStatus.BAD_REQUEST);
        }

        Page<Voucher> merchants = voucherRepository.findAll(
                PageRequest.of(page.orElse(0), 10, Sort.Direction.DESC, sortBy.orElse("voucherName")));

        List<Page<Voucher>> merchantsResponse = new ArrayList<>();
        merchantsResponse.add(merchants);

        Map merchantResp = new HashMap<>();
        merchantResp.put("data",merchantsResponse);
        merchantResp.put("message","Successfully");
        merchantResp.put("status","200");

        return ResponseEntity.ok(merchantResp);

    }
}
