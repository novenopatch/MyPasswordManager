package jin.jerrykel.mypasswordmanager.utils;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class CallSaveNoteItem {
    private Dao<SaveNoteItem,Integer> dao;

    public CallSaveNoteItem(Dao<SaveNoteItem, Integer> dao) {
        this.dao = dao;
    }

    public void insertSaveNoteItem(SaveNoteItem saveNoteItem){

        try {
            dao.create(saveNoteItem);
            //this.close();
            Log.i("DATABASE","insert success");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public ArrayList<SaveNoteItem> getSaveNoteItem(){

        try {
            ArrayList<SaveNoteItem> saveItemArrayList = (ArrayList<SaveNoteItem>) dao.queryForAll();
            Log.i("DATABASE","insert success");
            return  saveItemArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public void deleteSaveNoteItems(SaveNoteItem saveNoteItem){
        try {

           dao.delete(saveNoteItem);
            Log.i("DATABASE","insert success");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSaveNoteItemsAll(List<SaveNoteItem> SaveNoteItemList){
        try {

            dao.delete(SaveNoteItemList);
            Log.i("DATABASE","insert success");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
