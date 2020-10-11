package jin.jerrykel.mypasswordmanager.model.SaveModel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
@DatabaseTable(tableName = "SaveItemCategory")
public class SaveItemCategory  {

    @DatabaseField(columnName = "idSaveItemCategory", generatedId = true)
    private int idSaveItemCategory;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField
    private int rate;

    private ArrayList<SaveNoteItem> saveNoteItems = new ArrayList<>();

    private int DrawableResource;




    public SaveItemCategory(){


    }

    public SaveItemCategory(String name, String decription){
        this.name = name;
        this.description = decription;
        this.rate = 1;

    }




    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public int getRate() {
        return rate;
    }
    public int getNomberSave() {
        return    saveNoteItems.size();
    }
    public int getDrawableResource() {
        return DrawableResource;
    }
    public void setDrawableResource(int drawableResource) {
        DrawableResource = drawableResource;
    }


    public void setSaveNoteItems(SaveNoteItem saveNoteItem) {
        this.saveNoteItems.add(saveNoteItem);
    } public ArrayList<SaveNoteItem> getSaveNoteItems() {
        return saveNoteItems;
    }
}
