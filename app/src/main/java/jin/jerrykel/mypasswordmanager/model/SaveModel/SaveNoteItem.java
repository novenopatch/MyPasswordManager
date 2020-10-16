package jin.jerrykel.mypasswordmanager.model.SaveModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_SaveNoteItem")
public class SaveNoteItem  {

    @DatabaseField(columnName = "idSaveNoteItem", generatedId = true)
    private int idSaveNoteItem;
    @DatabaseField(canBeNull = false,unique = true)
    private String title;
    @DatabaseField
    private String id;
    @DatabaseField
    private String password;
    @DatabaseField
    private String homePage;
    @DatabaseField
    private String comment ;



    public SaveNoteItem() {
    }


    public SaveNoteItem(String title, String id, String password, String homePage, String comment) {
        this.title = title;
        this.password = password;
        this.homePage = homePage;
        this.comment = comment;


    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHomePage() {
        return homePage;
    }
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    /*
    public SaveItemCategory getSaveItemCategory() {
        return saveItemCategory;
    }

     */





}
