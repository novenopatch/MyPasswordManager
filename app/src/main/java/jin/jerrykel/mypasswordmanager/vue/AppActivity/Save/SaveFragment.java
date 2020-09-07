package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.model.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.utils.Utils;
import jin.jerrykel.mypasswordmanager.vue.AppActivity.MainActivity;


public class SaveFragment extends Fragment  {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    private static String pageTitle= "Save";


    private View rootView;



    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;



    private SaveListCategoryAdapter saveListCategoryAdapter;
    private SaveListNoteAdapter saveListNoteAdapter;
    private TextView textViewCategoryName;
    private Controler controler;
    private Context context;
    private ImageView imageViewIfHome;


    public SaveFragment() {
        // Required empty public constructor
    }
    public static SaveFragment newInstance() {
        return new SaveFragment();
    }

    public SaveListCategoryAdapter getSaveListCategoryAdapter() {
        return saveListCategoryAdapter;
    }
    public RecyclerView getRecycleView() {
        return recycleView;
    }

    //return color
    public static String getStringColor() {
        return "#317589";
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        controler = Controler.getInstance(context);
        //default
        controler.addNewSaveCategoryList("General","@exemple");
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
       rootView =  inflater.inflate(R.layout.fragment_save, container, false);

       initView(rootView);
        return rootView;

    }

    public void initView(View rootView){
        textViewCategoryName = rootView.findViewById(R.id.textViewCategoryName);
        imageViewIfHome = rootView.findViewById(R.id.imageViewIfHome);
        imageButtonGoHome =rootView.findViewById(R.id.imageButtonGoHome);
        createListCategoriesView(rootView);
        initAllFloatingBouton(rootView);
    }
    private  ImageButton   imageButtonGoHome;
    private  LinearLayout linearLayoutFloatingActionButton;
    private FloatingActionButton makevisibleFloatingActionButton;

