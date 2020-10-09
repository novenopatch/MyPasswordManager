package jin.jerrykel.mypasswordmanager.model.SaveModel;

import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItem;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveItemCategory;

public class SaveNoteItem extends SaveItem {
    private String id;
    private String password;
    private String homePage;



    private String  saveItemCategoryName;




    public SaveNoteItem(String title, SaveItemCategory saveItemCategory, String id, String password, String homePage, String comment) {
        super(title,comment);
        this.id = id;
        this.password = password;
        this.homePage = homePage;
        this.saveItemCategoryName = saveItemCategory.getName();
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
    public String getSaveItemCategoryName() {
        return saveItemCategoryName;
    }

}
