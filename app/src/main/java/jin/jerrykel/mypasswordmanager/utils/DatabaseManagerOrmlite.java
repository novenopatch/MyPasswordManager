package jin.jerrykel.mypasswordmanager.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jin.jerrykel.mypasswordmanager.model.GenerateModel.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItem;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class DatabaseManagerOrmlite extends OrmLiteSqliteOpenHelper {

    public DatabaseManagerOrmlite(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GeneratePassword.class);
           // TableUtils.createTable(connectionSource, SaveItemCategory.class);
            TableUtils.createTable(connectionSource, SaveNoteItem.class);

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
    public void insertGeneratePassword(GeneratePassword generatePassword){

        try {
            generatePasswordIntegerDao().create(generatePassword);
            //this.close();
            Log.i("DATABASE","insert success");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<GeneratePassword> getGeneratePasswords(){

        try {
            ArrayList<GeneratePassword> generatePasswords = (ArrayList<GeneratePassword>) generatePasswordIntegerDao().queryForAll();
            Log.i("DATABASE","insert success");
            return  generatePasswords;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public void deleteGeneratePasswords(GeneratePassword generatePassword){
        try {

            generatePasswordIntegerDao().delete(generatePassword);
            Log.i("DATABASE","insert success");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteGeneratePasswordsAll(List<GeneratePassword> generatePasswordList){
        try {

            generatePasswordIntegerDao().delete(generatePasswordList);
            Log.i("DATABASE","insert success");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