    public void makeInvisibleFloatingButton(){
        linearLayoutFloatingActionButton.setVisibility(View.INVISIBLE);
        makevisibleFloatingActionButton.setVisibility(View.INVISIBLE);
        makevisibleFloatingActionButton.setImageResource(R.drawable.ic_baseline_add_white_24);

    }
    /**
     * init All FloatingBouton
     * @param view
     */
    public void initAllFloatingBouton(View view){
        imageButtonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(recycleView==null) && !(recycleView.getAdapter()==saveListCategoryAdapter)){
                        createListCategoriesView(view);
                }

            }
        });
        linearLayoutFloatingActionButton = view.findViewById(R.id.linearLayoutOfFloatingActionButton);
        makevisibleFloatingActionButton = view.findViewById(R.id.floatingActionButtonMain);
        FloatingActionButton AddCategoryFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddCategory);
        FloatingActionButton AddNoteFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddNote);
        linearLayoutFloatingActionButton.setVisibility(View.INVISIBLE);
        makevisibleFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( linearLayoutFloatingActionButton.getVisibility()==View.INVISIBLE){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        makevisibleFloatingActionButton.setImageResource(R.drawable.ic_baseline_close_white_24);
                    }
                    else {
                        makevisibleFloatingActionButton.setImageResource(R.drawable.ic_baseline_close_black_24);
                    }
                    linearLayoutFloatingActionButton.setVisibility(View.VISIBLE);
                }else{
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        makevisibleFloatingActionButton.setImageResource(R.drawable.ic_baseline_add_white_24);
                    }
                    else {
                        makevisibleFloatingActionButton.setImageResource(R.drawable.ic_baseline_add_black_24);
                    }
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
        AddNoteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNoteDialog(context);
            }
        });
    }

    /**
     * show ADD category  alert dialogu
     * @param context
     */
    private void   showAddCategoryDialog(Context context){
        makeInvisibleFloatingButton();
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_alert_dialogue_add_new_category);
        Button buttonCancel = (Button)dialog.findViewById(R.id.buttonCancel);
        Button buttonSave = (Button)dialog.findViewById(R.id.buttonSave);
        EditText editextcategorieName = (EditText) dialog.findViewById(R.id.EditTextCategoryName);
        EditText editextcategorieDescription = (EditText) dialog.findViewById(R.id.EditTextCategoryDescription);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
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

                    makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
                    dialog.dismiss();


                }else {

                }



            }
        });
        dialog.show();
        makevisibleFloatingActionButton.setVisibility(View.VISIBLE);

    }

    /**
     * show add not Dialog alerte
     * @param context
     */
    private void   showAddNoteDialog(Context context){
        makeInvisibleFloatingButton();
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_DayNight_DialogWhenLarge);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_alert_dialogue_add_new_note);

        Spinner spinnerSelectCategory = (Spinner) dialog.findViewById(R.id.spinnerSelectCategory);
        ImageButton imageButtonAddNewCategory = (ImageButton) dialog.findViewById(R.id.imageButtonAddNewCategory);
        Button buttonSaveNote = (Button) dialog.findViewById(R.id.buttonSaveNote);

        EditText EditTextNoteTitle = (EditText)dialog.findViewById(R.id.EditTextNoteTitle);
        EditText EditTextNoteUserName = (EditText)dialog.findViewById(R.id.EditTextNoteUserName);
        EditText EditTextPassword = (EditText)dialog.findViewById(R.id.EditTextPassword);
        EditText EditTextHomepage = (EditText)dialog.findViewById(R.id.EditTextHomepage);
        EditText EdiTextNoteCommentaire = (EditText)dialog.findViewById(R.id.EdiTextNoteCommentaire);
        EditText[] editTexts = {
                EditTextNoteTitle,EditTextNoteUserName,
                EditTextPassword,EditTextHomepage,EdiTextNoteCommentaire};
        List<CharSequence> charSequenceList = controler.returnSaveItemCategoryName();
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(context,android.R.layout.simple_spinner_item,charSequenceList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectCategory.setAdapter(arrayAdapter);

        imageButtonAddNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCategoryDialog(context);
            }
        });
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(
                        !EditTextNoteTitle.getText().toString().isEmpty()
                        &!EditTextNoteUserName.getText().toString().isEmpty()
                        &!EditTextPassword.getText().toString().isEmpty()
                        &!EditTextHomepage.getText().toString().isEmpty()
                        &!EdiTextNoteCommentaire.getText().toString().isEmpty()
                    ){
                        controler.addNewNote(
                                EditTextNoteTitle.getText().toString(),
                                spinnerSelectCategory.getSelectedItem().toString(),
                                EditTextNoteUserName.getText().toString(),
                                EditTextPassword.getText().toString(),
                                EditTextHomepage.getText().toString(),
                                EdiTextNoteCommentaire.getText().toString()
                        );
                        if(!(saveListNoteAdapter ==null)){
                            saveListCategoryAdapter.notifyDataSetChanged();
                        }
                        saveListCategoryAdapter.notifyDataSetChanged();
                        saveListCategoryAdapter.notifyItemChanged(controler.findandreturnpositon(spinnerSelectCategory.getSelectedItem().toString()));

                        Utils.makeToast("Success",context);
                        makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
                        dialog.dismiss();

                    }
                    if(
                            !EditTextNoteTitle.getText().toString().isEmpty()
                            &!EditTextNoteUserName.getText().toString().isEmpty()
                            &!EditTextPassword.getText().toString().isEmpty()
                    ){
                        controler.addNewNote(
                                EditTextNoteTitle.getText().toString(),
                                spinnerSelectCategory.getSelectedItem().toString(),
                                EditTextNoteUserName.getText().toString(),
                                EditTextPassword.getText().toString(),
                                "",
                                ""
                        );
                        if(!(saveListNoteAdapter ==null)){
                            saveListCategoryAdapter.notifyDataSetChanged();
                        }
                        saveListCategoryAdapter.notifyDataSetChanged();
                        saveListCategoryAdapter.notifyItemChanged(controler.findandreturnpositon(spinnerSelectCategory.getSelectedItem().toString()));
                        //on changera ca un peu apres
                        Utils.makeToast("Success",context);
                        makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                    else {

                    }
            }
        });


        makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
        dialog.show();

    }
    public RecyclerView  initRecycleView(View view){
        if(recycleView==null){
            recycleView = (RecyclerView)view.findViewById(R.id.saveRecyclerView);
        }
        return recycleView;

    }
    public RecyclerView  initRecycleView(){
        return recycleView;

    }
    /**
     * create content of RecyclerView defaut content is categories
     * @param view
     */
    public void createListCategoriesView(View view){
        imageViewIfHome.setVisibility(View.VISIBLE);
        imageButtonGoHome.setVisibility(View.INVISIBLE);
        textViewCategoryName.setText(Utils.getString(R.string.AllCategory,view.getContext()));
        RecyclerView recycleView = initRecycleView(view);
        layoutManager = new LinearLayoutManager(view.getContext());
        recycleView.setLayoutManager(layoutManager);

        saveListCategoryAdapter  = new SaveListCategoryAdapter(controler.getSaveCategoryListArrayList(),this);
        recycleView.setAdapter( saveListCategoryAdapter );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleView.getContext(),DividerItemDecoration.VERTICAL);
        recycleView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper.Callback itemToucherHelperCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START;
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Collections.swap(controler.getSaveCategoryListArrayList(), viewHolder.getAdapterPosition(),target.getAdapterPosition());
                saveListCategoryAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                controler.getSaveCategoryListArrayList().remove(position);
                saveListCategoryAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemToucherHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);



    }

    /**
     *
     * @param saveItemCategory
     */
    public void viewNoteList(SaveItemCategory saveItemCategory) {
        imageViewIfHome.setVisibility(View.INVISIBLE);
        imageButtonGoHome.setVisibility(View.VISIBLE);
        textViewCategoryName.setText(saveItemCategory.getName());
        RecyclerView recycleView = initRecycleView();
        layoutManager = new LinearLayoutManager(recycleView.getContext());
        recycleView.setLayoutManager(layoutManager);

        saveListNoteAdapter  = new SaveListNoteAdapter(saveItemCategory.getSaveItemCategories());
        recycleView.setAdapter( saveListNoteAdapter );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycleView.getContext(),DividerItemDecoration.VERTICAL);
        recycleView.addItemDecoration(dividerItemDecoration);


        ItemTouchHelper.Callback itemToucherHelperCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START;
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Collections.swap(saveItemCategory.getSaveItemCategories(), viewHolder.getAdapterPosition(),target.getAdapterPosition());
                saveListNoteAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                saveItemCategory.getSaveItemCategories().remove(position);
                saveListNoteAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemToucherHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);
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
               Utils.makeToast("Il n'y a rien à paramétrer ici, passez votre chemin...",context);

                return true;
            case R.id.menu_activity_main_search:
                Utils.makeToast("Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.",context);

                return true;
            default:


        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }


}