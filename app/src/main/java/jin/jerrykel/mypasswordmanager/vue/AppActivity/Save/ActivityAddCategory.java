package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.vue.AppActivity.MainActivity;

public class ActivityAddCategory extends AppCompatActivity {

    private Button buttonCancel;
    private Button buttonSave;
    private EditText editextcategorieName ;
    private EditText editextcategorieDescription;
    private Controler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        this.controler = Controler.getInstance(this);
        initView();



    }
    public void initView(){
        this.buttonCancel = findViewById(R.id.buttonCancel);
        this.buttonSave = findViewById(R.id.buttonSave);
        this.editextcategorieName = findViewById(R.id.EditTextCategoryName);
        this.editextcategorieDescription = findViewById(R.id.EditTextCategoryDescription);
        initOnClickListener();


    }
    public void initOnClickListener(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editextcategorieName.getText().toString().isEmpty()){
                    if(!editextcategorieDescription.getText().toString().isEmpty()){
                        controler.addNewSaveCategoryList( ActivityAddCategory.this,editextcategorieName.getText().toString(),editextcategorieDescription.getText().toString());
                    }else {
                        controler.addNewSaveCategoryList( ActivityAddCategory.this,editextcategorieName.getText().toString(),"");
                    }
                    //saveListCategoryAdapter.notifyDataSetChanged();
                    backToMainActivity();

                    //makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
                    //dialog.dismiss();


                }



            }
            });
            //dialog.show();
            //makevisibleFloatingActionButton.setVisibility(View.VISIBLE);

            buttonCancel.setOnClickListener(v -> {
               backToMainActivity();




            });
    }
    public void backToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}