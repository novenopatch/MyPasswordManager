package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import jin.jerrykel.mypasswordmanager.R;

public class ShowAddNoteDialog {
    private Dialog dialog;

    private Button buttonSaveNote;
    private EditText EditTextNoteTitle;
    private EditText EditTextNoteUserName;

    private EditText EditTextPassword;
    private EditText EditTextHomepage;
    private EditText EdiTextNoteCommentaire;

    public Dialog getDialog() {
        return dialog;
    }

    public ShowAddNoteDialog(Context context) {
        this.dialog = new Dialog(context, R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_alert_dialogue_add_new_note);


        this.buttonSaveNote = (Button) dialog.findViewById(R.id.buttonSaveNote);

        this.EditTextNoteTitle = (EditText)dialog.findViewById(R.id.EditTextNoteTitle);
        this.EditTextNoteUserName = (EditText)dialog.findViewById(R.id.EditTextNoteUserName);
        this.EditTextPassword = (EditText)dialog.findViewById(R.id.EditTextPassword);
        this.EditTextHomepage = (EditText)dialog.findViewById(R.id.EditTextHomepage);
        this.EdiTextNoteCommentaire = (EditText)dialog.findViewById(R.id.EdiTextNoteCommentaire);
    }



    public Button getButtonSaveNote() {
        return buttonSaveNote;
    }

    public EditText getEditTextNoteTitle() {
        return EditTextNoteTitle;
    }

    public EditText getEditTextNoteUserName() {
        return EditTextNoteUserName;
    }

    public EditText getEditTextPassword() {
        return EditTextPassword;
    }

    public EditText getEditTextHomepage() {
        return EditTextHomepage;
    }

    public EditText getEdiTextNoteCommentaire() {
        return EdiTextNoteCommentaire;
    }



}
