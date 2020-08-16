package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.content.Context;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jin.jerrykel.mypasswordmanager.R;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavDrawerMenuTestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavDrawerMenuTestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View rootView ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    NavDrawerMenuTestFragment.OnFragmentInteractionListener  mListener;

    public NavDrawerMenuTestFragment() {
        // Required empty public constructor
    }



    public static NavDrawerMenuTestFragment newInstance(String param1, String param2) {
        NavDrawerMenuTestFragment fragment = new NavDrawerMenuTestFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mListener != null){
            mListener.OnFragmentInteractionChangeTitle("drawer_menu_test");
        }
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_nav_drawer_menu_test, container, false);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
          //  rootView.findViewById(R.id.toolbar);
        }else{
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener{
        void OnFragmentInteractionChangeTitle(String title);
    }

}