package com.danapprentech.debrief2.voucherservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Coba
{
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        Date currentDate = new Date();
        Date exp = new Date();
        exp = dateFormat.parse("2020/07/15 11:08:02");

        String currentDates = dateFormat.format(currentDate);
        System.out.println(currentDates.length());
        String expired = dateFormat.format(exp);



        Integer tahun = Integer.parseInt(""+currentDates.charAt(0)+(currentDates.charAt(1))+(currentDates.charAt(2))+(currentDates.charAt(3)));
        Integer bulan = Integer.parseInt(""+currentDates.charAt(5)+(currentDates.charAt(6)));
        Integer hari = Integer.parseInt(""+currentDates.charAt(8)+(currentDates.charAt(9)));
        Integer jam = Integer.parseInt(""+currentDates.charAt(11)+(currentDates.charAt(12)));
        Integer menit = Integer.parseInt(""+currentDates.charAt(14)+(currentDates.charAt(15)));
        Integer detik= Integer.parseInt(""+currentDates.charAt(17)+(currentDates.charAt(18)));

        Integer tahunExp = Integer.parseInt(""+expired.charAt(0)+(expired.charAt(1))+(expired.charAt(2))+(expired.charAt(3)));
        Integer bulanexp = Integer.parseInt(""+expired.charAt(5)+(expired.charAt(6)));
        Integer bulanExpired = bulanexp +1;
        Integer hariexp = Integer.parseInt(""+expired.charAt(8)+(expired.charAt(9)));
        Integer jamexp = Integer.parseInt(""+expired.charAt(11)+(expired.charAt(12)));
        Integer menitexp = Integer.parseInt(""+expired.charAt(14)+(expired.charAt(15)));
        Integer detikexp = Integer.parseInt(""+expired.charAt(17)+(expired.charAt(18)));

        System.out.println(bulan);
        System.out.println(bulanExpired);

        System.out.println(tahun);
        System.out.println(bulan);
        System.out.println(hari);
        System.out.println(jam);
        System.out.println(menit);
        System.out.println(detik);

        if (tahun >= tahunExp)
        {
            System.out.println("tahun oke");
        }else {
            System.out.println("tahun salah");
        }

        if ((bulanExpired - bulan == 0) || bulanExpired - bulan == 1)
        {
            System.out.println("bulan oke");
        }else {
            System.out.println("bulam salah");
        }

        if (hariexp <= hari)
        {
            System.out.println(hari);
            System.out.println(hariexp);
            System.out.println("hari oke");
        }else {
            System.out.println("hari salah");
        }

        if (jamexp <= jam)
        {
            System.out.println();
            System.out.println("jkam oke");
        }else {
            System.out.println("jam salah");
        }

        if (menitexp <= menit)
        {
            System.out.println("menit oke");
        }else {
            System.out.println("menit salah");
        }

        if (detikexp <= detik)
        {
            System.out.println("detik oke");
        }else {
            System.out.println("detik salah");
        }


        System.out.println(dateFormat.format(currentDate));
        // manipulate date
//        c.add(Calendar.YEAR, 1);
//        c.add(Calendar.MONTH, 1);
//        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
//        c.add(Calendar.HOUR, 1);
//        c.add(Calendar.MINUTE, 1);
//        c.add(Calendar.SECOND, 1);
//
//        String exp = new String();
//        exp = "2020-06-01 10:08:02";
//        // convert calendar to date
//        Date currentDatePlusOne = c.getTime();
//
//        if (currentDate.before(currentDatePlusOne))
//        {
//            System.out.println("setelah 1 bulan");
//        }


//        System.out.println(dateFormat.format(currentDatePlusOne));
    }
}
