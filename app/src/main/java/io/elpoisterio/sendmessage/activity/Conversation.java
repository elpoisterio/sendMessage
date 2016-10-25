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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import io.elpoisterio.sendmessage.R;
import io.elpoisterio.sendmessage.adapters.ConversationRecyclerAdapter;
import io.elpoisterio.sendmessage.adapters.SmsURI;
import io.elpoisterio.sendmessage.models.ModelUser;

/**
 * Created by rishabh on 22/10/16.
 */

public class Conversation extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSION = 1;
    private EditText messageBox;
    private Button send;
    private RecyclerView recyclerView;
    private ArrayList<ModelUser> modelUserArrayList;
    private ConversationRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Intent intent = getIntent();
        initView();
        if(intent !=null)
            getConvFromDB(intent);
        setAdapter(modelUserArrayList);


    }
    private void initView(){
        messageBox = (EditText) findViewById(R.id.message_input);
        send = (Button) findViewById(R.id.send_button);
        send.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

    }
    private void getConvFromDB(Intent intent){
        String id = intent.getStringExtra("id");
        modelUserArrayList = SmsURI.getCOnversations(this, id);

    }

    private void setAdapter(ArrayList<ModelUser> modelUserArrayList){
         adapter = new ConversationRecyclerAdapter(this, modelUserArrayList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        if(v == send && !messageBox.getText().toString().equals("")){
            sendSmS();

        }
    }

    private ModelUser updateNewModel() {
        ModelUser modelUser = new ModelUser();
        modelUser.setBody(messageBox.getText().toString());
        modelUser.setAddress(modelUserArrayList.get(0).getAddress());
        modelUser.setType("0");
        modelUser.setThread_id(modelUserArrayList.get(0).getThread_id());
        return modelUser;
    }
    private void sendSmS(){
        if(!checkPermission()){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS,Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION);
        }else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(modelUserArrayList.get(0).getAddress(), null, messageBox.getText().toString(), null,null);
            adapter.addNewMessage(updateNewModel());
            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() -1 );
            messageBox.setText("");
        }

    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED ;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode != REQUEST_PERMISSION) {
            Toast.makeText(this, "Sorry, cannot send message without permission", Toast.LENGTH_SHORT).show();
        } else if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
           sendSmS();
        }
    }
}
