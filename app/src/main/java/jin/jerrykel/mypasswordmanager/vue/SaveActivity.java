package jin.jerrykel.mypasswordmanager.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.LoginControle;

public class SaveActivity extends AppCompatActivity {
    private EditText editTextIdentifiant;
    private EditText  editTextPassword;
    private LoginControle loginControle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        initElement();

    }
    public void initElement(){
        loginControle = LoginControle.getInstance();
        editTextIdentifiant = findViewById(R.id.editTextIdentifiant);
        editTextPassword = findViewById(R.id.editTextPassword);
    }
    public void ConnectionAction(View view){
        Button btnconnect = findViewById(R.id.btnconnect);
        if(!editTextIdentifiant.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()){
            if(loginControle.connectIdTest(editTextIdentifiant.getText().toString(),editTextPassword.getText().toString()) ){

                Intent intent = new Intent(SaveActivity.this, AppBordActivity.class);
                //Intent intent = new Intent(SaveActivity.this, ConnectActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(500);
       // btnconnect.startAnimation(alphaAnimation);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360);
        rotateAnimation.setDuration(500);
       // btnconnect.startAnimation(rotateAnimation);

        TranslateAnimation translateAnimation = new  TranslateAnimation(0,100,0,0);
        translateAnimation.setDuration(500);
        // btnconnect.startAnimation(translateAnimation);


    }

    @Override
    protected void onPause() {
        super.onPause();
        editTextIdentifiant.setText("");
        editTextPassword.setText("");

    }

}