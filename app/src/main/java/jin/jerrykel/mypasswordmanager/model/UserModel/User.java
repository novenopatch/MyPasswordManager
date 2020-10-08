package jin.jerrykel.mypasswordmanager.model.UserModel;

public class User {
    private String username ;
    private String password;
    private int questionSecretId;
    private String reponseQuestion;
    public User(String username,String password, int questionSecret,String reponseQuestion ){
        this.username = username;
        this.password = password;
        this.questionSecretId = questionSecret;
        this.reponseQuestion = reponseQuestion;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
