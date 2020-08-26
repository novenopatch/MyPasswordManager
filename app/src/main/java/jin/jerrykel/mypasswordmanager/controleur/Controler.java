package jin.jerrykel.mypasswordmanager.controleur;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import jin.jerrykel.mypasswordmanager.model.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.RandomPasswordGenerator;
import jin.jerrykel.mypasswordmanager.model.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.model.User;
import jin.jerrykel.mypasswordmanager.utils.Utils;
import jin.jerrykel.mypasswordmanager.vue.fragment.SaveListCategoryAdapter;

public class Controler {

    //intialise property
    private static Controler instance = null;
    private static Context contexte;

    //for future db
    private static  String nomFic = "saveProfil";

    //for generate password
    private RandomPasswordGenerator passGen = new RandomPasswordGenerator();

    private ArrayList<GeneratePassword> generatePasswordArrayList = new ArrayList<>();

    private ArrayList<SaveItemCategory> SaveCategoryListArrayList= new ArrayList<>();

    //contructeur private
    private Controler(){
        super();
    }


    /**
     * return password generate arrayList
     * @return
     */
    public ArrayList<GeneratePassword> getGeneratePasswordArrayList() {
        return generatePasswordArrayList;
    }

    /**
     * add new generate password
     * @param password
     */
    public void addGeneratepassword( String password){
            generatePasswordArrayList.add(new GeneratePassword(Utils.getDate(),password));
        }

    /**
     * return ArrayList SaveItemCategory
     * @return
     */
    public ArrayList<SaveItemCategory> getSaveCategoryListArrayList() {
        return  SaveCategoryListArrayList;
    }

    /**
     * to add new category
     * @param name
     * @param description
     */
    public void addNewSaveCategoryList(String name,String description){
        SaveCategoryListArrayList.add(new SaveItemCategory(name,description));
    }

    /**
     * création de l'instance
     * @return Controler.instance
     */
    public static Controler getInstance(Context contexte){


        if(contexte == null){
            Controler.contexte = contexte;
        }
        if(Controler.instance == null){
            Controler.instance = new Controler();
        }
        return  Controler.instance;
    }


    /**
     * create new user
     * @param username
     * @param password
     * @param questionSecret
     * @param reponseQuestion
     */
    public void createUser(String username,String password, int questionSecret,String reponseQuestion){
        User user = new User(username,password, questionSecret,reponseQuestion);


    }


    /**
     * test connect
     * @param user
     * @param password
     * @return
     */
    public  boolean connectIdTest(String user, String password){
       if(user.equals("jin")&& password.equals("jo")){
           return true;
       }
       return false;
    }









    /**
     * this methode test  password
     * @param password
     */
    public void testPassword(String password){
        int specialCharCount = 0;
        int nombre =0;
        int upperCase = 0;
        int lowerCase = 0;
        char[] chars = {'#','$','%','^','&','*',';','(',')','_',' ','+'};
        char[] nombrechars = {'1','2','3','4','5','6','7','8','9','0'};
        char[] upper = {'A','B','C','D','E','F','G','H','I','J','K','M','N','O','P','Q','R','S','T','V','W','X','Y','Z'};
        char[] lower = {'a','b','c','d','e','f','g','h','i','j','k','m','n','o','p','q','r','s','t','v','w','x','y','z'};
        for (char c : password.toCharArray()) {
            for (char s : chars) {
                if (c == s) {
                    specialCharCount++;
                }
            }
            for (char s : nombrechars) {
                if (c == s) {
                    nombre++;
                }
            }
            for (char s : upper) {
                if (c == s) {
                    upperCase++;
                }
            }
            for (char s : lower) {
                if (c == s) {
                    lowerCase++;
                }
            }


        }
        Log.d("1probleme", "\n____passlenght____ " + String.valueOf(password.length()) +
                "\n____specialChars_____"+String.valueOf(specialCharCount)
                +
                "\n____upperChars_____"+String.valueOf(upperCase)+
                "\n____lowerChars_____"+String.valueOf(lowerCase)
                +
                "\n____nombre_____"+String.valueOf(nombre));
    }


    /**
     * return password
     * @return
     */
    public String getPassword(){
        String password = passGen.generatePassayPassword();
        return password;
    }

    //change la longeur
    public String getPassword(int passwordLength){
        String password = passGen.generatePassayPassword(passwordLength);
        return password;
    }
    //touf sauf upercase et lower
    public String getPassword(boolean lowerCase,boolean upperCase, boolean numberCase,boolean specialCharsCase,int passwordLength){
        String password = passGen.generatePassayPassword(lowerCase,upperCase,numberCase,specialCharsCase,passwordLength);
        return password;
    }
    //pas de nombre pas de specialchars
    public String getPassword(boolean b, int parseInt) {
        String password = passGen.generatePassayPassword(b,parseInt);
        return password;
    }
    //pas special chars
    public String getPassword(String b, int parseInt) {
        String password = passGen.generatePassayPassword(b,parseInt);
        return password;
    }
    ///pas de nombre
    public String getPassword(int b, int parseInt) {
        String password = passGen.generatePassayPassword(b,parseInt);
        return password;
    }

}
