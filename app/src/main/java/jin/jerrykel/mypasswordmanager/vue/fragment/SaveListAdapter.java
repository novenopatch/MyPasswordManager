package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.SaveItem;
import jin.jerrykel.mypasswordmanager.utils.Utils;

public class SaveListAdapter extends RecyclerView.Adapter<SaveListAdapter.SaveViewHolder> {
    private ArrayList<SaveItem> saveArrayList;
    private Context context;


    public static class SaveViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView heuredate;
        TextView passwordString;
        ImageButton copyImgbtn;

        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textViewDate);
            heuredate = itemView.findViewById(R.id.textViewHeure);
            passwordString = itemView.findViewById(R.id.textViewPassword);
            copyImgbtn = itemView.findViewById(R.id.btnCopy);

        }
    }


    public SaveListAdapter(ArrayList<SaveItem> SaveArrayList){
        this.saveArrayList = SaveArrayList;
    }



    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_histo_genrate, parent,false);
        SaveViewHolder passwordViewHolder = new SaveViewHolder(view);
        return passwordViewHolder;
    }
    @Override
    public int getItemCount() {

        return saveArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {

    }


    @NonNull
    public  final String getString(@StringRes int resId,Context context) {
        return context.getString(resId);
    }



    public void filter(String queryText){
        if(queryText.isEmpty()){

        }else{

        }
    }

}
