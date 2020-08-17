package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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




    private View rootView ;
    private Button buttonGenerate;
    private EditText editTextPasswordGenerate;
    private static String pageTitle="Generer";
    private Context context;






    //bouton Listener
    private View.OnClickListener btnGenerateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String password = setPassword();
            editTextPasswordGenerate.setText(password);
            ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("copy Text",password);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(v.getContext(), "ajouter au presse-papier...", Toast.LENGTH_LONG).show();
        }
    };

    public static GenererFragment newInstance() {
        return (new GenererFragment());
    }

    public static String getStringTile() {
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
        editTextPasswordGenerate = (EditText) rootView.findViewById(R.id.editTextPasswordGenerate);

        buttonGenerate.setOnClickListener(btnGenerateListener);
    }


    //this methode set password
    public String setPassword(){
        RandomPasswordGenerator passGen = new RandomPasswordGenerator();
        String password = passGen.generatePassayPassword();
        return password;
    }




}