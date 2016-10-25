package io.elpoisterio.sendmessage.helpers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by rishabh on 24/10/16.
 */

public class NotificationHelper {

    private String title;
    private String message;

    String lines[];
    private Context context;
    private Class classTo;
    public static int NOTIFICATION_TYPE_ADD_ACTTION = 0;
    public static int NOTIFICATION_TYPE_INBOX_STYLE = 1;
    public static int NOTIFICATION_TYPE_NORMAL = 2;
    public static int NOTIFICATION_ID_PAYMENT_DUE = 0;
    public static int NOTIFICATION_ID_RECENT = 1;
    public static int NOTIFICATION_ID_FORCE_LOGOUT = 3;
    private NotificationManager notificationManager;

    public NotificationHelper(String title, String message, Context context, Class classTo) {
        this.title = title;
        this.message = message;
        this.context = context;
        this.classTo = classTo;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(context, classTo);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(classTo);
        taskStackBuilder.addNextIntent(intent);

        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private Uri setSoundUri() {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    }

    public void NotificationBuilder() {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
        notification.setContentText(message);
        notification.setAutoCancel(true);
        notification.setContentIntent(createPendingIntent());
        notification.setSound(setSoundUri());
        notification.setContentTitle(title);




        notificationManager.notify(2, notification.build());
    }
}
