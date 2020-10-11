package jin.jerrykel.mypasswordmanager.model.SaveModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_SaveNoteItem")
public class SaveNoteItem  {
    @DatabaseField(columnName = "idSaveNoteItem", generatedId = true)
    private int idSaveNoteItem;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField(canBeNull = false,foreign = true,foreignColumnName ="idSaveItemCategory" )
    private SaveItemCategory  saveItemCategory;
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


    public SaveNoteItem(String title, SaveItemCategory saveItemCategory, String id, String password, String homePage, String comment) {
        //super(title,comment);
        this.title = title;
        this.saveItemCategory = saveItemCategory;
        this.id = id;
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
    public SaveItemCategory getSaveItemCategory() {
        return saveItemCategory;
    }


    public String getSaveItemCategoryName() {
        return saveItemCategory.getName();
    }
}
