package jin.jerrykel.mypasswordmanager.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jin.jerrykel.mypasswordmanager.R;

public abstract  class Utils {
    /**
     * conversion d'une date en chaine sous la forme yyyy-MM-dd hh:mm:ss
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd- hh:mm:ss");
        return date.format(uneDate);
    }
    public static String convertDateToString(Date uneDate, String formatAttendu){
        SimpleDateFormat date = new SimpleDateFormat(formatAttendu);
        try {
            return date.format(uneDate);
        } catch (Exception e) {
            Log.d("erreur", "parse de la date impossible"+ e.toString());
        }
        return null;

    }

    /**
     * Conversion chaine format recu en parametre vers date
     * @param unedate
     * @param formatAttendu
     * @return
     */
    public static Date convertStringToDate(String unedate, String formatAttendu){
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
     * Conversion chaine format Tue Feb 06 09:16:17 GMT 2018(EEE MMM dd hh:mm:ss 'GMT' yyyy) vers date
     * @param unedate
     * @return
     */
    public static Date convertStringToDate(String unedate){
        return  convertStringToDate(unedate,"EEE MMM dd hh:mm:ss 'GMT' yyyy");
    }

    public static String format2Decimal(Float valeur){
        return  String.format("%.01f",valeur);
    }

    public static String[] getDate(){
        Calendar calendar = Calendar.getInstance();

        int jour = calendar.get(Calendar.DAY_OF_MONTH);
        int mois = calendar.get(Calendar.MONTH)+ 1;
        int annee = calendar.get(Calendar.YEAR);

        int heure = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String dateString = jour + "/"+mois+"/"+annee;

        String zero ="";
        if(minute <10)
            zero = "0";
        String heurefinal = heure + ":"+zero+minute;


        String[] tab ={
                dateString,heurefinal
        };
        return tab;


    }

    /**
     * copy Password In clipBoard
     * @param generatePassword
     * @param contexte
     */
    public static void copyPasswordInclipBoard(String toastText, String generatePassword, Context contexte) {
        ClipboardManager clipboardManager = (ClipboardManager)contexte.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copy Text",generatePassword);
        clipboardManager.setPrimaryClip(clipData);
        makeToast(toastText,contexte);

    }
    public static void makeToast(String toastText,Context contexte){
        Toast toast = Toast.makeText(contexte, toastText, Toast.LENGTH_LONG);
        View toastView = toast.getView();
        TextView tv = (TextView) toastView.findViewById(android.R.id.message);
        tv.setTextSize(18);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_settings_blue_24,0,0,0);
        tv.setCompoundDrawablePadding(15);
        toastView.setBackgroundColor(Color.parseColor("#00000000"));


        toast.show();
    }
    /**
     * my personal getString for take String for resource to get translate foncton all over
     * @param resId
     * @param context
     * @return
     */
    @NonNull
    public  static String getString(@StringRes int resId,Context context) {
        return context.getString(resId);
    }

}
