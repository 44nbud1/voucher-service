package com.danapprentech.debrief2.voucherservice.restcontroller;

import com.danapprentech.debrief2.voucherservice.exception.NotFoundException;
import com.danapprentech.debrief2.voucherservice.exception.ValidationImpl;
import com.danapprentech.debrief2.voucherservice.model.*;
import com.danapprentech.debrief2.voucherservice.model.request.*;
import com.danapprentech.debrief2.voucherservice.model.response.*;
//import com.danapprentech.debrief2.voucherservice.rabbit.producer.RabbitMqProducer;
import com.danapprentech.debrief2.voucherservice.repository.MerchantRepository;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import com.danapprentech.debrief2.voucherservice.service.*;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
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
//
//	@Autowired
//    RabbitMqProducer rabbitMqProducer;

	@Autowired
    ValidationImpl validation;

    @PostMapping("/admin/{idUser}/merchant/{idMerchant}/vouchers")
    public ResponseEntity<?> createOutlet(@PathVariable Long idMerchant,
                                          @PathVariable String idUser,
                             @RequestBody VoucherRequest voucherRequest)
    {
        if (!idUser.equalsIgnoreCase("user01"))
        {
            return new ResponseEntity<>(new MessageResponse("User not found","022","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.NOT_FOUND);
        }

        if (voucherOutletService.findById(idMerchant) == null)
        {
            return new ResponseEntity<>(new MessageResponse("Id Merchant Not Found","054","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.NOT_FOUND);
        }

        if (voucherRequest.getVoucherName() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form voucher name","055","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getDiscount() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form discount","056","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getExpiredDate() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form expired date","057","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getMaxDiscount() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form max discount","058","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getQuota() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form quota","059","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getStatus() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form status","060","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }


        if (voucherRequest.getVoucherPrice() == null)
        {
            return new ResponseEntity<>(new MessageResponse("Please fill form voucher price","061","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (validation.NumberOnlyValidator(voucherRequest.getQuota()))
        {
            return new ResponseEntity<>(new MessageResponse("Your data is invalid","043","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getQuota() > 1000)
        {
            return new ResponseEntity<>(new MessageResponse("Maximum voucher quota of 1000","068","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRequest.getVoucherPrice() > 1000000)
        {
            return new ResponseEntity<>(new MessageResponse("Your data is invalid","043","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        Calendar calendarValidation = Calendar.getInstance();
        calendarValidation.setTime(voucherRequest.getExpiredDate());
        calendarValidation.add(Calendar.MONTH, 1);
        Date currentDatePlusOne = calendarValidation.getTime();

        Date aa = voucherRequest.getExpiredDate();


        if (aa.after(currentDatePlusOne))
        {
            return new ResponseEntity<>(new MessageResponse("test","068","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (!validation.aplhabetOnly(voucherRequest.getVoucherName()))
        {
            return new ResponseEntity<>(new MessageResponse("Voucher name is invalid","065","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        if (voucherRepository.findByVoucherName(voucherRequest.getVoucherName()) != null)
        {
            return new ResponseEntity<>(new MessageResponse("Voucher name is exist","053","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

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
//                rabbitMqProducer.sendToRabbitVoucher(vouchers);
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
                vouchersRes.put("timestamp",new Date());
                vouchersRes.put("path","/api/admin/"+idUser+"/merchant/"+idMerchant+"/vouchers");
                vouchersRes.put("message","Create voucher successfully");
                vouchersRes.put("status","042");

                return ResponseEntity.ok(vouchersRes);
            }).orElseThrow(() -> new NotFoundException("id Merchant not found","054"));
    }

    @GetMapping("/admin/show-all-voucher")
    public ResponseEntity<?> getAllVoucher(
            @RequestParam Optional<Integer> page,
            @RequestParam(defaultValue = "voucherName") String sortBy)
    {
        String check = String.valueOf(page);
        System.out.println(page);

        List<String> list = Arrays.asList(check);

        Optional<String> result = list.stream()
                .filter(x -> x.length() == 1)
                .findFirst();

        if (result.isPresent()) {
            System.out.println(result.get()); // a
        }

        if (check.contains("-"))
        {
            return new ResponseEntity<>(new MessageResponse("voucher not found","062","/api/admin/filterByStatus-voucher",new Date()),
                    HttpStatus.NOT_FOUND);
        }

        if (voucherRepository.findAll() == null)
        {
            return new ResponseEntity<>(new MessageResponse("voucher not found","062","/api/admin/filterByStatus-voucher",new Date()),
                    HttpStatus.NOT_FOUND);
        }

        Page<Voucher> vouchers = voucherRepository.findAll(
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<Voucher>> voucherResponses = new ArrayList<>();
        voucherResponses.add(vouchers);

        Map vouchersRes = new HashMap<>();
        vouchersRes.put("data",voucherResponses);
        vouchersRes.put("timestamp",new Date());
        vouchersRes.put("path","/api/admin/show-all-voucher");
        vouchersRes.put("message","Vouchers are successfully collected");
        vouchersRes.put("status","040");
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
        } else if (filterByStatus.equalsIgnoreCase("false")){
            status = Boolean.FALSE;
        } else {
            return new ResponseEntity<>(new MessageResponse("Status invalid","063","/api/admin/filterByStatus-voucher",new Date()),
                    HttpStatus.BAD_REQUEST);
        }

        Page<Voucher> vouchers = voucherRepository.findByStatus(status,
                PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy));

        List<Page<Voucher>> voucherResponses = new ArrayList<>();
        voucherResponses.add(vouchers);

        Map vouchersRes = new HashMap<>();
        vouchersRes.put("data",voucherResponses);
        vouchersRes.put("timestamp",new Date());
        vouchersRes.put("path","/api/admin/filterByStatus-voucher");
        vouchersRes.put("message","Vouchers are successfully collected");
        vouchersRes.put("status","040");

        return ResponseEntity.ok(vouchersRes);
    }
        //  /admin/filterByMerchantName-voucher?merchantName=
    @GetMapping("/admin/findByMerchantName-voucher")
    public ResponseEntity<?> findByMerchantName(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> merchantName,
            @RequestParam(defaultValue = "merchantName") String sortBy)
    {
        Merchant merchants = merchantRepository.findByMerchantNameContainingIgnoreCase(merchantName.orElse(""));

        if (merchants == null)
        {
            return new ResponseEntity<>(new MessageResponse("Page is not found","038",
                    "/api/admin/findByMerchantName-voucher",new Date()),
                    HttpStatus.BAD_REQUEST);
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
        merchantResp.put("path","/api/admin/findByMerchantName-voucher");
        merchantResp.put("idMerchant",merchants.getIdMerchant() );
        merchantResp.put("merchantName",merchants.getMerchantName() );
        merchantResp.put("message","Vouchers are successfully collected");
        merchantResp.put("status","040");
        return ResponseEntity.ok(merchantResp);
    }

    //  /admin/filterByMerchantName-voucher?merchantName=
    @GetMapping("/admin/voucher-detail-voucher/{idVoucher}")
    public ResponseEntity<?> voucherDetail(

            @PathVariable Long idVoucher)

    {
        if (voucherRepository.findByIdVoucher(idVoucher) == null)
        {
            return new ResponseEntity<>(new MessageResponse("Voucher not found","062","/api/admin/voucher-detail-voucher/"+idVoucher,new Date()),
                    HttpStatus.BAD_REQUEST);
        }

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
        merchantResp.put("timestamp",new Date());
        merchantResp.put("path","/api/admin/voucher-detail-voucher/"+idVoucher);
        merchantResp.put("message","Vouchers are successfully collected");
        merchantResp.put("status","040");

        return ResponseEntity.ok(merchantResp);
    }

    @PutMapping("/admin/update-status-voucher/{idVoucher}/restock")
    public ResponseEntity<?> updateVoucher(
            @PathVariable Long idVoucher,
            @RequestBody UpdateVoucherRequest updateVoucherRequest)
    {
        if (updateVoucherRequest.getStatus() == null)
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Status invalid","063",
                    "/api/admin/update-status-voucher/{idVoucher}/restock",new Date()));
        }

        // only change status
        if (updateVoucherRequest.getStatus() == true && updateVoucherRequest.getQuota() == null)
        {

            Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);

            if (vouchers == null)
            {
                return ResponseEntity.badRequest().body(new MessageResponse("Voucher not found","062",
                        "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
            }

            if (updateVoucherRequest.getStatus() == false)
            {
                return ResponseEntity.badRequest().body(new MessageResponse("Status invalid","063",
                        "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
            }

            if (vouchers.getQuota() < 5)
            {
                vouchers.setQuota(5);
            }

            vouchers.setStatus(Boolean.TRUE);
            vouchers.setQuota(vouchers.getQuota());
            vouchers.setUpdateAt(new Date());
            voucherRepository.save(vouchers);

            return ResponseEntity.ok(new MessageResponse("Successfully Change Status","044",
                    "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
        }

        // only change stock
        if (updateVoucherRequest.getStatus() == true && updateVoucherRequest.getQuota() != null)
        {

            Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);

            if (vouchers.getStatus() != false && updateVoucherRequest.getStatus())
            {
                return ResponseEntity.badRequest().body(new MessageResponse("Status invalid",
                        "063","/admin/update-status-voucher/{idVoucher}/restock",new Date()));
            }

            if (vouchers == null)
            {
                return ResponseEntity.badRequest().body(new MessageResponse("Voucher not found","062",
                        "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
            }

            if (updateVoucherRequest.getStatus() == false)
            {
                return ResponseEntity.badRequest().body(new MessageResponse("Status invalid","063",
                        "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
            }

            vouchers.setStatus(Boolean.TRUE);
            vouchers.setQuota(vouchers.getQuota()+ updateVoucherRequest.getQuota());
            vouchers.setUpdateAt(new Date());
            voucherRepository.save(vouchers);

            return ResponseEntity.ok(new MessageResponse("Successfully change status","044",
                    "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
        }

        // only change status
        if (updateVoucherRequest.getStatus() == false) {
            Voucher vouchers = voucherRepository.findByIdVoucher(idVoucher);

            vouchers.setStatus(Boolean.FALSE);
            vouchers.setUpdateAt(new Date());
            voucherRepository.save(vouchers);

            return ResponseEntity.ok(new MessageResponse("Successfully change status", "044",
                    "/admin/update-status-voucher/{idVoucher}/restock",new Date()));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Status invalid", "063",
                    "/admin/update-status-voucher/{idVoucher}/restock", new Date()));
        }

    }


}