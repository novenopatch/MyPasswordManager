package jin.jerrykel.mypasswordmanager.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import jin.jerrykel.mypasswordmanager.model.User;


public class AccesLocal {
    //propiétés
    private String nomBase = "bdJin";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;

    private SQLiteDatabase bd;

    /**
     * Contructeur
     * @param contexte
     */
    public AccesLocal(Context contexte){
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, null,versionBase);
    }


    public void ajout (User user){
        bd = accesBD.getWritableDatabase();

        String req = "insert into User(datemesure, poids, taille, age, sexe)values";
        //req += "(\"" +User.getDateMesure() +"\"," +Usere.getPoids() + ","+Usere.getTaille() +","+Usere.getAge() +"," +Usere.getSexe()+ ")" ;

        bd.execSQL(req);

    }

    public User recupDernier(){
        bd = accesBD.getReadableDatabase();
       User User = null;
        String req = "select * from User";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            //Date date  = MesOutils.covertStringToDate(curseur.getString(0));
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);

            //User = new User(poids,taille,age,sexe);

        }
        curseur.close();
        return User;
    }
    public  ArrayList<User> recupAll(){
        ArrayList<User> lesUsers = new  ArrayList<User>();
        bd = accesBD.getReadableDatabase();
        User user = null;
        String req = "select * from User";
        Cursor curseur = bd.rawQuery(req, null);
        //curseur.moveToLast();
        //curseur.move(0);
        while (curseur.moveToNext()){
           // Date date  = MesOutils.covertStringToDate(curseur.getString(0));
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);

            //user = new User(date,poids,taille,age,sexe);
            lesUsers.add(user);

        }
        curseur.close();
        return lesUsers;
    }
    public void removeUser(User user){
        bd = accesBD.getWritableDatabase();

       // String req = "DELETE FROM User WHERE datemesure = AND poids, taille, age, sexe";
      //  req += "datemesure =\"" +Usere.getDateMesure() +"\"," +Usere.getPoids() + ","+Usere.getTaille() +","+Usere.getAge() +"," +Usere.getSexe()+ "" ;


        String req = "DELETE FROM User WHERE "; //+
                /*
                "datemesure = \"" +user.getDateMesure()   + "\" " +
                "AND poids = \"" + user.getPoids()       + "\"  " +
                "AND taille = \""+ user.getTaille()      + "\" " +
               "AND age  = \""+   user.getAge()         + "\" " +
               "AND sexe =  \""+  user.getSexe()        + "\" " +
                ";";

                 */

        bd.execSQL(req);
       // bd.close();

    }
}

