package com.danapprentech.debrief2.voucherservice.restcontroller;

import com.danapprentech.debrief2.voucherservice.exception.NotFoundException;
import com.danapprentech.debrief2.voucherservice.model.*;
import com.danapprentech.debrief2.voucherservice.model.request.*;
import com.danapprentech.debrief2.voucherservice.model.response.*;
//import com.danapprentech.debrief2.voucherservice.repository.CompanyRepository;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import com.danapprentech.debrief2.voucherservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
public class AdminRestController {

	@Autowired
    VoucherRepository voucherRepository;

	@Autowired
    VoucherOutletServiceImpl voucherOutletService;

	@Autowired
	MerchantRepository merchantRepository;

	@Autowired
    VoucherServiceImpl voucherService;


    @PostMapping("/admin/{idUser}/merchant/{idMerchant}/vouchers")
    public ResponseEntity<?> createOutlet(@PathVariable Long idMerchant,
                                          @PathVariable String idUser,
                             @RequestBody VoucherRequest voucherRequest)
    {
        return voucherOutletService.findById(idMerchant)
            .map(merchant -> {
                Voucher vouchers = new Voucher();
                vouchers.setIdUser(idUser);
                vouchers.setMaxDiscount(voucherRequest.getMaxDiscount());
                vouchers.setExpiredDate(voucherRequest.getExpiredDate());
                vouchers.setQuota(voucherRequest.getQuota());
                vouchers.setVoucherPrice(voucherRequest.getVoucherPrice());
                vouchers.setVoucherName(voucherRequest.getVoucherName());
                vouchers.setDiscount(voucherRequest.getDiscount());
                vouchers.setStatus(voucherRequest.getStatus());
                vouchers.setCreateAt(new Date());
                vouchers.setUpdateAt(new Date());
                vouchers.setMerchant(merchant);
                voucherRepository.save(vouchers);

                // response
                VoucherResponse voucherResponse = new VoucherResponse();
                voucherResponse.setVoucherName(voucherRequest.getVoucherName());
                voucherResponse.setDiscount(voucherRequest.getDiscount());
                voucherResponse.setVoucherPrice(voucherRequest.getVoucherPrice());
                voucherResponse.setMaxDiscount(voucherRequest.getMaxDiscount());
                voucherResponse.setQuota(voucherRequest.getQuota());
                voucherResponse.setExpiredDate(voucherRequest.getExpiredDate());
                voucherResponse.setStatus(voucherRequest.getStatus());
                voucherResponse.setMerchantId(vouchers.getMerchant().getIdMerchant());

                List<VoucherResponse> voucherResponses = new ArrayList<>();
                voucherResponses.add(voucherResponse);
                Map vouchersRes = new HashMap<>();
                vouchersRes.put("data",voucherResponses);
                vouchersRes.put("message","Successfully");
                vouchersRes.put("status","200");

                return ResponseEntity.ok(vouchersRes);
            }).orElseThrow(() -> new NotFoundException("Company not found","400"));
    }

    @GetMapping("/admin/show-all-voucher")
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

