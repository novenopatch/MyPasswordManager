package jin.jerrykel.mypasswordmanager.vue.AppActivity.Generate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Collections;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.utils.Utils;


public class GenererFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;



    int defaultPasswordLength = 12;
    LinearLayout linearleayoutCustom;
    private TextView textViewPassLenght;
    private View rootView ;
    private Button buttonGenerate;
    private EditText editTextPasswordGenerate;
    private EditText editTextPasswordLength;
    private EditText editTextSpecialchars;
    private Context context;
    private Switch switch_custom_or_default;
    private CheckBox  checkBoxUpperCase;
    private CheckBox checkBoxLowerCase;
    private CheckBox checkBoxNumber;
    private CheckBox checkBoxSpicalChars;
    private ImageButton imgBtnAdd;
    private ImageButton imgBtnRemove;
    private ImageButton superImgbtncopy;
    private SwipeRefreshLayout GenererFragmentSwipeRefreshLayout;


    private Controler controle;


    private RecyclerView recycleView;
    private RecyclerView.LayoutManager layoutManager;
    private PasswordGenerateListAdapter passwordGenerateListAdapter;


    boolean booleanCheckBoxUpperCase = true;
    boolean  booleanCheckBoxLowerCase  = true;
    boolean  booleanCheckBoxNumber  = true;
    boolean  booleanCheckBoxSpicalChars  = true;
    boolean customActived = false;


    //return color
    public static String getStringColor() {
        return  "#ff4b4c";
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_generer, container, false);
        this.context = rootView.getContext();
        //get Context
        this.controle = Controler.getInstance(rootView.getContext());
        controle.getGeneratePasswordForDB();
        //this methode intialise graphique element
        initView(rootView);
        //creerListe(rootView.getContext());
        return rootView;
    }

    /**
     * initialise all view element
     * @param view
     */

    public void initView(View view){


        buttonGenerate =(Button) view.findViewById(R.id.buttonGenerate);
        switch_custom_or_default = (Switch)  view.findViewById(R.id.switch_custom_or_default);

        editTextPasswordGenerate = (EditText)  view.findViewById(R.id.editTextPasswordGenerate);

        editTextSpecialchars = (EditText)  view.findViewById(R.id.editTextText_special_chars);
        editTextPasswordLength = (EditText)  view.findViewById(R.id.editText_pass_lenght);

        checkBoxUpperCase = (CheckBox) view.findViewById(R.id.checkBox_upperCase);
        checkBoxLowerCase = (CheckBox) view.findViewById(R.id.checkBox_lowerCase);
        checkBoxNumber  = (CheckBox) view.findViewById(R.id.checkBox_number);
        checkBoxSpicalChars  = (CheckBox) view.findViewById(R.id.checkBox_special_chars);

        imgBtnAdd = (ImageButton)  view.findViewById(R.id.imgBtn_add);
        imgBtnRemove = (ImageButton)  view.findViewById(R.id.imgBtn_remove);
        superImgbtncopy = (ImageButton)  view.findViewById(R.id.superImgbtncopy);

        textViewPassLenght = (TextView)  view.findViewById(R.id.textView_pass_lenght);

        linearleayoutCustom = (LinearLayout) view.findViewById(R.id.linearleayoutCustom);



        //add histopassword recicler
        createListView(view);
        configureSwipeRefreshLayout();

        //btn generate listener
        buttonGenerate.setOnClickListener(btnGenerateListener);
        //checkboxe tab
        CheckBox[] checkBoxes1 = {checkBoxLowerCase,checkBoxUpperCase,checkBoxNumber,checkBoxSpicalChars};
        for(CheckBox v : checkBoxes1){
            v.setOnClickListener(checkBoxesListener);
        }

        //img btn and switch tab
        View[] viewsBoxes = {switch_custom_or_default,imgBtnAdd,imgBtnRemove,superImgbtncopy,
                editTextPasswordGenerate};
        for(View v : viewsBoxes){
            v.setOnClickListener(ImgbtnListener);
        }

        linearleayoutCustom.setVisibility(View.INVISIBLE);



    }
    public void configureSwipeRefreshLayout(){
        GenererFragmentSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateListView();

            }
        });
    }
    /**
     * create content of RecyclerView
     * @param view
     */
    public void createListView(View view){

        GenererFragmentSwipeRefreshLayout = view.findViewById(R.id.GenererFragmentSwipeRefreshLayout);
        recycleView = (RecyclerView)view.findViewById(R.id.miniHistoGenerate);
        layoutManager = new LinearLayoutManager(view.getContext());
        recycleView.setLayoutManager(layoutManager);

        passwordGenerateListAdapter = new PasswordGenerateListAdapter(controle.getGeneratePasswordArrayList(),view.getContext());

        recycleView.setAdapter(passwordGenerateListAdapter);

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
                Collections.swap(passwordGenerateListAdapter.generatePasswordArrayList, viewHolder.getAdapterPosition(),target.getAdapterPosition());
                passwordGenerateListAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                //for delete in db
                controle.deleteGeneratePassword(passwordGenerateListAdapter.generatePasswordArrayList.get(position));
                //passwordGenerateListAdapter.generatePasswordArrayList.remove(position);
                passwordGenerateListAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemToucherHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);
    }
    public void updateListView(){
        GenererFragmentSwipeRefreshLayout.setRefreshing(false);
        passwordGenerateListAdapter.notifyDataSetChanged();

    }


    /**
     * checBoxesListener
     */
    private View.OnClickListener checkBoxesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!checkBoxUpperCase.isChecked())
                booleanCheckBoxUpperCase = false;
            if(!checkBoxLowerCase.isChecked())
                booleanCheckBoxLowerCase = false;
            if(!checkBoxNumber.isChecked())
                booleanCheckBoxNumber = false;
            if(!checkBoxSpicalChars.isChecked())
                booleanCheckBoxSpicalChars = false;

        }
    };

    /**
     * my btn Onclicklistern of generate fragment
     */
    private View.OnClickListener btnGenerateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String password = "";
            if(customActived){

                //si changement uniquement de la longueur du password
                if(booleanCheckBoxUpperCase && booleanCheckBoxLowerCase
                        && booleanCheckBoxNumber && booleanCheckBoxSpicalChars ) {
                    password = controle.getPassword(Integer.parseInt( editTextPasswordLength.getText().toString() ));


                }

                else if(!booleanCheckBoxUpperCase && !booleanCheckBoxLowerCase
                        && booleanCheckBoxNumber && booleanCheckBoxSpicalChars ) {
                    password = controle.getPassword(
                            true,
                            true,
                            true,
                            true,
                            Integer.parseInt( editTextPasswordLength.getText().toString() ));


                }
                //pas de 111 et {}3
                else if(booleanCheckBoxLowerCase && booleanCheckBoxLowerCase
                        && !booleanCheckBoxNumber && !booleanCheckBoxSpicalChars){
                    password = controle.getPassword(
                            false,
                            Integer.parseInt(editTextPasswordLength.getText().toString() ));
                }

                //si l'on considere booleanCheckBoxNumber booleanCheckBoxSpicalChars

                //trois trois
                else  if(booleanCheckBoxUpperCase && booleanCheckBoxLowerCase
                        && booleanCheckBoxNumber && !booleanCheckBoxSpicalChars ) {
                    password = controle.getPassword(
                            "",
                            Integer.parseInt( editTextPasswordLength.getText().toString() ));


                }
                else if(booleanCheckBoxUpperCase && booleanCheckBoxLowerCase
                        && !booleanCheckBoxNumber && booleanCheckBoxSpicalChars ) {
                    password = controle.getPassword( 1234,
                            Integer.parseInt(editTextPasswordLength.getText().toString() ));


                }
                else{
                    //make toat for user
                    Toast.makeText(v.getContext(), "Certain combinaison sont ignoré pour raison de securité...", Toast.LENGTH_LONG).show();
                    password = controle.getPassword(Integer.parseInt( editTextPasswordLength.getText().toString() ));
                }

            }else{

                    password = controle.getPassword();


            }
            updatePassword(password);

            //testPassword(password);

        }
    };
    /**
     * imgbtn and switch listerner
     */
    private View.OnClickListener ImgbtnListener = new View.OnClickListener() {

        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgBtn_add:

                    ++defaultPasswordLength;
                    break;
                case R.id.imgBtn_remove:
                    if(defaultPasswordLength>=9){
                        --defaultPasswordLength;
                    }else {

                    }
                    break;
                case R.id.superImgbtncopy:
                    if(!editTextPasswordGenerate.getText().toString().isEmpty()){
                        Utils.copyPasswordInclipBoard(getString(R.string.toastText),
                                editTextPasswordGenerate.getText().toString(),v.getContext()
                        );
                    }
                    break;
                case R.id.switch_custom_or_default:
                    if (switch_custom_or_default.isChecked()){
                        customActived= true;
                        switch_custom_or_default.setText("Custom");
                        linearleayoutCustom.setVisibility(View.VISIBLE);
                        linearleayoutCustom.setBackgroundColor(Color.parseColor("#CECECE"));
                    }
                    else {
                        customActived= false;
                        switch_custom_or_default.setText("Default");
                        linearleayoutCustom.setVisibility(View.INVISIBLE);
                        if(v instanceof CheckBox)
                            ((CheckBox) v).setChecked(true);

                        editTextPasswordLength.setText(String.valueOf(12));

                    }
                    break;
                case R.id.editTextPasswordGenerate:
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        editTextPasswordGenerate.setFocusable(View.FOCUSABLE_AUTO);
                        editTextPasswordGenerate.getFocusable();
                    }
                    else {
                        editTextPasswordGenerate.setFocusable(true);



                    }
                    editTextPasswordGenerate.hasFocusable();
                    break;

                default:
                    break;
            }
            editTextPasswordLength.setText(Integer.toString(defaultPasswordLength));
        }
    };

    /**
     * update password and add password to collection
     * @param password
     */
    public void updatePassword(String password){
        if(controle.getGeneratePasswordArrayList().size()>=4){
            controle.getGeneratePasswordArrayList().remove(0);

        }
       // controle.addGeneratepassword( password);
        //pour reverser la listr
        Collections.reverse(controle.getGeneratePasswordArrayList());
        passwordGenerateListAdapter.notifyDataSetChanged();
        editTextPasswordGenerate.setText(password);
        // copyInClipboardAndNotify(password,v);
    }






    public static GenererFragment newInstance() {
        return (new GenererFragment());
    }

    public static String getStringTitle() {
        String pageTitle = "Generer";
        return pageTitle;
    }




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
    public GenererFragment() {
        // Required empty public constructor
    }


    public static GenererFragment newInstance(String param1, String param2) {
        GenererFragment fragment = new GenererFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



}