package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class SaveListNoteAdapter extends RecyclerView.Adapter<SaveListNoteAdapter.SaveViewHolder>{
    private ArrayList<SaveNoteItem> saveNoteItemArrayList;
    private Context context;





    public static class SaveViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNoteIndicatif;
        TextView textViewNoteTitle;
        ImageButton categoryRatingBar;


        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNoteIndicatif = itemView.findViewById(R.id.textViewNoteIndicatif);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
            categoryRatingBar = itemView.findViewById(R.id.imageViewRatebarSaveItem);


        }
    }

    public SaveListNoteAdapter(ArrayList<SaveNoteItem> saveNoteItemArrayList) {
        this.saveNoteItemArrayList = saveNoteItemArrayList;
    }

    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_see_note_in_another_categories,
                parent,false);
        SaveListNoteAdapter.SaveViewHolder saveViewHolder = new SaveListNoteAdapter.SaveViewHolder(view);
        return saveViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        SaveNoteItem  saveNoteItem = saveNoteItemArrayList.get(position);
        if(saveNoteItem.getHomePage().isEmpty() && saveNoteItem.getHomePage().equals("")){
            holder.textViewNoteIndicatif.setText(saveNoteItem.getTitle().substring(0,2));
        }else {
            holder.textViewNoteIndicatif.setText(saveNoteItem.getHomePage().substring(0,2));
        }
        holder.textViewNoteTitle.setText(saveNoteItem.getTitle());


    }

    @Override
    public int getItemCount() {
        return saveNoteItemArrayList.size();
    }
}
