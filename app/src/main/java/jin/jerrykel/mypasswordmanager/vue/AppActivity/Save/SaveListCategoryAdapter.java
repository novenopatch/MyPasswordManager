package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.SaveItemCategory;
import jin.jerrykel.mypasswordmanager.vue.AppActivity.MainActivity;

public class SaveListCategoryAdapter extends RecyclerView.Adapter<SaveListCategoryAdapter.SaveViewHolder> {
    private ArrayList<SaveItemCategory> saveCategoryArrayList;
    private Context context;
    private SaveFragment saveFragment;


    public static class SaveViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewIconCategory;
        TextView categoryName;
        TextView categoryNameDescription;
        TextView Number;
        ImageButton categoryRate;

        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.textViewTitleCategoryName);
            categoryNameDescription = itemView.findViewById(R.id.textViewCategoryDescription);
            Number = itemView.findViewById(R.id.textViewCategorySaveNumber);
            categoryRate = itemView.findViewById(R.id.imageViewRatebarCategrory);
            imageViewIconCategory  = itemView.findViewById(R.id.imageViewIconCategory);


        }
    }


    public SaveListCategoryAdapter(ArrayList<SaveItemCategory> SaveCategoryArrayList,SaveFragment saveFragment){
        this.saveCategoryArrayList = SaveCategoryArrayList;
        this.saveFragment = saveFragment;
    }

    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_see_category_or_folder,
                parent,false);
        SaveViewHolder passwordViewHolder = new SaveViewHolder(view);
        this.context = view.getContext();
        return passwordViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        SaveItemCategory saveCategory =  saveCategoryArrayList.get(position);


        holder.categoryName.setText(saveCategory.getName());
        holder.categoryNameDescription.setText(saveCategory.getDecription());
        holder.Number.setText(String.valueOf(saveCategory.getNomberSave()));
        holder.categoryRate.setImageResource(R.drawable.ic_baseline_favorite_orange_24);
        holder.imageViewIconCategory.setBackgroundResource(R.drawable.ic_baseline_folder_orange_24);
        holder.categoryRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //demande à l'affichage de la liste de note
                saveFragment.viewNoteList(saveCategoryArrayList.get(position));


            }
        });
    }
    @Override
    public int getItemCount() {

        return saveCategoryArrayList.size();
    }





}
