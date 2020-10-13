package jin.jerrykel.mypasswordmanager.vue.AppActivity.Generate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import jin.jerrykel.mypasswordmanager.vue.AppActivity.MainActivity;

public class PasswordGenerateListAdapter extends
        RecyclerView.Adapter<PasswordGenerateListAdapter.PasswordViewHolder> {


    public ArrayList<GeneratePassword> generatePasswordArrayList;
    private Context context;
    private Controler controler;


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

        GeneratePassword generatePassword = generatePasswordArrayList.get(position);
        holder.date.setText(generatePassword.getDateString());
        holder.heuredate.setText(generatePassword.getHeuredate());
        holder.passwordString.setText(generatePassword.getPassword());
        holder.copyImgbtn.setTag(position);
        holder.copyImgbtn.setOnClickListener(v -> {
            int position1 = (int)v.getTag();
            // demande de suppression au controlleur
            //controle.delPassword(lesPassword.get(position));
            // raffraichir la liste
            // notifyDataSetChanged();
            Utils.copyPasswordInclipBoard(Utils.getString(R.string.toastText,v.getContext() ),generatePassword.getPassword(),v.getContext());
            String[] tom = Utils.getDate();
            String dategetfromCalendare = tom[0];
            String heuregetfromCalendare = tom[1];


        });

        holder.imageButtonMenu.setOnClickListener(v -> {

            makePopMenu(v.getContext(),holder.imageButtonMenu,position);


        });
        holder.itemView.setOnClickListener(v -> makePopMenu(v.getContext(),holder.imageButtonMenu,position));

        holder.date.setTag(position);
    }
    @Override
    public int getItemCount() {
        return generatePasswordArrayList.size();
    }


    private void makePopMenu(Context context, ImageButton imageButtonMenu,int position){
        PopupMenu popupMenu = new PopupMenu(context,imageButtonMenu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch(item.getItemId()){
                case R.id.menu_histo_search:
                    return true;
                case R.id.menu_histo_share:
                    Intent intent = new Intent(Intent.ACTION_SEND);// Uri.parse(generatePasswordArrayList.get(position).getPassword()));
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,generatePasswordArrayList.get(position).getPassword());
                    context.startActivity(Intent.createChooser(intent,"Share via"));
                    return true;
                case R.id.menu_histo_add_new_note:
                    MainActivity mainActivity = ((MainActivity)context);
                    mainActivity.getTabs().getTabAt(1).select();
                    mainActivity.getSaveFragment().startAddNewNoteOnGernerateFragment(generatePasswordArrayList.get(position).getPassword());
                    return true;
                case R.id.menu_histo_delete:
                    controler.deleteGeneratePassword(generatePasswordArrayList.get(position));
                   // generatePasswordArrayList.remove(position);
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
