package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**Manages timer.
 *
 */
public class TimerActivity extends AppCompatActivity {

    private long startMinutes;
    private long startTime;
    private TextView tvCountDown;
    private TextView txtStop;
    private Button btnStartStop;
    private Button btnReset;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private long timeLeft;

    /**Initializes activity responsible for timer.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MinutesActivity.EXTRA_MESSAGE);
        startMinutes = Long.valueOf(message);

       startTime = startMinutes*60000;
       timeLeft = startTime;

    }

    /**Starts or stops timer in activity after clicking button.
     *
     * @param view
     */
    public void onStartStopClick(View view) {
        tvCountDown = findViewById(R.id.txtTime);
        btnStartStop = findViewById(R.id.btnStartStop);
        btnReset = findViewById(R.id.btnReset);
        txtStop = findViewById(R.id.txtStop);

        if (isTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }

        updateCountDown();
    }

    /**Resets timer in activity after clicking button.
     *
     * @param view
     */
    public void onResetClick(View view) {
        tvCountDown = findViewById(R.id.txtTime);
        btnStartStop = findViewById(R.id.btnStartStop);
        btnReset = findViewById(R.id.btnReset);
        txtStop = findViewById(R.id.txtStop);

        resetTimer();

        updateCountDown();
    }

    /**Sets up timer in activity.
     *
     */
    private void startTimer() {
        txtStop.setText("Ćwicz!");

        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                btnStartStop.setText("START");
                btnStartStop.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                txtStop.setText("Ćwiczenia zakończone");
                tvCountDown.setText("00:00");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(500); //milliseconds
            }
        }.start();

        isTimerRunning = true;
        btnStartStop.setText("STOP");
        btnReset.setVisibility(View.INVISIBLE);
    }

    /**Pauses timer.
     *
     */
    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        btnStartStop.setText("START");
        btnReset.setVisibility(View.VISIBLE);
    }

    /**Resets timer.
     *
     */
    private void resetTimer() {
        timeLeft = startTime;
        updateCountDown();
        btnReset.setVisibility(View.INVISIBLE);
        btnStartStop.setVisibility(View.VISIBLE);
        txtStop.setText("Wciśniej START i zacznij ćwiczyć");
    }

    /**Updates display of timer in activity.
     *
     */
    private void updateCountDown() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvCountDown.setText(timeLeftFormatted);
    }


}