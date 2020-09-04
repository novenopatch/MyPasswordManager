package jin.jerrykel.mypasswordmanager.model;

import java.util.ArrayList;

public class SaveItemCategory {

    private  String name;
    private String decription;
    private int rate;


    private ArrayList<SaveNoteItem> saveNoteItems = new ArrayList<>();

    private int DrawableResource;

    public SaveItemCategory(String name, String decription){
        this.name = name;
        this.decription = decription;
        this.rate = 1;

    }


    public String getName() {
        return name;
    }

    public String getDecription() {
        return decription;
    }

    public int getisRate() {
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

    public ArrayList<SaveNoteItem> getSaveItemCategories() {
        return saveNoteItems;
    }

    public void setsaveNoteItems(SaveNoteItem saveNoteItem) {
        this.saveNoteItems.add(saveNoteItem);
    } public ArrayList<SaveNoteItem> getSaveNoteItems() {
        return saveNoteItems;
    }
}
