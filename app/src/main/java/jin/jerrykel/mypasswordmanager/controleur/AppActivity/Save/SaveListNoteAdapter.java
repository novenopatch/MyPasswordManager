package jin.jerrykel.mypasswordmanager.controleur.AppActivity.Save;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.SaveModel.SaveNoteItem;

public class SaveListNoteAdapter extends RecyclerView.Adapter<SaveListNoteAdapter.SaveViewHolder> {
    private ArrayList<SaveNoteItem> saveNoteItemArrayList;
    private MessageAdapterListener listener;
    private Context contexte;
    private boolean actionMode = false;
    private List<SaveNoteItem> actionModeList = new ArrayList<>();



    public SaveListNoteAdapter(ArrayList<SaveNoteItem> saveNoteItemArrayList, MessageAdapterListener listener, Context context) {
        this.saveNoteItemArrayList = saveNoteItemArrayList;
        this.listener = listener;
        this.contexte = context;


    }
    public class SaveViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        TextView textViewNoteIndicatif;
        TextView textViewNoteTitle;
        ImageButton categoryRatingBar;
        LinearLayout linearLayoutActionModeItemSelected;

        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNoteIndicatif = itemView.findViewById(R.id.textViewNoteIndicatif);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
            categoryRatingBar = itemView.findViewById(R.id.imageViewRatebarSaveItem);
            linearLayoutActionModeItemSelected  = itemView.findViewById(R.id.linearLayoutActionModeItemSelected);

            itemView.setOnLongClickListener(this);

        }
        @Override
        public boolean onLongClick(View v) {
            //listener.onRowLongClicked(getAdapterPosition());
            //actionMode = true;

            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }



    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_see_note,
                parent,false);
        return new SaveViewHolder(view);
    }

    public void setActionMode(boolean actionMode) {
        this.actionMode = actionMode;
        actionModeList = new ArrayList<>();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        SaveNoteItem  saveNoteItem = saveNoteItemArrayList.get(position);


        if(saveNoteItem.getHomePage().length()<2 || saveNoteItem.getHomePage().equals("")){
            holder.textViewNoteIndicatif.setText(saveNoteItem.getTitle().substring(0,2));

        }else {
            holder.textViewNoteIndicatif.setText(saveNoteItem.getHomePage().substring(0,2));
        }
        holder.textViewNoteTitle.setText(saveNoteItem.getTitle());
        holder.textViewNoteIndicatif.setBackground(contexte.getResources().getDrawable(saveNoteItem.getDrawableInt()));
        if(saveNoteItemArrayList.get(position).isRate()){
            holder.categoryRatingBar.setImageResource(R.drawable.ic_favorite_24dp);
        }
        if(!actionMode){
            holder.linearLayoutActionModeItemSelected.setVisibility(View.INVISIBLE);
            saveNoteItem.itemSelected = false;
        }else {
            //holder.linearLayoutActionModeItemSelected.setVisibility(View.VISIBLE);
            //saveNoteItem.itemSelected = true;
        }
        // apply click events
        holder.itemView.setOnClickListener(v -> {
                if(actionMode){
                    if (!saveNoteItem.itemSelected){
                        saveNoteItem.itemSelected = true;
                        holder.linearLayoutActionModeItemSelected.setVisibility(View.VISIBLE);
                        actionModeList.add(saveNoteItem);
                        listener.actionModeActon(true);
                    }
                    else {
                        saveNoteItem.itemSelected = false;
                        holder.linearLayoutActionModeItemSelected.setVisibility(View.INVISIBLE);
                        actionModeList.remove(saveNoteItem);
                        listener.actionModeActon(true);
                        if (actionModeList.size()==0){
                            actionMode = false;
                            listener.actionModeActon(false);//actionMode();
                            actionModeList = new ArrayList<>();
                            applyClickEvents(holder, position);
                        }
                    }
                }
                else {
                    holder.linearLayoutActionModeItemSelected.setVisibility(View.INVISIBLE);
                    applyClickEvents(holder, position);
                }

        });
        holder.itemView.setOnLongClickListener(v -> {
            if(!actionMode){
                actionMode = true;
                listener.actionModeActon(true);
                saveNoteItem.itemSelected = true;
                holder.linearLayoutActionModeItemSelected.setVisibility(View.VISIBLE);
                return true;
            }
            return false;

        });

    }
    private void applyClickEvents(SaveViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(v -> listener.onItemClicked(position));
        holder.categoryRatingBar.setOnClickListener(v -> listener.onIconCategoryRatingBarClicked(position,holder.categoryRatingBar));
        /*
        holder.itemView.setOnLongClickListener(view -> {
            listener.onRowLongClicked(position);
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        });

         */
    }



    public boolean isActionMode() {
        return actionMode;

    }

    @Override
    public int getItemCount() {
        return saveNoteItemArrayList.size();
    }

    public List<SaveNoteItem> getActionModeList() {
        return actionModeList;
    }

    public interface MessageAdapterListener {
        //void onIconClicked(int position);

        void onIconCategoryRatingBarClicked(int position,ImageButton categoryRatingBarClicked);

       // void onMessageRowClicked(int position);
        void onItemClicked(int position);

       void actionModeActon(boolean actionMode);
    }
}
