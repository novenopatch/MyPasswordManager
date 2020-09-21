package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.vue.AppActivity.MainActivity;

public class DialogSelectCategory {
    private Dialog dialog;
    private Button  buttonCancel;
    private Button buttonRestore;
    private RecyclerView recyclerView;
    private ImageButton imageButtonAddNewCategory;
    @SuppressLint("CutPasteId")
    public DialogSelectCategory(MainActivity mainActivity) {
        //Dialog dialog = new Dialog(mainActivity, R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        this.dialog = new Dialog(mainActivity);
        this.buttonCancel = mainActivity.findViewById(R.id.buttonRestore);
        this.buttonRestore = mainActivity.findViewById(R.id.buttonRestore);
        this.recyclerView = mainActivity.findViewById(R.id.recyclerCategory);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_select_category);

        buttonCancel.setOnClickListener(v -> dialog.dismiss());


    }



    public Dialog getDialog() {
        return dialog;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Button getButtonRestore() {
        return buttonRestore;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ImageButton getImageButtonAddNewCategory() {
        return imageButtonAddNewCategory;
    }
}
