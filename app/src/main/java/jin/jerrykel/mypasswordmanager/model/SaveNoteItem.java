package jin.jerrykel.mypasswordmanager.model;

public class SaveNoteItem {
    private String title;
    private String id;
    private String password;
    private String homePage;
    private String comment;

    public SaveNoteItem(String title, SaveItemCategory saveItemCategory, String id, String password,String homePage, String comment) {
        this.title = title;
        this.id = id;
        this.password = password;
        this.homePage = homePage;
        this.comment = comment;
        saveItemCategory.setsaveNoteItems(this);
    }

    public String getTitle() {
        return title;
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
        return comment;
    }
}
