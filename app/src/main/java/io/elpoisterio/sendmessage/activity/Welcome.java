package io.elpoisterio.sendmessage.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.elpoisterio.sendmessage.R;

/**
 * Created by rishabh on 25/10/16.
 */

public class Welcome extends AppCompatActivity {

    private static final int REQUEST_PERMISSION= 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkPermission(Manifest.permission.READ_SMS)) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS,Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode != REQUEST_PERMISSION) {
            Toast.makeText(this, "Sorry, this app cannot work without permission", Toast.LENGTH_SHORT).show();
        } else if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private boolean checkPermission(String perm){

        return ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED ;
    }
}
