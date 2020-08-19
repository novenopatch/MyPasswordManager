package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.RandomPasswordGenerator;
import jin.jerrykel.mypasswordmanager.vue.MainActivity;


public class GenererFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;





    int defaultPasswordLength = 12;
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


    private boolean aBoolean = false;




    private   RandomPasswordGenerator passGen = new RandomPasswordGenerator();

    //bouton Listener
    private View.OnClickListener btnGenerateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View[] viewsche = {editTextSpecialchars,textViewPassLenght,editTextPasswordLength,checkBoxUpperCase,checkBoxLowerCase ,checkBoxNumber,checkBoxSpicalChars,imgBtnAdd,imgBtnRemove};
            String password = "";
            if(switch_custom_or_default.isChecked()){
                switch_custom_or_default.setText("Custom");
                for(View view : viewsche){
                    view.setVisibility(View.VISIBLE);
                    view.setEnabled(true);
                }

                updateImgButton(v);
                //changement de la longueur du password
                if(checkBoxUpperCase.isChecked() && checkBoxLowerCase.isChecked()
                        && checkBoxNumber.isChecked() && checkBoxSpicalChars.isChecked()  ) {
                    password = passGen.generatePassayPassword(Integer.parseInt( editTextPasswordLength.getText().toString() ));


                    testPassword(password);
                }


            }else{
                switch_custom_or_default.setText("Default");
                for(View view : viewsche){
                    if(v instanceof CheckBox)
                        ((CheckBox) v).setChecked(true);
                }
                editTextPasswordLength.setText(String.valueOf(12));
                for(View view : viewsche){
                    view.setVisibility(View.INVISIBLE);
                    view.setEnabled(false);

                }
                if(v.getId() == buttonGenerate.getId()){ 
                    password = passGen.generatePassayPassword();
                    
                }


            }
            updateEditTextPasswordGenerate(password);
            copyInClipboardAndNotify(password,v);

        }
    };
    public void testPassword(String password){
        int specialCharCount = 0;
        int nombre =0;
        int upperCase = 0;
        int lowerCase = 0;
        char[] chars = {'#','$','%','^','&','*',';','(',')','_',' ','+'};
        char[] nombrechars = {'1','2','3','4','5','6','7','8','9','0'};
        char[] upper = {'A','B','C','D','E','F','G','H','I','J','K','M','N','O','P','Q','R','S','T','V','W','X','Y','Z'};
        char[] lower = {'a','b','c','d','e','f','g','h','i','j','k','m','n','o','p','q','r','s','t','v','w','x','y','z'};
        for (char c : password.toCharArray()) {
            for (char s : chars) {
                if (c == s) {
                    specialCharCount++;
                }
            }
            for (char s : nombrechars) {
                if (c == s) {
                    nombre++;
                }
            }
            for (char s : upper) {
                if (c == s) {
                    upperCase++;
                }
            }
            for (char s : lower) {
                if (c == s) {
                    lowerCase++;
                }
            }


        }
        Log.d("1probleme", "\n____passlenght____ " + String.valueOf(password.length()) +
                "\n____specialChars_____"+String.valueOf(specialCharCount)
                +
                "\n____upperChars_____"+String.valueOf(upperCase)+
                "\n____lowerChars_____"+String.valueOf(lowerCase)
                +
                "\n____nombre_____"+String.valueOf(nombre));
    }
    public void updateEditTextPasswordGenerate(String password){
        editTextPasswordGenerate.setText(password);
    }
    public  void copyInClipboardAndNotify(String text, View v){
        ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copy Text",text);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(v.getContext(), "ajouter au presse-papier...", Toast.LENGTH_LONG).show();

    }
    public void updateImgButton(View v){
        if(v.getId() == imgBtnAdd.getId() || v.getId() == imgBtnRemove.getId()){
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
                default:
                    break;
            }
            editTextPasswordLength.setText(Integer.toString(defaultPasswordLength));

        }
    }

    public static GenererFragment newInstance() {
        return (new GenererFragment());
    }

    public static String getStringTile() {
        String pageTitle = "Generer";
        return pageTitle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_generer, container, false);

        //this methode intialise graphique element
        initView();
        return rootView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       this.context = context;


    }
    public GenererFragment() {
        // Required empty public constructor
    }


    public static GenererFragment newInstance(String param1, String param2) {
        GenererFragment fragment = new GenererFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
    public void initView(){
         buttonGenerate =(Button) rootView.findViewById(R.id.buttonGenerate);
         switch_custom_or_default = (Switch) rootView.findViewById(R.id.switch_custom_or_default);

         editTextPasswordGenerate = (EditText) rootView.findViewById(R.id.editTextPasswordGenerate);

         editTextSpecialchars = (EditText) rootView.findViewById(R.id.editTextText_special_chars);
         editTextPasswordLength = (EditText) rootView.findViewById(R.id.editText_pass_lenght);

         checkBoxUpperCase = (CheckBox)rootView.findViewById(R.id.checkBox_upperCase);
         checkBoxLowerCase = (CheckBox)rootView.findViewById(R.id.checkBox_lowerCase);
         checkBoxNumber  = (CheckBox)rootView.findViewById(R.id.checkBox_number);
         checkBoxSpicalChars  = (CheckBox)rootView.findViewById(R.id.checkBox_special_chars);

         imgBtnAdd = (ImageButton) rootView.findViewById(R.id.imgBtn_add);
         imgBtnRemove = (ImageButton) rootView.findViewById(R.id.imgBtn_remove);

         textViewPassLenght = (TextView) rootView.findViewById(R.id.textView_pass_lenght);



         View[] viewsBoxes = {switch_custom_or_default, buttonGenerate,imgBtnAdd,imgBtnRemove};
         for(View v : viewsBoxes){
             v.setOnClickListener(btnGenerateListener);
         }



    }
    public void clickAction(View view){

    }


    //this methode set password
    public String setPassword(){

        String password = passGen.generatePassayPassword();
        return password;
    }





}