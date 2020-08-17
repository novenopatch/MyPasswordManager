package jin.jerrykel.mypasswordmanager.vue.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    // 2 - Default Constructor
    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }


    @Override
    public int getCount() {
        return (2); // 2- number of page to show
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return GenererFragment.newInstance();
            case 1:
                return SaveFragment.newInstance();
            default:
                return  null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return  GenererFragment.getStringTile();
            case 1:
                return SaveFragment.getStringTile();
            default:
                return null;
        }


    }


}
