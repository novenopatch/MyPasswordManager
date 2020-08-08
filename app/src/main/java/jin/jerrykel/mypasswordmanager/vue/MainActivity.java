package jin.jerrykel.mypasswordmanager.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import jin.jerrykel.mypasswordmanager.R;

public class MainActivity extends AppCompatActivity {
    private EditText editTextIdentifiant;
    private EditText  editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElement();
    }
    public void initElement(){
        editTextIdentifiant = findViewById(R.id.editTextIdentifiant);
        editTextPassword = findViewById(R.id.editTextPassword);
    }
    public void ConnectionAction(View view){
        editTextIdentifiant.getText();
        editTextPassword.getText();

    }
}