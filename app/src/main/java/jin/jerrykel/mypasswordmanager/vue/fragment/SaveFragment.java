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
import androidx.recyclerview.widget.SortedList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.SaveItem;


public class SaveFragment extends Fragment implements  SearchView.OnQueryTextListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String pageTitle= "Save";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private SaveListAdapter saveListAdapter  ;
    public SaveFragment() {
        // Required empty public constructor
    }
    public static SaveFragment newInstance() {
        return new SaveFragment();
    }



    public static GenererFragment newInstance(String param1, String param2) {
        GenererFragment fragment = new GenererFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

       initView(rootView);
        return rootView;

    }

    public void initView(View rootView){
        SearchView searchView = (SearchView) rootView.findViewById(R.id.saveSearchView);
        searchView.setQueryHint("Search your penis ou your .....");
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);

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

        //saveListAdapter = new PasswordGenerateListAdapter(controle.getGeneratePasswordArrayList(),view.getContext());
        //saveListAdapter  = new SaveListAdapter();
        //recycleView.setAdapter(saveListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleView.getContext(),DividerItemDecoration.VERTICAL);
        recycleView.addItemDecoration(dividerItemDecoration);

        //Drag and drop et swipe
        ItemTouchHelper.Callback itemToucherHelperCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemToucherHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);
    }


    //retour fragment Tile
    public static String getStringTitle(){

        return pageTitle;
    }

    //implementation du searview blabla
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        saveListAdapter.filter(newText);
        return true;
    }

}