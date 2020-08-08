package jin.jerrykel.mypasswordmanager.controleur;

import jin.jerrykel.mypasswordmanager.model.User;

public class LoginControle {
    private static LoginControle instance = null;


    //contructeur private
    private  LoginControle(){
        super();
    }
    /**
     * création de l'instance
     * @return LoginControle.instance
     */
    public static  LoginControle getInstance(){

        if(LoginControle.instance == null){
            LoginControle.instance = new LoginControle();


        }
        return  LoginControle.instance;
    }
    public void creerProfile(String username,String password, int questionSecret,String reponseQuestion){
        User user = new User(username,password, questionSecret,reponseQuestion);


    }
}
