package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import jin.jerrykel.mypasswordmanager.R;

public class ShowAddCategoryDialog {
    private Dialog dialog;
    private Button buttonCancel;
    private Button buttonSave;
    private EditText editextcategorieName ;
    private EditText editextcategorieDescription;
    public ShowAddCategoryDialog(Context context) {
        this.dialog = new Dialog(context,R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_add_category);
        this.buttonCancel = (Button)dialog.findViewById(R.id.buttonCancel);
        this.buttonSave = (Button)dialog.findViewById(R.id.buttonSave);
        this.editextcategorieName = (EditText) dialog.findViewById(R.id.EditTextCategoryName);
        this.editextcategorieDescription = (EditText) dialog.findViewById(R.id.EditTextCategoryDescription);

    }
    public Dialog getDialog() {
        return dialog;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Button getButtonSave() {
        return buttonSave;
    }

    public EditText getEditextcategorieName() {
        return editextcategorieName;
    }

    public EditText getEditextcategorieDescription() {
        return editextcategorieDescription;
    }




}
