package jin.jerrykel.mypasswordmanager.vue.save;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.AppActivity.MainActivity;
import jin.jerrykel.mypasswordmanager.controleur.Controler;

public class SaveActivity extends AppCompatActivity {
    private EditText editTextIdentifiant;
    private EditText  editTextPassword;
    private Controler controler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        initElement();

    }
    public void initElement(){
        controler = Controler.getInstance(this);
        //editTextIdentifiant = findViewById(R.id.editTextIdentifiant);
       // editTextPassword = findViewById(R.id.editTextPassword);
    }
    public void ConnectionAction(View view){
       // Button btnconnect = findViewById(R.id.btnconnect);
        TranslateAnimation animation = new TranslateAnimation(0,100,0,0);
        animation.setDuration(200);

        view.startAnimation(animation);
        /*
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

         */
        if(!editTextIdentifiant.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()){
            if(controler.connectIdTest(editTextIdentifiant.getText().toString(),editTextPassword.getText().toString()) ){

                Intent intent = new Intent(SaveActivity.this, MainActivity.class);
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



        //Animation shake = AnimationUtils.loadAnimation(this, R.anim.shakeanimation);

        //btnconnect.setAnimation(shake);

    }

    @Override
    protected void onPause() {
        super.onPause();
        editTextIdentifiant.setText("");
        editTextPassword.setText("");

    }

}