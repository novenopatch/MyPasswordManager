package jin.jerrykel.mypasswordmanager.controleur.AppActivity.smsProgamCode;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import jin.jerrykel.mypasswordmanager.R;

public class SendMessageActivity extends AppCompatActivity {
    private  boolean viewListContact = true;
    private EditText txtContact;
    private EditText txtPhone;
    private EditText txtMessage;
    private LinearLayout myLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        txtContact  = findViewById(R.id.txtContact);
        txtPhone  = findViewById(R.id.txtPhone);
        txtMessage  = findViewById(R.id.txtMessage);
        myLinearLayout  = findViewById(R.id.myLinearLayout);

    }


    public void recupContact(){
        // access
        ContentResolver contentResolver = this.getContentResolver();
        //recup
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                ContactsContract.CommonDataKinds.Phone.NUMBER},null ,null,null);

        if(cursor== null){
            Log.d("recup","*************error cursor");
        }else {

            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                txtContact.setText(txtContact.getText().toString() + "\n\r" + name+":"+phone);

                //
            }
            cursor.close();
        }
    }

    public void afficheContactList(View view) {
        Button contactButton = (Button)view;
        if(viewListContact){
            viewListContact = false;
            txtContact.setText("");
            contactButton.setText("afficher les contact");

        }else {

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){

                    viewListContact = true;
                    recupContact();
                    contactButton.setText("cacher les contact");
                

            }else {
                // demande une fois de donner la permission
                if(!ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this,Manifest.permission.READ_CONTACTS)){
                    String[] permissions = {Manifest.permission.READ_CONTACTS};
                    //afficher ask permission
                    ActivityCompat.requestPermissions(SendMessageActivity.this,permissions,2);
                }else {
                    // afficher un message precisant que la permission est indispensable
                    messageAddPermissionF("Persmission READ contact Obligatoire");
                }
            }

        }
    }

    public void sendMessage(View view) {
       if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
           String phoneString = txtPhone.getText().toString();
           String messageString = txtMessage.getText().toString();
           if(phoneString !=null && messageString !=null){
               SmsManager.getDefault().sendTextMessage(phoneString,null,messageString,null,null);
               Toast.makeText(this,"Sms envoyé",Toast.LENGTH_SHORT).show();
               txtMessage.setText("");
           }
       }else {

           if(!ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this,Manifest.permission.SEND_SMS)){
               String[] permissions = {Manifest.permission.SEND_SMS};

               ActivityCompat.requestPermissions(SendMessageActivity.this,permissions,3);
           }else {
               messageAddPermissionF("Persmission SEND SMS Obligatoire");
           }
       }



    }
    public  void messageAddPermissionF(String msj){
        Snackbar.make(myLinearLayout,msj,Snackbar.LENGTH_SHORT).setAction("TODO Paramètres", view -> {
            final Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            final Uri uri = Uri.fromParts("package",SendMessageActivity.this.getPackageName(),null);
            intent.setData(uri);
            startActivity(intent);
        }).show();
    }
}