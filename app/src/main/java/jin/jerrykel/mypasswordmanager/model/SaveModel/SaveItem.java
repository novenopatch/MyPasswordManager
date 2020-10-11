package jin.jerrykel.mypasswordmanager.model.SaveModel;




public class SaveItem {


    private  String name;
    private String decription;



    public SaveItem(String name, String decription) {
        this.name = name;
        this.decription = decription;
    }
    public String getName() {
        return name;
    }

    public String getDecription() {
        return decription;
    }
}

