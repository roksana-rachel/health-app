package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**Manages menu.
 *
 */
public class ChooseActivity extends AppCompatActivity {

    /**Initializes activity responsible for menu.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        Locale polish = new Locale("pl", "POL");

        TextView tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setText(date.format(formatter));

        TextView tvDay = (TextView) findViewById(R.id.tvDay);
        tvDay.setText(dayOfWeek.getDisplayName(TextStyle.FULL, polish));

    }

    /**Starts new activity (responsible for  getting number of minutes to set the timer) after clicking button.
     * @param view
     */
    public void onTimerClick(View view) {
        Intent intent = new Intent(this, MinutesActivity.class);
        this.startActivity(intent);
    }

    /**Starts new activity (responsible for BMI calculator) after clicking button.
     *
     * @param view
     */
    public void onBmiClick(View view) {
        Intent intent = new Intent(this, BmiActivity.class);
        this.startActivity(intent);
    }

    /**Starts new activity (responsible for exercise videos) after clicking button.
     *
     * @param view
     */
    public void onExerciseClick(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        this.startActivity(intent);
    }

    /**Starts new activity (responsible for stand up alarm) after clicking button.
     *
     * @param view
     */
    public void onStandClick(View view) {
        Intent intent = new Intent(this, StandActivity.class);
        this.startActivity(intent);
    }

    /**Starts new activity (responsible for managing fluid intake) after clicking button.
     *
     * @param view
     */
    public void onWaterClick(View view) {
        Intent intent = new Intent(this, WaterActivity.class);
        this.startActivity(intent);
    }
}