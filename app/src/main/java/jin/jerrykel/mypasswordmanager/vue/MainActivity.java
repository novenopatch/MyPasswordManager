package jin.jerrykel.mypasswordmanager.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.LoginControle;

public class MainActivity extends AppCompatActivity {
    private EditText editTextIdentifiant;
    private EditText  editTextPassword;
    private LoginControle loginControle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElement();
    }
    public void initElement(){
        loginControle = LoginControle.getInstance();
        editTextIdentifiant = findViewById(R.id.editTextIdentifiant);
        editTextPassword = findViewById(R.id.editTextPassword);
    }
    public void ConnectionAction(View view){
        if(!editTextIdentifiant.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty()){
            if(loginControle.connectIdTest(editTextIdentifiant.getText().toString(),editTextPassword.getText().toString()) ){
               // Intent intent = new Intent(MainActivity.this, AppBordActivity.class);
                Intent intent = new Intent(MainActivity.this, ConnectActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }


    }
}