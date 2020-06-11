package com.danapprentech.debrief2.voucherservice.rabbit.consumer;

import com.danapprentech.debrief2.voucherservice.model.Voucher;
import com.danapprentech.debrief2.voucherservice.rabbit.model.UpdateQtyConsumer;
import com.danapprentech.debrief2.voucherservice.repository.VoucherRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Consumer
{
    @Autowired
    VoucherRepository voucherRepository;

    @RabbitListener(queues = "${spring.rabbitmq.queue.listener}",containerFactory = "createListener")
    public void updateQuota(UpdateQtyConsumer updateQtyConsumer)
    {
        // dapat data dari order
        // consume updateVoucherRequest = rabbitservice.....
            /*
            // String message = updateVoucherRequest.message();

            qtyVoucher ->
            idVoucher -> yang dibeli
             */
        // data dumy, seolah2 data diatas sebagai request postman

        // Optional

//        System.out.println(updateQtyConsumer.getIdVoucher());

        System.out.println("-------------");

        Voucher vouchers = voucherRepository.findByIdVoucher(updateQtyConsumer.getIdVoucher());
        System.out.println(vouchers.getQuota() );
        vouchers.setQuota(vouchers.getQuota()-1);
        vouchers.setUpdateAt(new Date());
        System.out.println("update sukses");
        System.out.println(vouchers.getQuota());
        voucherRepository.save(vouchers);

    }
}
