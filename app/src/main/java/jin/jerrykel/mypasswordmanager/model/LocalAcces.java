package jin.jerrykel.mypasswordmanager.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;
import jin.jerrykel.mypasswordmanager.utils.LocalDatabase;

public class LocalAcces {
    private static final String DATABASE_NAME = "SafePass.db";
    private static  final int DATABASE_VERSION = 1;
    private LocalDatabase localDatabase ;
    private SQLiteDatabase bd;


    public LocalAcces(Context context) {
        this.localDatabase = new LocalDatabase(context, DATABASE_NAME, DATABASE_VERSION);
    }

    public void insertSaveNote(SaveNoteItem saveNoteItem){
        bd = localDatabase.getWritableDatabase();
        String name = "";
        name.replace("'","''");
        String Sqlr= "INSERT INTO T_NoteSave(" +
                "NoteSaveTitle," +
                "NoteSaveCategoryName," +
                "NoteSaveID," +
                "NoteSavePassword," +
                "NoteSaveHomePage," +
                "NoteSaveComment) VALUES (" +
                "\""+saveNoteItem.getTitle()+ "\"," +
                "\""+saveNoteItem.getSaveItemCategoryName()+ "\"," +
                "\""+saveNoteItem.getId()+ "\"," +
                "\""+saveNoteItem.getPassword()+ "\"," +
                "\""+saveNoteItem.getHomePage()+ "\"," +
                "\""+saveNoteItem.getComment()+ "\"" +
                ");";
        bd.execSQL(Sqlr);
        bd.close();
        Log.d("dbInserteInDB","success");
    }
    // TODO
    public List<SaveNoteItem> getSaveNote(){
        bd = localDatabase.getReadableDatabase();
        List<SaveNoteItem> saveNoteItemList = new ArrayList<>();

        String stringSql = "select * from T_NoteSave";
        Cursor cursor = bd.rawQuery(stringSql,null);
        // deuxieme technique
        String[] coloneRecuperer = {
                "NoteSaveTitle",
                "NoteSaveCategoryName",
                "NoteSaveID" ,
                "NoteSavePassword",
                "NoteSaveHomePage",
                "NoteSaveComment"};
        Cursor cursor1 = bd.query("T_NoteSave",coloneRecuperer,null,null,null,null,null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast()){
            /*
            SaveNoteItem  saveNoteItem = new SaveNoteItem(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(6),
                    cursor.getString(5));


            saveNoteItemList.add(saveNoteItem);
             */

            cursor.moveToNext();
        }
        cursor.close();
        bd.close();
        return  saveNoteItemList;
    }
}
