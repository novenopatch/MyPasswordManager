package jin.jerrykel.mypasswordmanager.controleur.AppActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment> fragmentArrayList = new  ArrayList<>();
    ArrayList<String> fragmentStringtitle  = new ArrayList<>();
    ArrayList<String> fragmentStringColor  = new ArrayList<>();
    // 2 - Default Constructor
    public PageAdapter(@NonNull FragmentManager fm,int Behavior) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


    }

    public void addFragmentAndFragmentTilte(Fragment fragment, String title){
        this.fragmentArrayList.add(fragment);
        this.fragmentStringtitle.add(title);
    }
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentArrayList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentStringtitle.get(position);



    }



}
