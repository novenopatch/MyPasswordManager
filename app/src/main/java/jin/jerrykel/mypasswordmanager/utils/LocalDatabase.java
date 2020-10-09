package jin.jerrykel.mypasswordmanager.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;

import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class LocalDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test";
    private static  final int DATABASE_VERSION = 1;
    public LocalDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql1 = "create table T_NoteSave("
                     +  "     id integer primary key autoincrement,"
                     +  "     SaveNoteTitle text not null,"
                     +  "     SaveNoteCategoryName text not null,"
                     +  "     SaveNoteID text not null,"
                     +  "     SaveNotePassword text ,"
                     +  "     SaveNoteHomePage text ,"
                     +  "     SaveNoteComment text "
                    // +  "     when_ integer not null"
                     +")";
        String reqSQL = "CREATE TABLE \"T_NoteSave\" (" +
                "\"id\"\tINTEGER," +
                "\"SaveNoteTitle\" TEXT NOT NULL," +
                "\"SaveNoteCategoryName\" TEXT NOT NULL," +
                "\"SaveNoteID\" TEXT NOT NULL," +
                "\"SaveNotePassword\" TEXT," +
                "\"SaveNoteHomePage\" TEXT," +
                "\"SaveNoteComment\" TEXT," +
                "PRIMARY KEY(\"id\" AUTOINCREMENT)" +
                ");";
        String strSql2 = "create table T_Category("
                +  "     id integer primary key autoincrement,"
                +  "     SaveNoteTitle text not null,"
                +  "     SaveNoteCategoryName text not null,"
                +  "     SaveNoteID text not null,"
                +  "     SaveNotePassword text ,"
                +  "     SaveNoteHomePage text ,"
                +  "     SaveNoteComment text "
                //+  "     when_ integer not null"
                +")";
        db.beginTransaction();
        db.execSQL(strSql1);
        db.execSQL(strSql2);
        db.endTransaction();
        Log.d("dbOncreate","success");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertSaveNote(SaveNoteItem saveNoteItem){
        String name = "";
        name.replace("'","''");
        String strSql = "insert into T_NoteSave(" +
                                "SaveNoteTitle," +
                                "SaveNoteCategoryName," +
                                "SaveNoteID," +
                                "SaveNotePassword," +
                                "SaveNoteHomePage," +
                                "SaveNoteComment " +
                                ") values ('"
                                + saveNoteItem.getTitle()
                                + "', '" + saveNoteItem.getSaveItemCategoryName()
                                + "', '" + saveNoteItem.getId()
                                + "', '" + saveNoteItem.getPassword()
                                + "', '" +saveNoteItem.getHomePage()
                                + "', '" +saveNoteItem.getComment()
                                //+ "', '" + new Date().getTime()
                                + "');";
        this.getWritableDatabase().execSQL(strSql);
        String Sqlr= "INSERT INTO \"T_NoteSave\"(" +
                "\"id\"," +
                "\"SaveNoteTitle\"," +
                "\"SaveNoteCategoryName\"," +
                "\"SaveNoteID\"," +
                "\"SaveNotePassword\"," +
                "\"SaveNoteHomePage\"," +
                "\"SaveNoteComment\") VALUES (" +
                "NULL," +
                "NULL,"+
                "NULL," +
                "NULL," +
                "NULL," +
                "NULL," +
                "NULL" +
                ");";
        this.getWritableDatabase().execSQL(Sqlr);
        Log.d("dbInserteInDB","success");
    }
}
