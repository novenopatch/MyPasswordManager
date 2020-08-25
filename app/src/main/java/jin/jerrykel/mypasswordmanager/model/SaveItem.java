package jin.jerrykel.mypasswordmanager.model;

public class SaveItem {

    private  String id;
    private String password;
    public SaveItem(String id,String password){
        this.id = id;

        this.password = password;

    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
