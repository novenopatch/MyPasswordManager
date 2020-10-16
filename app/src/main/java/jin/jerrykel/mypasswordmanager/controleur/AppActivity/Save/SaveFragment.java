package jin.jerrykel.mypasswordmanager.controleur.AppActivity.Save;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.AppActivity.ActivityAddNewNote;
import jin.jerrykel.mypasswordmanager.controleur.AppActivity.MainActivity;
import jin.jerrykel.mypasswordmanager.controleur.AppActivity.Save.Adapter.SaveListNoteAdapter;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.utils.Utils;


public class SaveFragment extends Fragment implements  SaveListNoteAdapter.MessageAdapterListener {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
   private MenuInflater myInflater;


    private static String pageTitle= "Save";


    private View rootView;
    private RecyclerView.LayoutManager layoutManager;
    private SaveListNoteAdapter saveListNoteAdapter;
    private Controler controler;
    private Context context;
    private  LinearLayout linearLayoutFloatingActionButton;
    private FloatingActionButton makevisibleFloatingActionButton;




    public SaveFragment() {
        // Required empty public constructor
    }

    public static SaveFragment newInstance() {
        return new SaveFragment();
    }



    /**
     * return fragment Tile
     * @return
     */
    public static String getStringTitle(){

        return pageTitle;
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
        //default category
        //controler.addNewSaveCategoryList(context,"General","@exemple");

        controler.getSaveNoteItemForDB();
        //localDatabase = new LocalDatabase(context);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        this.myInflater = inflater;
        myInflater.inflate(R.menu.add_new_note_menu_activity_main, menu);
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
        //textViewCategoryName = rootView.findViewById(R.id.textViewCategoryName);

        viewNoteList(rootView);
        initAllFloatingBouton(rootView);

    }



