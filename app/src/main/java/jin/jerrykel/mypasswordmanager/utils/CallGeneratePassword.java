package jin.jerrykel.mypasswordmanager.utils;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jin.jerrykel.mypasswordmanager.model.GenerateModel.GeneratePassword;

public class CallGeneratePassword {

        private Dao<GeneratePassword, Integer> dao;

        public CallGeneratePassword(Dao<GeneratePassword, Integer> dao) {
            this.dao = dao;
        }

        public void deleteGeneratePasswords(GeneratePassword generatePassword){
            try {

                dao.delete(generatePassword);
                Log.i("DATABASE","insert success");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void deleteGeneratePasswordsAll(List<GeneratePassword> generatePasswordList){
            try {

                dao.delete(generatePasswordList);
                Log.i("DATABASE","insert success");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void insertGeneratePassword(GeneratePassword generatePassword){

            try {
                dao.create(generatePassword);
                //this.close();
                Log.i("DATABASE","insert success");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        public ArrayList<GeneratePassword> getGeneratePasswords(){

            try {
                ArrayList<GeneratePassword> generatePasswords = (ArrayList<GeneratePassword>) dao.queryForAll();
                Log.i("DATABASE","insert success");
                return  generatePasswords;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }


        }

}
