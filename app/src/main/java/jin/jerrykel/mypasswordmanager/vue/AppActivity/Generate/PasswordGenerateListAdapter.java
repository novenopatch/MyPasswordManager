package jin.jerrykel.mypasswordmanager.vue.AppActivity.Generate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.GeneratePassword;
import jin.jerrykel.mypasswordmanager.utils.Utils;

public class PasswordGenerateListAdapter extends
        RecyclerView.Adapter<PasswordGenerateListAdapter.PasswordViewHolder> {
    public ArrayList<GeneratePassword> generatePasswordArrayList;
    private Context context;


    public static class PasswordViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView heuredate;
        TextView passwordString;
        ImageButton copyImgbtn;
        public PasswordViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textViewDate);
            heuredate = itemView.findViewById(R.id.textViewHeure);
            passwordString = itemView.findViewById(R.id.textViewPassword);
            copyImgbtn = itemView.findViewById(R.id.btnCopy);

        }
    }
    public PasswordGenerateListAdapter(ArrayList<GeneratePassword> arrayListpassword, Context context){
        this.generatePasswordArrayList = arrayListpassword;
        this.context = context;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_histo_generate_password,
                parent,false);
        PasswordViewHolder passwordViewHolder = new PasswordViewHolder(view);
        return passwordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {
        GeneratePassword generatePassword = generatePasswordArrayList.get(position);
        holder.date.setText(generatePassword.getDateString());
        holder.heuredate.setText(generatePassword.getHeuredate());
        holder.passwordString.setText(generatePassword.getPassword());
        holder.copyImgbtn.setTag(position);
        holder.copyImgbtn.setOnClickListener(new  ImageButton.OnClickListener(){
            public void onClick(View v){
                int position = (int)v.getTag();
                // demande de suppression au controlleur
                //controle.delPassword(lesPassword.get(position));
                // raffraichir la liste
                // notifyDataSetChanged();
                Utils.copyPasswordInclipBoard(Utils.getString(R.string.toastText,v.getContext() ),generatePassword.getPassword(),v.getContext());
                String[] tom = Utils.getDate();
                String dategetfromCalendare = tom[0];
                String heuregetfromCalendare = tom[1];


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.date.setTag(position);
    }
    @Override
    public int getItemCount() {
        return generatePasswordArrayList.size();
    }






}
