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
    /*
    public SaveItemCategory getSaveItemCategory() {
        return saveItemCategory;
    }

     */





}
