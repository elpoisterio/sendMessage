package io.elpoisterio.sendmessage.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import io.elpoisterio.sendmessage.activity.MainActivity;
import io.elpoisterio.sendmessage.helpers.NotificationHelper;

/**
 * Created by rishabh on 22/10/16.
 */

public class SmsReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(SMS_RECEIVED)){
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");

                if (pdus !=null && pdus.length >0) {
                    SmsMessage message = null;
                    String body = "";
                    for (Object pdu : pdus) {

                        message = SmsMessage.createFromPdu((byte[]) pdu);
                        String smsBody = message.getMessageBody();
                        body += smsBody;
                    }

                    assert message != null;
                    String address = message.getOriginatingAddress();
                    String time = String.valueOf(message.getTimestampMillis());
                    String type = "1";
                    NotificationHelper notificationHelper = new NotificationHelper(address,body,context,MainActivity.class);
                    notificationHelper.NotificationBuilder();
                    MainActivity instance = new MainActivity().getInstance();
                    instance.getSmsURI();
                }

            }
        }

    }
}
