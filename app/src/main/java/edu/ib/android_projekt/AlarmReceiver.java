package edu.ib.android_projekt;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**Receives and handles broadcast intents from AlarmManager.
 *
 */
public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager notManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    /**This method is called when the BroadcastReceiver is receiving an intent broadcast.
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        notManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);
    }

    /**Builds and delivers notification.
     *
     * @param context
     */
    private void deliverNotification(Context context) {
        Intent intent = new Intent(context, StandActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Stand Up!")
                .setContentText("Wstań i porusz się trochę")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        notManager.notify(NOTIFICATION_ID, builder.build());
    }
}