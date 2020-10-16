package jin.jerrykel.mypasswordmanager.controleur.AppActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.utils.Utils;

public class ActivityAddNewNote extends AppCompatActivity {
    private Button buttonSaveNote;
    private EditText EditTextNoteTitle;
    private EditText EditTextNoteUserName;
    private EditText EditTextPassword;
    private EditText EditTextHomepage;
    private EditText EdiTextNoteCommentaire;
    private Controler controler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        initView();
        resumeBackAddNote();


    }
    public void resumeBackAddNote(){
        boolean tag = getIntent().getBooleanExtra("addNote",false);
        if(tag){
            String tagStr = getIntent().getStringExtra("password");
            EditTextPassword.setText(tagStr);
            saveNoteAction();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_note_menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menu_activity_add_note_save:
                saveNoteAction();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void initView(){
        configureToobar();
        controler = Controler.getInstance(this);
        this.buttonSaveNote = (Button) findViewById(R.id.buttonSaveNote);

        this.EditTextNoteTitle = (EditText)findViewById(R.id.EditTextNoteTitle);
        this.EditTextNoteUserName = (EditText)findViewById(R.id.EditTextNoteUserName);
        this.EditTextPassword = (EditText)findViewById(R.id.EditTextPassword);
        this.EditTextHomepage = (EditText)findViewById(R.id.EditTextHomepage);
        this.EdiTextNoteCommentaire = (EditText)findViewById(R.id.EdiTextNoteCommentaire);
        AddNoteListener();
    }
   public void configureToobar(){
       Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       ActionBar actionBar = getSupportActionBar();
       actionBar.setDisplayHomeAsUpEnabled(true);
   }
    public void   AddNoteListener(){

        buttonSaveNote.setOnClickListener(v -> saveNoteAction());

    }
    public void saveNoteAction(){
        if(returnBoleanfirstTest() || returnBoleanSecondTest()){
            if(
                    returnBoleanfirstTest()


            ){
                controler.addNewNotes(
                        this,
                        EditTextNoteTitle.getText().toString(),
                        EditTextNoteUserName.getText().toString(),
                        EditTextPassword.getText().toString(),
                        EditTextHomepage.getText().toString(),
                        EdiTextNoteCommentaire.getText().toString()
                );

                //saveListNoteAdapter.notifyItemChanged(controler.findandreturnpositon(spinnerSelectCategory.getSelectedItem().toString()));
                getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark);

            }
            if( returnBoleanSecondTest()

            ){
                controler.addNewNotes(
                        this,
                        EditTextNoteTitle.getText().toString(),
                        EditTextNoteUserName.getText().toString(),
                        EditTextPassword.getText().toString(),
                        "",
                        ""
                );

                // TODO
                //on changera ca un peu apres

            }
            Utils.makeToast("Success",this);
            returnToMainActivity();
        }

    }
    public boolean returnBoleanSecondTest(){
        if( !EditTextNoteTitle.getText().toString().isEmpty()
                &!EditTextNoteUserName.getText().toString().isEmpty()
                &!EditTextPassword.getText().toString().isEmpty()
        ){
            return  true;
        }
        return false;
    }
    public boolean returnBoleanfirstTest(){
        if( !EditTextNoteTitle.getText().toString().isEmpty()
                &!EditTextNoteUserName.getText().toString().isEmpty()
                &!EditTextPassword.getText().toString().isEmpty()
                &!EditTextHomepage.getText().toString().isEmpty()
                &!EdiTextNoteCommentaire.getText().toString().isEmpty()
        ){
            return  true;
        }
        return false;
    }
    public void returnToMainActivity(){
        Intent intent = new Intent(ActivityAddNewNote.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("noveno",true);
        startActivity(intent);
    }

}