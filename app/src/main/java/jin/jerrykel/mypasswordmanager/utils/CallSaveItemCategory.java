package jin.jerrykel.mypasswordmanager.utils;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItemCategory;

public class CallSaveItemCategory {
    private Dao<SaveItemCategory ,Integer> dao;

    public CallSaveItemCategory(Dao<SaveItemCategory, Integer> dao) {
        this.dao = dao;
    }
    public void deleteSaveItemCategorys(SaveItemCategory SaveItemCategory){
        try {

            dao.delete(SaveItemCategory);
            Log.i("DATABASE","insert success");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSaveItemCategorysAll(List<SaveItemCategory> saveItemCategoryList){
        try {

            dao.delete(saveItemCategoryList);
            Log.i("DATABASE","insert success");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<SaveItemCategory> getSaveItemCategory(){

        try {
            ArrayList<SaveItemCategory> saveItemCategoryArrayList = (ArrayList<SaveItemCategory>) dao.queryForAll();
            Log.i("DATABASE","insert success");
            return  saveItemCategoryArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public void insertSaveItemCategory(SaveItemCategory saveItemCategory){

        try {
            dao.create(saveItemCategory);
            //this.close();
            Log.i("DATABASE","insert success");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
