package jin.jerrykel.mypasswordmanager.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import jin.jerrykel.deve.Controleur.Controle;
import jin.jerrykel.deve.Modele.Profile;
import jin.jerrykel.deve.Outils.MesOutils;
import jin.jerrykel.deve.R;
import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.model.GeneratePassword;
import jin.jerrykel.mypasswordmanager.model.PasswordGenerateControle;
import jin.jerrykel.mypasswordmanager.utils.Utils;

public class HistoListAdapter extends BaseAdapter {
    private ArrayList<GeneratePassword> lesPassword;
    private LayoutInflater inflater;
    PasswordGenerateControle controle;
    private Context context;
    public HistoListAdapter(Context contexte , ArrayList<GeneratePassword> lesProfiles){
        this.lesPassword = lesProfiles;
        this.inflater = LayoutInflater.from(contexte);
        this.controle = PasswordGenerateControle.getInstance(contexte);
        this.context =contexte;

    }
    /**
     * retourne le nombre de lignes
     * @return
     */
    @Override
    public int getCount() {
        return lesPassword.size();
    }

    /**
     * retourne l'item de la ligne actuelle
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return lesPassword.get(position);
    }

    /**
     * retourne un indice par rapport à la ligne actuelle
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * retourne la ligne (view) formaté avec gestion des évènements
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //declaration d'un Holder
        ViewHolder holder;
        //si la ligne n'existe pas
        if(convertView ==null){
            holder = new ViewHolder();
            // la ligne est contruite avec un formatage (inflater) relié à layout _liste_histo
            convertView = inflater.inflate(R.layout.layout_histo_genrate,null);
            //chaque propriété du holder est relié à une propriété graphique
            holder.textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
            holder.textViewPassword = (TextView) convertView.findViewById(R.id.textViewPassword);
            holder.btnCopy = (ImageButton)convertView.findViewById(R.id.btnCopy);
            // affecter le holder  à la vue
            convertView.setTag(holder);

        }else{
            //récupération du holder dans la ligne existante
            holder = (ViewHolder) convertView.getTag();
        }
        // valoristaion du contenu du holder (donc de la ligne)
        holder.textViewDate.setText(Utils.convertDateToString(lesPassword.get(position).getDate()));
        holder.textViewPassword.setText(lesPassword.get(position).getPassword());
        holder.btnCopy.setTag(position);
        holder.btnCopy.setOnClickListener(new  ImageButton.OnClickListener(){
            public void onClick(View v){
              int position = (int)v.getTag();
              // demande de suppression au controlleur
                controle.delProfil(lesPassword.get(position));
                // raffraichir la liste
                notifyDataSetChanged();
            }
        });
        holder.textViewDate.setTag(position);

        return convertView;
    }
    private class ViewHolder{
        ImageButton btnCopy;
        TextView textViewDate;
        TextView  textViewPassword;
    }
}
