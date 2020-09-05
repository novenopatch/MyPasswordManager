package jin.jerrykel.mypasswordmanager.model;

public class SaveNoteItem extends SaveItem{
    private String id;
    private String password;
    private String homePage;

    public SaveNoteItem(String title, SaveItemCategory saveItemCategory, String id, String password,String homePage, String comment) {
        super(title,comment);
        this.id = id;
        this.password = password;
        this.homePage = homePage;
        saveItemCategory.setsaveNoteItems(this);
    }

    public String getTitle() {
        return this.getName();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getHomePage() {
        return homePage;
    }


    public String getComment() {
        return this.getDecription();
    }
}
