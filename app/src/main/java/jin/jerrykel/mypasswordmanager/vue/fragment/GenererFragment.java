package jin.jerrykel.mypasswordmanager.vue.fragment;

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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String pageTitle="Generer";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private View rootView ;
    private Button buttonGenerate;
    private EditText editTextPasswordGenerate;

    private View.OnClickListener btnGenerateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editTextPasswordGenerate.setText(setPassword());
            Toast.makeText(v.getContext(), "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
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
        buttonGenerate =(Button) rootView.findViewById(R.id.buttonGenerate);
        editTextPasswordGenerate = (EditText) rootView.findViewById(R.id.editTextPasswordGenerate);

        buttonGenerate.setOnClickListener(btnGenerateListener);
        return rootView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


    }
    public GenererFragment() {
        // Required empty public constructor
    }

  
    // TODO: Rename and change types and number of parameters
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
    public String setPassword(){
        RandomPasswordGenerator passGen = new RandomPasswordGenerator();
        String password = passGen.generatePassayPassword();
        return password;
    }




}