    ///admin/filter-voucher?sortBy={name}&page={page}
    @GetMapping("/admin/filterByStatus-voucher")
    public ResponseEntity<?> filterByStatus(
            @RequestParam Optional<Integer> page,
            @RequestParam String filterByStatus,
            @RequestParam(defaultValue = "voucherName") String sortBy)
    {
        Boolean status;
        if (filterByStatus.equalsIgnoreCase("true"))
        {
            status = Boolean.TRUE;
        } else {
            status = Boolean.FALSE;
        }

        Page<Voucher> vouchers = voucherRepository.findByStatus(status,
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<Voucher>> voucherResponses = new ArrayList<>();
        voucherResponses.add(vouchers);

        Map vouchersRes = new HashMap<>();
        vouchersRes.put("data",voucherResponses);
        vouchersRes.put("message","Successfully");
        vouchersRes.put("status","200");

        return ResponseEntity.ok(vouchersRes);
    }
        //  /admin/filterByMerchantName-voucher?merchantName=
    @GetMapping("/admin/findByMerchantName-voucher")
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

    //  /admin/filterByMerchantName-voucher?merchantName=
    @GetMapping("/admin/voucher-detail-voucher/{idVoucher}")
    public ResponseEntity<?> voucherDetail(

            @PathVariable Long idVoucher)

    {
        Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setStatus(vouchers.getStatus());
        voucherResponse.setExpiredDate(vouchers.getExpiredDate());
        voucherResponse.setQuota(vouchers.getQuota());
        voucherResponse.setMaxDiscount(vouchers.getMaxDiscount());
        voucherResponse.setDiscount(vouchers.getDiscount());
        voucherResponse.setMerchantId(vouchers.getMerchant().getIdMerchant());
        voucherResponse.setVoucherName(vouchers.getVoucherName());
        voucherResponse.setVoucherPrice(vouchers.getVoucherPrice());

        List<VoucherResponse> merchantsResponse = new ArrayList<>();
        merchantsResponse.add(voucherResponse);

        Map merchantResp = new HashMap<>();
        merchantResp.put("data",merchantsResponse);
        merchantResp.put("message","Successfully");
        merchantResp.put("status","200");

        return ResponseEntity.ok(merchantResp);
    }

    @PutMapping("/admin/update-to-in-active/{idVoucher}")
    public ResponseEntity<?> update(
            @PathVariable Long idVoucher,
            @RequestBody ToInActiveRequest toInActiveRequest)
    {

        Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);
        if (vouchers.getStatus() == false)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Status Allready InActive","400"));
        }

        if (toInActiveRequest.getStatus() == true)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Wrong status","400"));
        }

        vouchers.setStatus(toInActiveRequest.getStatus());
        vouchers.setUpdateAt(new Date());
        voucherRepository.save(vouchers);

        return ResponseEntity.ok(new MessageResponse("Successfully Changed Status","200"));

    }

    @PutMapping("/admin/update-to-active/{idVoucher}/restock")
    public ResponseEntity<?> updateToActive(
            @PathVariable Long idVoucher,
            @RequestBody ToActive toActive)
    {

        Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);

        if (vouchers == null)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Voucher not found","400"));
        }

        if (toActive.getStatus() == false)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Wrong status","400"));
        }


        if (vouchers.getQuota() < 5)
        {
            vouchers.setQuota(5);
        }

        if (toActive.getUpdateQty() != null)
        {
            vouchers.setQuota(toActive.getUpdateQty());
        }

        vouchers.setStatus(toActive.getStatus());
        vouchers.setQuota(vouchers.getQuota());
        vouchers.setUpdateAt(new Date());
        voucherRepository.save(vouchers);

        return ResponseEntity.ok(new MessageResponse("Successfully Changed Status","200"));
    }

    @PutMapping("/admin/restock-voucher/{idVoucher}")
    public ResponseEntity<?> restockVoucher(
            @PathVariable Long idVoucher,
            @RequestBody RestockRequest restockRequest)
    {

        Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);
        if (vouchers == null)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Voucher not found","404"));
        }

        if (vouchers.getStatus() == false)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Status voucher inactive","400"));
        }

        vouchers.setQuota(restockRequest.getUpdateQty());
        vouchers.setUpdateAt(new Date());
        voucherRepository.save(vouchers);

        return ResponseEntity.ok(new MessageResponse("Stock Allready updated","200"));

    }

    @PutMapping("/admin/update-voucher/{idVoucher}")
    public ResponseEntity<?> updateVoucher(
            @PathVariable Long idVoucher,
            @RequestBody ToInActiveRequest toInActiveRequest)
    {

        Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);
        if (vouchers.getStatus() == false)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Status Allready InActive","400"));
        }

        if (toInActiveRequest.getStatus() == true)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Wrong status","400"));
        }

        vouchers.setStatus(toInActiveRequest.getStatus());
        vouchers.setUpdateAt(new Date());
        voucherRepository.save(vouchers);

        return ResponseEntity.ok(new MessageResponse("Successfully Changed Status","200"));

    }



}