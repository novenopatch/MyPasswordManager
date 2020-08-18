package jin.jerrykel.mypasswordmanager.vue;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.vue.fragment.GenererFragment;
import jin.jerrykel.mypasswordmanager.vue.fragment.NavDrawerMenuTestFragment;
import jin.jerrykel.mypasswordmanager.vue.fragment.PageAdapter;
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
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,NavDrawerMenuTestFragment.OnFragmentInteractionListener{

// public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {
    private Button btnGenerer;
    private Button btnSauveugarder;
    private Button btnNoter;
    private Button imageViewOC;

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 6 - Configure all views

        //init();
       // 3 - Configure ViewPager
        //this.configureViewPager();




        this.configureDrawerLayout();
        this.configureToolbar();
        this.configureNavigationView();
        this.configureViewPagerAndTabs();
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);

        //// Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // 1 - Get TabLayout from layout
        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);//Mode_Fixed
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
    @SuppressLint("WrongConstant")
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
               if(!this.drawerLayout.isDrawerOpen(Gravity.START))
                   drawerLayout.openDrawer(Gravity.START);
               else drawerLayout.closeDrawer(Gravity.END);
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        //open new fragment
        Fragment fragment = null;

        switch (id){
            case R.id.activity_main_drawer_news :
               // fragment = new NavDrawerMenuTestFragment();
               // TabLayout tabLayout = findViewById(R.id.activity_main_tabs);
                //tabLayout.setVisibility(View.INVISIBLE);

                Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
                //Intent intent = new Intent(SaveActivity.this, ConnectActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
            case R.id.activity_main_drawer_profile:
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                break;
            case R.id.activity_main_drawer_settings:
                break;
            default:
                break;
        }
        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_viewpager, fragment);
            ft.commit();
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void OnFragmentInteractionChangeTitle(String title) {
        getSupportActionBar().setTitle(title);

    }




      /*
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
   */

}