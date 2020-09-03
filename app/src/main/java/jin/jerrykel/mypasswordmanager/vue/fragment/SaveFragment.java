package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.vue.MainActivity;


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
    private Context context;
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
        setHasOptionsMenu(true);

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
       context = rootView.getContext();
       controler = Controler.getInstance(rootView.getContext());
       controler.addNewSaveCategoryList("General","@exemple");
       initView(rootView);
        return rootView;

    }

    public void initView(View rootView){

        createListView(rootView);
        initAllFloatingBouton(rootView);
    }
    public void initAllFloatingBouton(View view){
        LinearLayout linearLayoutFloatingActionButton = view.findViewById(R.id.linearLayoutOfFloatingActionButton);
        FloatingActionButton makevisibleFloatingActionButton = view.findViewById(R.id.floatingActionButtonMain);
        FloatingActionButton AddCategoryFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddCategory);
        FloatingActionButton AddNoteFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddNote);
        linearLayoutFloatingActionButton.setVisibility(View.INVISIBLE);
        makevisibleFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( linearLayoutFloatingActionButton.getVisibility()==View.INVISIBLE){
                    linearLayoutFloatingActionButton.setVisibility(View.VISIBLE);
                }else{
                    linearLayoutFloatingActionButton.setVisibility(View.INVISIBLE);
                }

            }
        });
        AddCategoryFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCategoryDialog(context);
            }
        });
    }

    /**
     * show ADD category  alert dialogu
     * @param context
     */
    private void   showAddCategoryDialog(Context context){
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialogueaddcategory);
        Button buttonCancel = (Button)dialog.findViewById(R.id.buttonCancel);
        Button buttonSave = (Button)dialog.findViewById(R.id.buttonSave);
        EditText editextcategorieName = (EditText) dialog.findViewById(R.id.EditTextCategoryName);
        EditText editextcategorieDescription = (EditText) dialog.findViewById(R.id.EditTextCategoryDescription);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editextcategorieName.getText().toString().isEmpty()){
                    if(!editextcategorieDescription.getText().toString().isEmpty()){
                        controler.addNewSaveCategoryList( editextcategorieName.getText().toString(),editextcategorieDescription.getText().toString());
                    }else {
                        controler.addNewSaveCategoryList( editextcategorieName.getText().toString(),"");
                    }
                    saveListCategoryAdapter.notifyDataSetChanged();
                    dialog.dismiss();

                }else {

                }


            }
        });
        dialog.show();

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
               Toast.makeText(context, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();

                return true;
            case R.id.menu_activity_main_search:
                //Toast.makeText(getContext(), "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            default:


        }
        return super.onOptionsItemSelected(item);
    }

}