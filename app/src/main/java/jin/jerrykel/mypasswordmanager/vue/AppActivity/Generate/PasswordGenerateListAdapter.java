package jin.jerrykel.mypasswordmanager.vue.AppActivity.Generate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.controleur.Controler;
import jin.jerrykel.mypasswordmanager.model.GenerateModel.GeneratePassword;
import jin.jerrykel.mypasswordmanager.utils.Utils;

public class PasswordGenerateListAdapter extends
        RecyclerView.Adapter<PasswordGenerateListAdapter.PasswordViewHolder> {


    public ArrayList<GeneratePassword> generatePasswordArrayList;
    private Context context;
    private Controler controler;
    private int position;


    public static class PasswordViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView heuredate;
        TextView passwordString;
        ImageButton copyImgbtn;
        ImageButton imageButtonMenu;

        public PasswordViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textViewDate);
            heuredate = itemView.findViewById(R.id.textViewHeure);
            passwordString = itemView.findViewById(R.id.textViewPassword);
            copyImgbtn = itemView.findViewById(R.id.btnCopy);
            imageButtonMenu = itemView.findViewById(R.id.imageButtonMenu);

        }
    }
    public PasswordGenerateListAdapter(ArrayList<GeneratePassword> arrayListpassword, Context context){
        this.generatePasswordArrayList = arrayListpassword;
        this.context = context;
        controler = Controler.getInstance(context);
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
        position = position;
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

        holder.imageButtonMenu.setOnClickListener(v -> {

            makePopMenu(v.getContext(),holder.imageButtonMenu);


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


    private void makePopMenu(Context context, ImageButton imageButtonMenu){
        PopupMenu popupMenu = new PopupMenu(context,imageButtonMenu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch(item.getItemId()){
                case R.id.menu_histo_search:
                    return true;
                case R.id.menu_histo_share:
                    return true;
                case R.id.menu_histo_add_new_note:
                    return true;
                case R.id.menu_histo_delete:
                    //passwordGenerateListAdapter.generatePasswordArrayList.remove(position);
                    //passwordGenerateListAdapter.notifyItemRemoved(position);
                    generatePasswordArrayList.remove(position);
                    this.notifyItemRemoved(position);
                    return true;
                default:
                    return true;

            }

        });
        popupMenu.inflate(R.menu.menu_histo_genrate_password_popup);
        try {

                 Field field = popupMenu.getClass().getDeclaredField("mPopup");
                 field.setAccessible(true);
                 Object menuPopupHelper = field.get(popupMenu);
                 Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                 Method method = cls.getDeclaredMethod("setForceShowIcon", new Class[]{boolean.class});
                 method.setAccessible(true);
                 method.invoke(menuPopupHelper, new Object[]{true});


        }catch (Exception e){
            Log.d("popupMenu error", Objects.requireNonNull(e.getMessage()));
        }finally {
            popupMenu.show();
        }

    }

/*

 */



}
