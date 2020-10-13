package jin.jerrykel.mypasswordmanager.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import jin.jerrykel.mypasswordmanager.model.GenerateModel.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class LocalDatabaseManager extends OrmLiteSqliteOpenHelper {
    public   CallGeneratePassword callGeneratePassword;
    public  CallSaveNoteItem callSaveNoteItem;


    public LocalDatabaseManager(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
        callGeneratePassword = new CallGeneratePassword(generatePasswordIntegerDao());
        callSaveNoteItem  = new CallSaveNoteItem(saveNoteItemDao());
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GeneratePassword.class);
            TableUtils.createTable(connectionSource, SaveNoteItem.class);
            Log.i("DATABASE","excution");
        }catch (Exception e){
            Log.e("DATABASE","error");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
    public Dao<GeneratePassword,Integer> generatePasswordIntegerDao(){
        try {
            Dao<GeneratePassword, Integer> dao = getDao(GeneratePassword.class);
            return dao;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Dao<SaveNoteItem,Integer> saveNoteItemDao(){
        try {
            Dao<SaveNoteItem,Integer> dao = getDao(SaveNoteItem.class);
            return dao;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




}
