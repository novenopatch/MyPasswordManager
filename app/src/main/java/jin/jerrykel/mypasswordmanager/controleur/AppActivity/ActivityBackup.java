package jin.jerrykel.mypasswordmanager.controleur.AppActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import jin.jerrykel.mypasswordmanager.R;
import jin.jerrykel.mypasswordmanager.utils.StorageUtils;

public class ActivityBackup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup);
        this.readFromStorage();
    }
      //...

    // 1 - FILE PURPOSE
    private static final String FILENAME = "tripBook.txt";
    private static final String FOLDERNAME = "bookTrip";
    private RadioButton radioButtonExternalChoice;
    private RadioButton radioButtonExternalPublicChoice;
    private EditText editText;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //...
            case R.id.accelerate:
                // 5 - Save
                this.save();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickRadioButton(CompoundButton button, boolean isChecked){
        if (isChecked) {

        }
        // 7 - Read from storage after user clicked on radio buttons
        this.readFromStorage();
    }

    // 4 - Save after user clicked on button
    private void save(){
        if (this.radioButtonExternalChoice.isChecked()){
            this.writeOnExternalStorage(); //Save on external storage
        } else {
            //TODO: Save on internal storage
        }
    }

    // ----------------------------------
    // UTILS - STORAGE
    // ----------------------------------

    // 2 - Read from storage
    private void readFromStorage(){
        if (this.radioButtonExternalChoice.isChecked()){
            if (StorageUtils.isExternalStorageReadable()){
                // EXTERNAL
                if (radioButtonExternalPublicChoice.isChecked()){
                    // External - Public
                    this.editText.setText(StorageUtils.getTextFromStorage(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),this, FILENAME, FOLDERNAME));
                } else {
                    // External - Privatex
                    this.editText.setText(StorageUtils.getTextFromStorage(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), this, FILENAME, FOLDERNAME));
                }
            }
        } else {
            // TODO : READ FROM INTERNAL STORAGE
        }
    }

    // 3 - Write on external storage
    private void writeOnExternalStorage(){
        if (StorageUtils.isExternalStorageWritable()){
            if (radioButtonExternalPublicChoice.isChecked()){
                StorageUtils.setTextInStorage(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), this, FILENAME, FOLDERNAME, this.editText.getText().toString());
            } else {
                StorageUtils.setTextInStorage(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), this, FILENAME, FOLDERNAME, this.editText.getText().toString());
            }
        } else {
            Toast.makeText(this, "getString(R.string.external_storage_impossible_create_file)", Toast.LENGTH_LONG).show();
        }
    }
}