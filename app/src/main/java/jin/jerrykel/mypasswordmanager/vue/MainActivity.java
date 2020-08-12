package jin.jerrykel.mypasswordmanager.vue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.vue.generer.GenererPasswordActivity;
import jin.jerrykel.mypasswordmanager.vue.save.SaveActivity;
//couleur cool "#ddd"
/*
implementation "org.passay:passay:1.6.0"
    implementation 'androidx.cardview:cardview:1.0.0'
 */
//getString(R.string.first_textview_menu);//retourne la string sauver dans String

/*


             <color name="colorPrimaryDarka">#3700B3</color>
            <color name="colorPrimaryDarkac">#E12929</color>
            <color name="colorAccenta">#03DAC5</color>

        <item name="colorControlNormal">@color/colorAccent</item>
        <item name="colorControlActivated">@color/colorAccent</item>
        <item name="colorControlHighlight">@color/colorAccent</item>
        <item name="android:textColorHint">@color/colorAccent</item>
 */
public class MainActivity extends AppCompatActivity {
    private Button btnGenerer;
    private Button btnSauveugarder;
    private Button btnNoter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    private void init(){
        btnGenerer = findViewById(R.id.btnGenerer);
        btnSauveugarder = findViewById(R.id.btnSauveugarder);
        btnNoter = findViewById(R.id.btnNoter);
        TextView textv_title = findViewById(R.id.textv_title);
        String string = getString(R.string.first_textview_menu);//retourne la string sauver dans String
        string = "<b>" + string.substring(0,8)+"</b>" +""+ string.substring(8,string.length()) +"";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            textv_title.setText(Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY));
        }
        else {
            textv_title.setText(Html.fromHtml(string));
        }


    }
    public   void btnaction(View view){
        if (view.getId() == btnGenerer.getId()) {
            Intent intent = new Intent(MainActivity.this, GenererPasswordActivity.class);
            //Intent intent = new Intent(SaveActivity.this, ConnectActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        }
        if(view.getId() ==  btnSauveugarder.getId()){
            // c'est ici je dois faire la redirection vers ConnectActivity si aucun user n'est enregistré in db
            //par contre s'il y  a un user om passe a save SaveActivity
            Intent intent = new Intent(MainActivity.this, SaveActivity.class);
            //Intent intent = new Intent(SaveActivity.this, ConnectActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        }
        if(view.getId() ==  btnNoter.getId()){
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}