    /**
     * init All FloatingBouton
     * @param view
     */
    public void initAllFloatingBouton(View view){
        linearLayoutFloatingActionButton = view.findViewById(R.id.linearLayoutOfFloatingActionButton);
        makevisibleFloatingActionButton = view.findViewById(R.id.floatingActionButtonMain);
        FloatingActionButton AddCategoryFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddCategory);
        FloatingActionButton AddNoteFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddNote);
        FloatingActionButton AddNoteTypeFloatingActionButton = view.findViewById(R.id.floatingActionButtonAddNoteType);
        linearLayoutFloatingActionButton.setVisibility(View.INVISIBLE);

        makevisibleFloatingActionButton.setOnClickListener(v -> {
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

        });
       // AddCategoryFloatingActionButton.setOnClickListener(v -> showAddCategoryDialog(context));

        //AddNoteFloatingActionButton.setOnClickListener(v -> showAddNoteDialog(context));
        AddNoteFloatingActionButton.setOnClickListener(v -> startAddNewNote());
        AddNoteTypeFloatingActionButton.setOnClickListener(v ->showAddNoteTypeDialog(
                (MainActivity) context)
        );
    }
    public void startAddNewNote(){
        Intent intent = new Intent(context, ActivityAddNewNote.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void startAddNewNoteOnGernerateFragment(String password){
        Intent intent = new Intent(context, ActivityAddNewNote.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("addNote",true);
        intent.putExtra("password",password);
        startActivity(intent);
    }

    /**
     * show add note Dialog
     * @param mainActivity
     */
    public void showAddNoteTypeDialog(MainActivity mainActivity){
        //DialogSelectCategory dialogSelectCategory = new DialogSelectCategory(mainActivity);
        // dialogSelectCategory.getDialog().show();
    }
    /*
    public void   showAddNoteDialog(Context context,String passwordText){
        showAddNoteDialog(context);
        EditTextPassword.setText(passwordText);


    }

     */


    /**
     * view Note List
     * @param
     */
    public void viewNoteList(View view) {

        saveListNoteAdapter  = new SaveListNoteAdapter(controler.getSaveNoteItemArrayList(),this);
        RecyclerView recycleView = (RecyclerView)view.findViewById(R.id.saveRecyclerView);;
        layoutManager = new LinearLayoutManager(recycleView.getContext());
        recycleView.setLayoutManager(layoutManager);

        //ArrayList<SaveNoteItem> saveNoteItems = controler.findAndShortSaveItemByCategoryName(saveItemCategory.getName());

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
                Collections.swap(controler.getSaveNoteItemArrayList(), viewHolder.getAdapterPosition(),target.getAdapterPosition());
                saveListNoteAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                controler.deleteSaveNoteItem(position);
                saveListNoteAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemToucherHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);
    }



    public void makeInvisibleFloatingButton(){
        linearLayoutFloatingActionButton.setVisibility(View.INVISIBLE);
        makevisibleFloatingActionButton.setVisibility(View.INVISIBLE);
        makevisibleFloatingActionButton.setImageResource(R.drawable.ic_baseline_add_white_24);

    }


    /**
     * show add not Dialog alerte
     * @param context
     */
    EditText EditTextPassword;
    /*
    public void   showAddNoteDialog(Context context){
        makeInvisibleFloatingButton();
        ShowAddNoteDialog showAddNoteDialog = new ShowAddNoteDialog(context);
        Dialog dialog = showAddNoteDialog.getDialog();

       // Spinner spinnerSelectCategory = showAddNoteDialog.getSpinnerSelectCategory();

        Button buttonSaveNote = showAddNoteDialog.getButtonSaveNote();

        EditText EditTextNoteTitle = showAddNoteDialog.getEditTextNoteTitle();
        EditText EditTextNoteUserName = showAddNoteDialog.getEditTextNoteUserName();
        EditTextPassword = showAddNoteDialog.getEditTextPassword();
        EditText EditTextHomepage = showAddNoteDialog.getEditTextHomepage();
        EditText EdiTextNoteCommentaire = showAddNoteDialog.getEdiTextNoteCommentaire();

        List<CharSequence> charSequenceList = controler.returnSaveItemCategoryName();
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(
                context,android.R.layout.simple_spinner_item,charSequenceList
        );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectCategory.setAdapter(arrayAdapter);




        buttonSaveNote.setOnClickListener(v -> {

                if(
                    !EditTextNoteTitle.getText().toString().isEmpty()
                    &!EditTextNoteUserName.getText().toString().isEmpty()
                    &!EditTextPassword.getText().toString().isEmpty()
                    &!EditTextHomepage.getText().toString().isEmpty()
                    &!EdiTextNoteCommentaire.getText().toString().isEmpty()
                ){
                    controler.addNewNote(
                            EditTextNoteTitle.getText().toString(),
                            EditTextNoteUserName.getText().toString(),
                            EditTextPassword.getText().toString(),
                            EditTextHomepage.getText().toString(),
                            EdiTextNoteCommentaire.getText().toString(),
                            getRandomMaterialColor("400")
                    );

                    //saveListNoteAdapter.notifyItemChanged(controler.findandreturnpositon(spinnerSelectCategory.getSelectedItem().toString()));

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
                            EditTextNoteUserName.getText().toString(),
                            EditTextPassword.getText().toString(),
                            "",
                            "",
                            getRandomMaterialColor("400")
                    );

                    // TODO
                    //on changera ca un peu apres
                    Utils.makeToast(Utils.getString(R.string.toastText,context),context);
                    makevisibleFloatingActionButton.setVisibility(View.VISIBLE);

                    dialog.dismiss();

                }
            saveListNoteAdapter.notifyDataSetChanged();

        });


        makevisibleFloatingActionButton.setVisibility(View.VISIBLE);
        dialog.show();
    }


    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }
    */

    @Override
    public void onItemClicked(int position) {
        Log.i("onItemClicked",controler.getSaveNoteItemArrayList().get(position).getTitle());


    }

    @Override
    public void onRowLongClicked(int position) {
        // long press is performed, enable action mode

    }






}