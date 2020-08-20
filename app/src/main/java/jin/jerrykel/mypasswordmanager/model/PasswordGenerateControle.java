package jin.jerrykel.mypasswordmanager.model;

import android.content.Context;

import org.json.JSONArray;

public class PasswordGenerateControle {
    private static PasswordGenerateControle instance = null;
    private static Context contexte;
    private PasswordGenerateControle(){
        super();
    }
    public static   PasswordGenerateControle getInstance(Context contexte){

        if(contexte != null){
            PasswordGenerateControle.contexte = contexte;
        }
        if(PasswordGenerateControle.instance == null){



        }
        return  PasswordGenerateControle.instance;

    }

    public void delProfil(GeneratePassword generatePassword) {
    }
}
