package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;


public class SaveFragment extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    private static String pageTitle= "Save";


    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private SaveListCategoryAdapter saveListCategoryAdapter  ;
    private Controler controler;
    public SaveFragment() {
        // Required empty public constructor
    }
    public static SaveFragment newInstance() {
        return new SaveFragment();
    }



    //return color
    public static String getStringColor() {
        return "#317589";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView =  inflater.inflate(R.layout.fragment_save, container, false);
       controler = Controler.getInstance(rootView.getContext());
       controler.addNewSaveCategoryList("General","@exemple");
       initView(rootView);
        return rootView;

    }

    public void initView(View rootView){

        createListView(rootView);
    }

    /**
     * create content of RecyclerView
     * @param view
     */
    public void createListView(View view){
        recycleView = (RecyclerView)view.findViewById(R.id.saveRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        recycleView.setLayoutManager(layoutManager);


        saveListCategoryAdapter  = new SaveListCategoryAdapter(controler.getSaveCategoryListArrayList());
        recycleView.setAdapter( saveListCategoryAdapter );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleView.getContext(),DividerItemDecoration.VERTICAL);
        recycleView.addItemDecoration(dividerItemDecoration);



    }


    //retour fragment Tile
    public static String getStringTitle(){

        return pageTitle;
    }


}