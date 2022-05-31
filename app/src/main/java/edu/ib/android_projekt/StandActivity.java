package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

/**Managaes Stand Up alarm (repeating alarm) sending notifications.
 *
 */
public class StandActivity extends AppCompatActivity {

    private NotificationManager notManager;
    private static final int NOTIFICATION_ID = 0;

    /**Initializes activity responsible for an alarm.
     * If user turns on the alarm using toggle buton, it sends notification every half an hour to stand up and move around.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);

        notManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        ToggleButton alarmToggle = (ToggleButton)findViewById(R.id.alarmToggle);

        Intent intent = new Intent(this, AlarmReceiver.class);

        //true, jeśli alarm istnieje
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, intent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);

        final PendingIntent notify = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if(isChecked){
                    long interval = AlarmManager.INTERVAL_HALF_HOUR;
                    long time = SystemClock.elapsedRealtime() + interval;

                    if (alarmManager != null) {
                        alarmManager.setInexactRepeating
                                (AlarmManager.ELAPSED_REALTIME_WAKEUP, time, interval, notify);
                    }
                    toastMessage = "Stand Up! włączony";
                } else {
                    notManager.cancelAll();

                    if (alarmManager != null) {
                        alarmManager.cancel(notify);
                    }

                    toastMessage = "Stand Up! wyłączony";
                }

                Toast.makeText(StandActivity.this, toastMessage,Toast.LENGTH_SHORT).show();
            }
        });


    }

}