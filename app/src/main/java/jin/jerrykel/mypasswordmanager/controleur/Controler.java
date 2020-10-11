package jin.jerrykel.mypasswordmanager.controleur;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import jin.jerrykel.mypasswordmanager.model.GenerateModel.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.GenerateModel.RandomPasswordGenerator;
import jin.jerrykel.mypasswordmanager.model.LocalAcces;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;
import jin.jerrykel.mypasswordmanager.model.UserModel.User;
import jin.jerrykel.mypasswordmanager.utils.DatabaseManagerOrmlite;
import jin.jerrykel.mypasswordmanager.utils.LocalDatabase;
import jin.jerrykel.mypasswordmanager.utils.Utils;

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
    private ArrayList<SaveNoteItem> saveNoteItemArrayList = new ArrayList<>();
    //private static LocalAcces localAcces;
    private static DatabaseManagerOrmlite databaseManagerOrmlite;

    /**
     * contructeur private
     */
    private Controler(){
        super();
    }
    /**
     * création de l'instance
     * @return Controler.instance
     */
    public static Controler getInstance(Context contexte){

        //localAcces = new LocalAcces(contexte);
        databaseManagerOrmlite = new DatabaseManagerOrmlite(contexte,"generateScoreTest.db",1);
        if(contexte == null){
            Controler.contexte = contexte;
        }
        if(Controler.instance == null){
            Controler.instance = new Controler();
        }
        return  Controler.instance;
    }
    /**
     * test if category exist
     * @param arrayListItem
     * @param name
     * @return
     */
    private boolean testIfCategoryExist( ArrayList<SaveItemCategory> arrayListItem,String name){
        for (SaveItemCategory saveItemCategory : arrayListItem){
            if(saveItemCategory.getName().equals(name))
                return true;
        }
        return false;

    }

    /**
     * test if Item exist
     * @param arrayListItem
     * @param name
     * @return
     */
    private boolean testIfItemExist( ArrayList<SaveNoteItem> arrayListItem,String name){
        for (SaveNoteItem saveItemCategory : arrayListItem){
            if(saveItemCategory.getTitle().equals(name))
                return true;
        }
        return false;

    }

    /**
     * to add new category
     * @param name
     * @param description
     */
    public void addNewSaveCategoryList(Context context,String name,String description){
        if(!testIfCategoryExist(SaveCategoryListArrayList,name)){
            SaveCategoryListArrayList.add(new SaveItemCategory(name,description));
        }else{

                Utils.makeToast("bbb",context);


        }

    }

    /**
     * add note
     * @param title
     * @param saveItemCategoryName
     * @param id
     * @param password
     * @param homePage
     * @param comment
     */
    public void addNewNote(String title, String saveItemCategoryName, String id, String password,String homePage, String comment ){
        SaveNoteItem saveNoteItem = new SaveNoteItem(title,findandreturnSaveItemCategory(saveItemCategoryName),id,password,homePage,comment);
        findandreturnSaveItemCategory(saveItemCategoryName).setSaveNoteItems(saveNoteItem);
        saveNoteItemArrayList.add(saveNoteItem);
        //localAcces.insertSaveNote(saveNoteItem);

    }


    /**
     * found save item category for his name
     * @param name
     * @return
     */
    public SaveItemCategory findandreturnSaveItemCategory(String name){
        for (SaveItemCategory saveItemCategory : SaveCategoryListArrayList){
            if(saveItemCategory.getName().equals(name))
                return saveItemCategory;
        }
        return null;
    }

    /**
     * find and return Item Category positon
     * @param name
     * @return
     */
    public Integer findandreturnpositon(String name){
        int count = 0;
        for (SaveItemCategory saveItemCategory : SaveCategoryListArrayList){
            count++;
            if(saveItemCategory.getName().equals(name))
                return count;
        }
        return null;
    }

    /**
     * return Save Category Name ArrayList<CharSequence>
     * @return
     */
    public ArrayList<CharSequence> returnSaveItemCategoryName(){
        ArrayList<CharSequence> quit = new ArrayList<>();
        for (SaveItemCategory saveItemCategory : SaveCategoryListArrayList){
                quit.add(saveItemCategory.getName());
        }
        return quit;
    }

    /**
     * return password generate arrayList
     * @return
     */
    public ArrayList<GeneratePassword> getGeneratePasswordArrayList() {
        return generatePasswordArrayList;
    }



    /**
     * return ArrayList SaveItemCategory
     * @return
     */
    public ArrayList<SaveItemCategory> getSaveCategoryListArrayList() {
        return  SaveCategoryListArrayList;
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
        addGeneratepassword(password);
        return password;
    }

    //change la longeur
    public String getPassword(int passwordLength){
        String password = passGen.generatePassayPassword(passwordLength);
        addGeneratepassword(password);
        return password;
    }
    //touf sauf upercase et lower
    public String getPassword(boolean lowerCase,boolean upperCase, boolean numberCase,boolean specialCharsCase,int passwordLength){
        String password = passGen.generatePassayPassword(lowerCase,upperCase,numberCase,specialCharsCase,passwordLength);
        addGeneratepassword(password);
        return password;
    }
    //pas de nombre pas de specialchars
    public String getPassword(boolean b, int parseInt) {
        String password = passGen.generatePassayPassword(b,parseInt);
        addGeneratepassword(password);
        return password;
    }
    //pas special chars
    public String getPassword(String b, int parseInt) {
        String password = passGen.generatePassayPassword(b,parseInt);
        addGeneratepassword(password);
        return password;
    }
    ///pas de nombre
    public String getPassword(int b, int parseInt) {
        String password = passGen.generatePassayPassword(b,parseInt);
        addGeneratepassword(password);
        return password;
    }
    /**
     * add new generate password
     * @param password
     */
    public void addGeneratepassword( String password){
        GeneratePassword generatePassword = new GeneratePassword(Utils.getDate(),password);
        if(generatePasswordArrayList.size()>=4){
            generatePasswordArrayList.clear();
            databaseManagerOrmlite.deleteGeneratePasswordsAll(generatePasswordArrayList);
        }
        generatePasswordArrayList.add(generatePassword);
        databaseManagerOrmlite.insertGeneratePassword(generatePassword);

    }
    public void getGeneratePasswordForDB(){
       generatePasswordArrayList = databaseManagerOrmlite.getGeneratePasswords();
       Collections.reverse(generatePasswordArrayList);
    }
    public  void deleteGeneratePassword(GeneratePassword generatePassword){
        generatePasswordArrayList.remove(generatePassword);
        databaseManagerOrmlite.deleteGeneratePasswords(generatePassword);
    }
}
