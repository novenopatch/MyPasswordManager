package jin.jerrykel.mypasswordmanager.model.GenerateModel;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_GeneratePassword")
public class GeneratePassword {
    @DatabaseField(columnName = "idGeneratePassword", generatedId = true)
    private int idGeneratePassword;
    @DatabaseField
    private String dateString;
    @DatabaseField
    private String heuredate;
    @DatabaseField
    private String password;

    public GeneratePassword() {

    }
    public GeneratePassword(String[] date, String password) {
        this.dateString = date[0];
        this.heuredate = date[1];
        this.password = password;
    }



    public String getDateString() {
        return dateString;
    }

    public String getHeuredate() {
        return heuredate;
    }

    public String getPassword() {
        return password;
    }
    public int getIdGeneratePassword() {
        return idGeneratePassword;
    }


}
