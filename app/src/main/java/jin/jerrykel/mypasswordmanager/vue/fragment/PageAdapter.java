package jin.jerrykel.mypasswordmanager.vue.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import jin.jerrykel.mypasswordmanager.vue.fragment.GenererFragment;

public class PageAdapter extends FragmentPagerAdapter {


    // 2 - Default Constructor
    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return (2); // 2- number of page to show
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "1Fil d'a";
            case 1:
                return "2";
            default:
                return null;
        }


    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return GenererFragment.newInstance();
            case 1:
                return GenererFragment.newInstance();
            default:
                return  null;
        }
    }
}
