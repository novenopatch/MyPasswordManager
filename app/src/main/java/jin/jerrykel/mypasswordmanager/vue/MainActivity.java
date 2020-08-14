package jin.jerrykel.mypasswordmanager.vue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.vue.fragment.MainFragment;
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
//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {
    private Button btnGenerer;
    private Button btnSauveugarder;
    private Button btnNoter;
    private Button imageViewOC;


    // 1 - Declare main fragment
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2 - Configure and show home fragment
        this.configureAndShowMainFragment();


        //init();
    }
    /*
    private void init(){
        //1 - Configuring Toolbar
        this.configureToolbar();

        //2 - Serialise & confige imageView
        this.configureImageView();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    // ----
    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureImageView(){
        this.imageViewOC = (Button) this.findViewById(R.id.btnconnect);
        // Set OnClick Listener on it
        imageViewOC.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Serialise ImageView

                //Launch Detail Activity
                launchDetailActivity();
            }
        });
    }

    // ----

    private void launchDetailActivity(){
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        this.startActivity(myIntent);
    }




     */
    // --------------
    // FRAGMENTS
    // --------------

    private void configureAndShowMainFragment(){
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        if (mainFragment == null) {
            // B - Create new main fragment
            mainFragment = new MainFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_main, mainFragment).commit();
        }
    }
    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button clicked !");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}