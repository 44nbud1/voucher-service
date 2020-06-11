package com.danapprentech.debrief2.voucherservice.exception;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationImpl implements Validation
{
    private Pattern pattern;
    private Matcher matcher;

    private static final String NUMBER_ONLY                 =
            "[0-9]";
    private static final String ALPHABET                    =
            "[a-zA-Z\\s']+";

    @Override
    public boolean NumberOnlyValidator (Integer number)
    {
        pattern = Pattern.compile(NUMBER_ONLY);
        matcher = pattern.matcher(String.valueOf(number));
        return matcher.matches();
    }

    @Override
    public boolean voucherName (String voucherName)
    {
        boolean status;
        if (voucherName.length() < 3 || voucherName.length() > 20)
        {
            return status = false;
        }
        return status = true;
    }

    @Override
    public boolean aplhabetOnly(String voucher) {
        pattern = Pattern.compile(ALPHABET);
        matcher = pattern.matcher(String.valueOf(voucher));
        return matcher.matches();
    }

    @Override
    public boolean dateExp(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date currentDate = new Date();
        String bulan1 = null;

        String a = dateFormat.format(currentDate);
        System.out.println(a.length());

        for (int i = 0; i < a.length(); i++)
        {
            bulan1 = (""+a.charAt(5)+(a.charAt(6)));
//                char bulan = (a.charAt(6));
            break;
        }

        return false;
    }

}
