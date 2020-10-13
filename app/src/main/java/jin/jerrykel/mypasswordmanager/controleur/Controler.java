package jin.jerrykel.mypasswordmanager.controleur;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import jin.jerrykel.mypasswordmanager.model.GenerateModel.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;
import jin.jerrykel.mypasswordmanager.model.UserModel.User;
import jin.jerrykel.mypasswordmanager.utils.LocalDatabaseManager;
import jin.jerrykel.mypasswordmanager.utils.RandomPasswordGenerator;
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


    private ArrayList<SaveNoteItem> saveNoteItemArrayList = new ArrayList<>();

    private static LocalDatabaseManager localDatabaseManager;

    public ArrayList<SaveNoteItem> getSaveNoteItemArrayList() {
        return saveNoteItemArrayList;
    }

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
    public static Controler getInstance(Context context){



        if(contexte == null){
            Controler.contexte = context;

        }
        if(Controler.instance == null){
            Controler.instance = new Controler();
            localDatabaseManager = new LocalDatabaseManager(contexte,"generateScoreTest.db",1);


        }
        return  Controler.instance;
    }


    /**
     * test if Item exist
     * @param arrayListItem
     * @param name
     * @return
     */
    private boolean testIfItemExist( ArrayList<SaveNoteItem> arrayListItem,String name){
        if(arrayListItem!=null){
            for (SaveNoteItem saveItemCategory : arrayListItem){
            if(saveItemCategory.getTitle().equals(name))
                return true;
        }
        }

        return false;

    }



    /**
     * add note
     * @param title
     * @param id
     * @param password
     * @param homePage
     * @param comment
     */
    public void addNewNotes(Context context,String title, String id, String password,String homePage, String comment ){
        if(!testIfItemExist(saveNoteItemArrayList,title)){
            SaveNoteItem saveNoteItem = new SaveNoteItem(title,id,password,homePage,comment);
            //saveItemCategory.setSaveNoteItem(saveNoteItem);
            saveNoteItemArrayList.add(saveNoteItem);
            localDatabaseManager.callSaveNoteItem.insertSaveNoteItem(saveNoteItem);

        }else {
            Utils.makeToast("controler line 121",context);
        }

    }
    public void addNewNote(String title, String id, String password,String homePage, String comment ){
        SaveNoteItem saveNoteItem = new SaveNoteItem(title,id,password,homePage,comment);
        localDatabaseManager.callSaveNoteItem.insertSaveNoteItem(saveNoteItem);



    }

    /*
    public ArrayList<SaveNoteItem> findAndShortSaveItemByCategoryName(String name){
        ArrayList<SaveNoteItem> arrayList = new ArrayList<>();
        for (SaveNoteItem saveNoteItem : saveNoteItemArrayList){
            if(saveNoteItem.getSaveItemCategoryName().equals(name))
                arrayList.add(saveNoteItem);
        }
        return arrayList;
    }

     */




    /**
     * return password generate arrayList
     * @return
     */
    public ArrayList<GeneratePassword> getGeneratePasswordArrayList() {
        return generatePasswordArrayList;
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
            localDatabaseManager.callGeneratePassword.deleteGeneratePasswordsAll(generatePasswordArrayList);
        }
        generatePasswordArrayList.add(generatePassword);
        localDatabaseManager.callGeneratePassword.insertGeneratePassword(generatePassword);

    }
    public void getGeneratePasswordForDB(){
       generatePasswordArrayList = localDatabaseManager.callGeneratePassword.getGeneratePasswords();
       Collections.reverse(generatePasswordArrayList);
    }

    public void getSaveNoteItemForDB(){
        if(localDatabaseManager.callSaveNoteItem.getSaveNoteItem()!=null){
            saveNoteItemArrayList = localDatabaseManager.callSaveNoteItem.getSaveNoteItem();
        }


        //saveItemCategory.setSaveNoteItems(arrayList) ;

    }



    public  void deleteGeneratePassword(GeneratePassword generatePassword){
        generatePasswordArrayList.remove(generatePassword);
        localDatabaseManager.callGeneratePassword.deleteGeneratePasswords(generatePassword);
    }


    public  void deleteSaveNoteItem(int position){
        // TODO probleme
        localDatabaseManager.callSaveNoteItem.deleteSaveNoteItems(saveNoteItemArrayList.get(position));
        saveNoteItemArrayList.remove(position);
       /// saveNoteItemArrayList.remove(position);

    }

}
