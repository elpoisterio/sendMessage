package io.elpoisterio.sendmessage.helpers;

import java.util.Calendar;
import java.util.TreeMap;

/**
 * Created by rishabh on 24/10/16.
 */

public class Utility {
    public static TreeMap<Integer, String> getMonthsFullName() {
        TreeMap<Integer, String> hashmap = new TreeMap<>();
        hashmap.put(1, "Jan");
        hashmap.put(2, "Feb");
        hashmap.put(3, "Mar");
        hashmap.put(4, "Apr");
        hashmap.put(5, "May");
        hashmap.put(6, "Jun");
        hashmap.put(7, "Jul");
        hashmap.put(8, "Aug");
        hashmap.put(9, "Sep");
        hashmap.put(10, "Oct");
        hashmap.put(11, "Nov");
        hashmap.put(12, "Dec");
        return hashmap;
    }
    private static Calendar getInstance(){
        return Calendar.getInstance();
    }

    public static String convertDate(String Date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(Date));

        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        return String.valueOf(mDay) + " " + getMonthsFullName().get(mMonth);

    }
    public static String getDateTime(String Date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(Date));
        int hr = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        return convertDate(Date) + ", " + String.valueOf(hr)+ ":" + String.valueOf(min);
    }
}
