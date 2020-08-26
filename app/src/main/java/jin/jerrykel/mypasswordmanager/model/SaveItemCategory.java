package jin.jerrykel.mypasswordmanager.model;

public class SaveItemCategory {

    private  String name;
    private String decription;
    private int rate;
    private int nomberSave;




    private int DrawableResource;

    public SaveItemCategory(String name, String decription){
        this.name = name;
        this.decription = decription;
        this.nomberSave = 0;
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
        return nomberSave;
    }
    public int getDrawableResource() {
        return DrawableResource;
    }
    public void setDrawableResource(int drawableResource) {
        DrawableResource = drawableResource;
    }





}
