package jin.jerrykel.mypasswordmanager.controleur.AppActivity.Save.Adapter;

import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private SparseBooleanArray selectedItems;
    private static int currentSelectedIndex = -1;

    public SaveListNoteAdapter(ArrayList<SaveNoteItem> saveNoteItemArrayList,MessageAdapterListener listener) {
        this.saveNoteItemArrayList = saveNoteItemArrayList;
        this.listener = listener;
        this.selectedItems = new SparseBooleanArray();
    }
    public class SaveViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        TextView textViewNoteIndicatif;
        TextView textViewNoteTitle;
        ImageButton categoryRatingBar;

        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNoteIndicatif = itemView.findViewById(R.id.textViewNoteIndicatif);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
            categoryRatingBar = itemView.findViewById(R.id.imageViewRatebarSaveItem);

            itemView.setOnLongClickListener(this);

        }
        @Override
        public boolean onLongClick(View v) {
            listener.onRowLongClicked(getAdapterPosition());
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }



    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_see_note,
                parent,false);
        SaveListNoteAdapter.SaveViewHolder saveViewHolder = new SaveListNoteAdapter.SaveViewHolder(view);
        return saveViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        SaveNoteItem  saveNoteItem = saveNoteItemArrayList.get(position);
        if(saveNoteItem.getHomePage().isEmpty() || saveNoteItem.getHomePage().equals("")){
            holder.textViewNoteIndicatif.setText(saveNoteItem.getTitle().substring(0,1));

        }else {
            holder.textViewNoteIndicatif.setText(saveNoteItem.getHomePage().substring(0,2));
        }
        holder.textViewNoteTitle.setText(saveNoteItem.getTitle());

        // apply click events
        applyClickEvents(holder, position);


    }
    private void applyClickEvents(SaveViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClicked(position);
        });

        holder.itemView.setOnLongClickListener(view -> {
            listener.onRowLongClicked(position);
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        });
    }
    public void toggleSelection(int pos) {
        currentSelectedIndex = pos;
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
            //animationItemsIndex.delete(pos);
        } else {
            selectedItems.put(pos, true);
            //animationItemsIndex.put(pos, true);
        }
        notifyItemChanged(pos);
    }
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    @Override
    public int getItemCount() {
        return saveNoteItemArrayList.size();
    }
    public void clearSelections() {
        //reverseAllAnimations = true;
        selectedItems.clear();
        notifyDataSetChanged();
    }
    public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
    public void removeData(int position) {
        saveNoteItemArrayList.remove(position);
        resetCurrentIndex();
    }
    private void resetCurrentIndex() {
        currentSelectedIndex = -1;
    }
    public interface MessageAdapterListener {
        //void onIconClicked(int position);

        //void onIconImportantClicked(int position);

       // void onMessageRowClicked(int position);
        void onItemClicked(int position);

        void onRowLongClicked(int position);
    }
}
