package jin.jerrykel.mypasswordmanager.utils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //proprietés
    private String creation = "create table userTable ("
            + "datemesure TEXT PRIMARY KEY,"
            + "poids INTEGER NOT NULL,"
            + "taille INTEGER NOT NULL,"
            + "age INTEGER NOT NULL,"
            + "sexe INTEGER NOT NULL);";

    /**
     * contructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name, factory,version);

    }

    /**
     * elle ne s'execute que une seule fois à la creation de la db ou bien-sur si l'on change de nom de la db
     * bref à a un changement concernant la db
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
    }

    /**
     * si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
