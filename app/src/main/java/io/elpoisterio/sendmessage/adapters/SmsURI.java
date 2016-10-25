package io.elpoisterio.sendmessage.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;

import io.elpoisterio.sendmessage.helpers.AppConstants;
import io.elpoisterio.sendmessage.helpers.Utility;
import io.elpoisterio.sendmessage.models.ModelUser;


/**
 * Created by rishabh on 21/10/16.
 */

public class SmsURI {


    public static ArrayList<ModelUser> getSmsFromInbox(Context context){
        Uri uri = Uri.parse(AppConstants.inbox_uri);
        String [] projection = new String[]{"address", "body", "thread_id", "date", "status", "read","callback_number","type"};
        Cursor cursor= context.getContentResolver().query(uri, projection, null ,null,null);
        HashMap<String,ModelUser> hashMap = new HashMap<>();
        ArrayList<ModelUser> arrayList = new ArrayList<>();
        // Read the sms data and store it in the list
        if(cursor !=null && cursor.getColumnCount() >0){
            cursor.moveToFirst();
            for(int i=0; i < cursor.getCount(); i++) {
                ModelUser modelUser = new ModelUser();
                modelUser.setThread_id(cursor.getString(cursor.getColumnIndexOrThrow("thread_id")));
                if(!hashMap.containsKey(modelUser.getThread_id())) {
                    modelUser.setBody(cursor.getString(cursor.getColumnIndexOrThrow("body")));
                    modelUser.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));

                    modelUser.setDateReceived(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                    modelUser.setSeen(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                    modelUser.setRead(cursor.getString(cursor.getColumnIndexOrThrow("read")));
                    modelUser.setCallback_number(cursor.getString(cursor.getColumnIndexOrThrow("callback_number")));
                    modelUser.setTime(Utility.convertDate(modelUser.getDateReceived()));
                    if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
                        modelUser.setType("1");

                    } else {
                        modelUser.setType("0");

                    }
                    hashMap.put(modelUser.getThread_id(), modelUser);

                    arrayList.add(modelUser);
                }


                cursor.moveToNext();
            }
            cursor.close();
        }
        //getConversations(context);
        return arrayList;


    }


    public static ArrayList<ModelUser> getCOnversations(Context context, String thread_id){
        Uri uri = Uri.parse(AppConstants.inbox_uri);
        String [] projection = new String[]{"address", "body", "thread_id", "date", "status", "read","callback_number","type"};
        String whereClause = "thread_id =? ";
        String[] whereArgs = new String[]{thread_id};
        Cursor cursor= context.getContentResolver().query(uri, projection, whereClause ,whereArgs,null);

        ArrayList<ModelUser> arrayList = new ArrayList<>();
        // Read the sms data and store it in the list
        if(cursor !=null && cursor.getColumnCount() >0){
            cursor.moveToFirst();
            for(int i=0; i < cursor.getCount(); i++) {
                ModelUser modelUser = new ModelUser();
                modelUser.setBody(cursor.getString(cursor.getColumnIndexOrThrow("body")));
                modelUser.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
                modelUser.setThread_id(cursor.getString(cursor.getColumnIndexOrThrow("thread_id")));
                modelUser.setDateReceived(cursor.getString(cursor.getColumnIndexOrThrow("date")));
                modelUser.setSeen(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                modelUser.setRead(cursor.getString(cursor.getColumnIndexOrThrow("read")));
                modelUser.setCallback_number(cursor.getString(cursor.getColumnIndexOrThrow("callback_number")));
                if (cursor.getString(cursor.getColumnIndexOrThrow("type")).contains("1")) {
                    modelUser.setType("1");
                } else {
                    modelUser.setType("0");
                }

                arrayList.add(modelUser);

                cursor.moveToNext();
            }
            cursor.close();
        }
        //getConversations(context);
        return arrayList;


    }


}
