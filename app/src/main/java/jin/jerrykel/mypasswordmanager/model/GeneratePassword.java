package jin.jerrykel.mypasswordmanager.model;



public class GeneratePassword {
    private String dateString;
    private String heuredate;
    private String password;

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


}
