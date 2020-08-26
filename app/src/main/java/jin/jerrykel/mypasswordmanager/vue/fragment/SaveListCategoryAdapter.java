package jin.jerrykel.mypasswordmanager.vue.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.SaveItemCategory;

public class SaveListCategoryAdapter extends RecyclerView.Adapter<SaveListCategoryAdapter.SaveViewHolder> {
    private ArrayList<SaveItemCategory> saveCategoryArrayList;
    private Context context;


    public static class SaveViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewIconCategory;
        TextView categoryName;
        TextView categoryNameDescription;
        TextView Number;
        RatingBar categoryRate;

        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.textViewTitleCategoryName);
            categoryNameDescription = itemView.findViewById(R.id.textViewCategoryDescription);
            Number = itemView.findViewById(R.id.textViewCategorySaveNumber);
            categoryRate = itemView.findViewById(R.id.CategoryRatingBar);
            imageViewIconCategory  = itemView.findViewById(R.id.imageViewIconCategory);


        }
    }


    public SaveListCategoryAdapter(ArrayList<SaveItemCategory> SaveCategoryArrayList){
        this.saveCategoryArrayList = SaveCategoryArrayList;
    }

    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_or_folder,
                parent,false);
        SaveViewHolder passwordViewHolder = new SaveViewHolder(view);
        return passwordViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        SaveItemCategory saveCategory =  saveCategoryArrayList.get(position);

        holder.categoryName.setText(saveCategory.getName());
        holder.categoryNameDescription.setText(saveCategory.getDecription());
        holder.Number.setText(String.valueOf(saveCategory.getNomberSave()));
        holder.categoryRate.setRating(saveCategory.getisRate());
        holder.imageViewIconCategory.setBackgroundResource(R.drawable.ic_baseline_folder_orange_24);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {

        return saveCategoryArrayList.size();
    }





}
