package jin.jerrykel.mypasswordmanager.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {


    public LocalDatabase(@Nullable Context context,String DATABASE_NAME,Integer DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQLT_NoteSave = "CREATE TABLE T_NoteSave("
                     +  "     id                    INTEGER PRIMARY KEY AUTOINCREMENT,"
                     +  "     NoteSaveTitle         TEXT NOT NULL,"
                     +  "     NoteSaveeCategoryName  TEXT NOT NULL,"
                     +  "     NoteSaveID            TEXT NOT NULL,"
                     +  "     NoteSavePassword      TEXT ,"
                     +  "     NoteSaveHomePage      TEXT ,"
                     +  "     NoteSaveComment       TEXT "
                     +");";
        String strSQLT_CategorySave= "CREATE TABLE T_CategorySave("
                +  "     id                    INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  "     CategorySaveName  TEXT NOT NULL,"
                +  "     CategorySaveDecription      TEXT ,"
                +  "     CategorySaveRate      INTEGER "
                +");";

        db.execSQL(strSQLT_NoteSave);
       db.execSQL(strSQLT_CategorySave);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
