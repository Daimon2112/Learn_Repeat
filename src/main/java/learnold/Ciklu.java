package learnold;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ciklu {

    public static void main(String[] args) {

        Date d = new Date();
        System.out.println(d.toString());
        System.out.println(d.toInstant());

        SimpleDateFormat a = new SimpleDateFormat("mm/dd/yyyy");//можно размещать даты по разному dd/mm/yyyy or yyyy/dd/mm ....
        System.out.println(a.format(d));


//        for (int i = 0; i < ; i++) {
//
//        }
//
//        for (:
//             ) {
//
//        }
}}
