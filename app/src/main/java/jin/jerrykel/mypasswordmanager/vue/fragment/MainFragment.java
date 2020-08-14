package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jin.jerrykel.mypasswordmanager.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment   implements View.OnClickListener {

    //2 - Declare callback
    private OnButtonClickedListener mCallback;
    /*
    Déclaration de notre Callback : En déclarant ce Callback comme variable au sein du fragment,
    nous allons pouvoir créer un lien direct avec notre activité parente.
     */


    // 1 - Declare our interface that will be implemented by any container activity
    /*
    Création de l'interface : Nous créons ici une interface dans le but d'obliger toute activité
    souhaitant communiquer avec ce fragment à implémenter la méthode onButtonClicked(View).

     */
    public interface OnButtonClickedListener {
        public void onButtonClicked(View view);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Inflate the layout of MainFragment
        View result=inflater.inflate(R.layout.fragment_main, container, false);

        //Set onClickListener to button "SHOW ME DETAILS"
        result.findViewById(R.id.fragment_main_button).setOnClickListener(this);

        return result;
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------


    /*
    Création du Callback pointant vers notre activité : Nous allons lier notre Callback avec notre
    activité parente en y souscrivant depuis le fragment enfant. Cependant, il faudra que notre activité parente (donc qui contient ce fragment) implémente l'interface OnButtonClickedListener.
     */
    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 4 - Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }
    // --------------
    // ACTIONS
    // --------------

    @Override
    public void onClick(View v) {
        // 5 - Spread the click to the parent activity
        mCallback.onButtonClicked(v);
    }





}