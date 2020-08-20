package jin.jerrykel.mypasswordmanager.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract  class Utils {
    /**
     * Conversion chaine format Tue Feb 06 09:16:17 GMT 2018(EEE MMM dd hh:mm:ss 'GMT' yyyy) vers date
     * @param unedate
     * @return
     */
    public static Date covertStringToDate(String unedate){
       return  covertStringToDate(unedate,"EEE MMM dd hh:mm:ss 'GMT' yyyy");
    }

    /**
     * Conversion chaine format recu en parametre vers date
     * @param unedate
     * @param formatAttendu
     * @return
     */
    public static Date covertStringToDate(String unedate, String formatAttendu){
        SimpleDateFormat formater = new SimpleDateFormat(formatAttendu);
        try {
            Date date = formater.parse(unedate);
            return date;
        } catch (ParseException e) {
            Log.d("erreur", "parse de la date impossible"+ e.toString());
        }
        return null;
    }

    /**
     * conversion d'une date en chaine sous la forme yyyy-MM-dd hh:mm:ss
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd- hh:mm:ss");
        return date.format(uneDate);
    }
    public static String format2Decimal(Float valeur){
        return  String.format("%.01f",valeur);
    }
}
