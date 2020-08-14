package jin.jerrykel.mypasswordmanager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.Home_Page_View);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.Home_tabs);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            super.onBackPressed();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Forum(), "Forum");
        adapter.addFragment(new MySpace(), "My Space");
        adapter.addFragment(new Community(), "Community");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_RProfile) {
            Intent intent = new Intent(Therapist_Home.this, Profile.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Financial) {
//            Intent intent=new Intent(Therapist_Home.this,Financial_detail.class);
//            startActivity(intent);

        } else if (id == R.id.nav_RReport) {
//            Intent intent=new Intent(Therapist_Home.this,Report.class);
//            startActivity(intent);
//            finish();

        } else if (id == R.id.nav_RComplaint) {
//            Intent intent=new Intent(Therapist_Home.this,My_Complaint.class);
//            startActivity(intent);
//            finish();

        } else if (id == R.id.nav_Resource) {
//            Intent intent=new Intent(Therapist_Home.this,MyResource.class);
//            startActivity(intent);
//            finish();

        } else if (id == R.id.nav_SOSFeedback) {
//            Intent intent=new Intent(Therapist_Home.this,SOSFeedback.class);
//            startActivity(intent);
//            finish();

        } else if (id == R.id.nav_ServicesProvider) {
//            Intent intent=new Intent(Therapist_Home.this,ServiceProvider.class);
//            startActivity(intent);

        } else if (id == R.id.nav_Rule) {


        } else if (id == R.id.nav_Alert) {


        } else if (id == R.id.nav_RChangePassword) {


        } else if (id == R.id.nav_RLogout) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}