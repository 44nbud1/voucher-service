package com.danapprentech.debrief2.voucherservice.restcontroller;

import com.danapprentech.debrief2.voucherservice.model.Merchant;
import com.danapprentech.debrief2.voucherservice.model.MerchantCategory;
import com.danapprentech.debrief2.voucherservice.model.Voucher;
import com.danapprentech.debrief2.voucherservice.model.response.MessageResponse;
import com.danapprentech.debrief2.voucherservice.model.response.VoucherOutletResponse;
import com.danapprentech.debrief2.voucherservice.repository.MerchantCategoryRepository;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
//        if (true)
//        {
//            return new ResponseEntity<>(new MessageResponse("voucher not found","062"),
//                    HttpStatus.NOT_FOUND);
//        }

        Page<Voucher> vouchers = voucherRepository.findAll(
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<Voucher>> voucherResponses = new ArrayList<>();
        voucherResponses.add(vouchers);

        Map vouchersRes = new HashMap<>();
        vouchersRes.put("data",voucherResponses);
        vouchersRes.put("timestamp",new Date());
        vouchersRes.put("path","/api/user/show-all-voucher");
        vouchersRes.put("message","Vouchers are successfully collected");
        vouchersRes.put("status","040");
        return ResponseEntity.ok(vouchersRes);
    }

    // /api/user/filter-voucher?merchantCategory={category}&page={page}
    @GetMapping("/filter-voucher")
    public ResponseEntity<?> filterByMerchantCategory(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> merchantCategory,
            @RequestParam(defaultValue = "merchantCategory") String sortBy)
    {
//        Page<MerchantCategory> vouchers = merchantCategoryRepository.findByMerchantCategoryContaining(merchantCategory.orElse("_"),
//                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        MerchantCategory merchantsCat = merchantCategoryRepository.findByMerchantCategoryContaining(
                merchantCategory.orElse("_"));

        if (merchantsCat == null)
        {
            return new ResponseEntity<>(new MessageResponse("voucher not found","062",
                    "/api/user/filter-voucher",new Date()),
                    HttpStatus.NOT_FOUND);
        }

        String category = merchantsCat.getMerchantCategory();

        if (category.equalsIgnoreCase("fnb"))
        {
            Merchant merchants = merchantRepository.findByMerchantNameContainingIgnoreCase("kfc");

            List<Voucher> vouc = merchants.getVouchers();
            Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy);

            Long start = pageable.getOffset();
            Long end = (start + pageable.getPageSize()) > vouc.size() ? vouc.size() : (start + pageable.getPageSize());

            int a = start.intValue();
            System.out.println(a);
            int b = end.intValue();
            System.out.println(b);

            Page<Voucher> pages = new PageImpl<Voucher>(vouc.subList(a, b), pageable, vouc.size());

            List<Page<Merchant>> merchantsResponse = new ArrayList<>();
            List<Voucher> voucherssss = merchants.getVouchers();

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(pages);

            Map merchantResp = new HashMap<>();
            merchantResp.put("data",jsonArray);
            merchantResp.put("timestamp",new Date());
            merchantResp.put("path","/api/user/filter-voucher");
            merchantResp.put("idMerchantCategory",merchantsCat.getIdMerchantCategory() );
            merchantResp.put("merchantCategory",merchantsCat.getMerchantCategory() );
            merchantResp.put("message","Vouchers are successfully collected");
            merchantResp.put("status","040");
            return ResponseEntity.ok(merchantResp);
        }

        if (category.equalsIgnoreCase("onlineTransaction"))
        {
            Merchant merchants = merchantRepository.findByMerchantNameContainingIgnoreCase("telkom");

            List<Voucher> vouc = merchants.getVouchers();
            Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy);

            Long start = pageable.getOffset();
            Long end = (start + pageable.getPageSize()) > vouc.size() ? vouc.size() : (start + pageable.getPageSize());

            int a = start.intValue();
            System.out.println(a);
            int b = end.intValue();
            System.out.println(b);

            Page<Voucher> pages = new PageImpl<Voucher>(vouc.subList(a, b), pageable, vouc.size());

            List<Page<Merchant>> merchantsResponse = new ArrayList<>();
            List<Voucher> voucherssss = merchants.getVouchers();

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(pages);

            Map merchantResp = new HashMap<>();
            merchantResp.put("data",jsonArray);
            merchantResp.put("timestamp",new Date());
            merchantResp.put("path","/api/user/filter-voucher");
            merchantResp.put("idMerchantCategory",merchantsCat.getIdMerchantCategory() );
            merchantResp.put("merchantCategory",merchantsCat.getMerchantCategory() );
            merchantResp.put("message","Vouchers are successfully collected");
            merchantResp.put("status","040");
            return ResponseEntity.ok(merchantResp);
        }

        return new ResponseEntity<>(new MessageResponse("voucher not found","062",
                "/api/user/filter-voucher",new Date()),
                HttpStatus.NOT_FOUND);
    }

    ///api/user/search-voucher?nameVoucher={name}&page={page}
    @GetMapping("/findByMerchantName-voucher")
    public ResponseEntity<?> findByMerchantName(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> merchantName,
            @RequestParam(defaultValue = "merchantName") String sortBy)
    {
        Merchant merchants = merchantRepository.findByMerchantNameContainingIgnoreCase(merchantName.orElse(""));

        if (merchants == null)
        {
            return new ResponseEntity<>(new MessageResponse("voucher not found","062",
                    "/api/user/filter-voucher",new Date()),
                    HttpStatus.NOT_FOUND);
        }

        List<Voucher> vouc = merchants.getVouchers();
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy);

        Long start = pageable.getOffset();
        Long end = (start + pageable.getPageSize()) > vouc.size() ? vouc.size() : (start + pageable.getPageSize());

        int a = start.intValue();
        System.out.println(a);
        int b = end.intValue();
        System.out.println(b);

        Page<Voucher> pages = new PageImpl<Voucher>(vouc.subList(a, b), pageable, vouc.size());

        List<Page<Merchant>> merchantsResponse = new ArrayList<>();
        List<Voucher> voucherssss = merchants.getVouchers();

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(pages);

        Map merchantResp = new HashMap<>();
        merchantResp.put("data",jsonArray);
        merchantResp.put("timestamp",new Date());
        merchantResp.put("path","/api/user/findByMerchantName-voucher");
        merchantResp.put("idMerchant",merchants.getIdMerchant() );
        merchantResp.put("merchantName",merchants.getMerchantName() );
        merchantResp.put("message","Vouchers are successfully collected");
        merchantResp.put("status","040");
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
            return new ResponseEntity<>(new MessageResponse("Please fill sort criteria","064",
                    "/api/user/sort-voucher",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRepository.findAll() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Voucher not found","062",
                    "/api/user/sort-voucher",new Date()),
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
