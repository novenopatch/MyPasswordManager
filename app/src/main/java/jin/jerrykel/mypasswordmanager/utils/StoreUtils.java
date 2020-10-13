package jin.jerrykel.mypasswordmanager.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by JerryKelDev on 12/10/2020
 */
public class StoreUtils {

    private static File createOrGetFile(File destination,String fileName, String folderName){
        //file chemin d' accees
        File folder = new File(destination,folderName);
        return  new File(folder,fileName);


    }
    private static String readOnFile(Context context, File file){
        String result = null;
        if(file.exists()){
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder builder = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while (line!=null){
                        builder.append(line);
                        builder.append("\n");
                        line= bufferedReader.readLine();
                    }
                    result = builder.toString();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
    public static void writeOneFile(String text, File file){

        try {
            file.getParentFile().mkdir();
            FileOutputStream fos =new FileOutputStream(file);
            Writer w = new BufferedWriter(new OutputStreamWriter(fos));
            try {
                w.write(text);
                w.flush();
                fos.getFD().sync();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                w.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String getTextFormStorage(File rooDestination,Context context,String fileName,String folderName){
        File file= createOrGetFile(rooDestination,fileName,folderName);
        return  readOnFile(context,file);
    }
    public static void setTextInStorage(File rooDestination,Context context,String fileName,String folderName, String text){
        File file = createOrGetFile(rooDestination,fileName,folderName);
        writeOneFile(text,file);

    }


    //EXternal
    public static  boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }
    public static  boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

}
