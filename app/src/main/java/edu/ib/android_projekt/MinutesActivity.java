package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**Manages activity responsible for getting number of minutes to set the timer.
 *
 */
public class MinutesActivity extends AppCompatActivity {

    static final String EXTRA_MESSAGE = "message";

    /**Initializes activity responsible for getting number of minutes to set the timer.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minutes);
    }

    /**Starts new activity (responsible for timer) after clicking button.
     *Sends number of minutes provided by the user to new activity.
     * @param view
     */
    public void onMinutesClick(View view) {
        EditText etMinutes = (EditText) findViewById(R.id.editMinutes);
        String message = etMinutes.getText().toString();

        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        this.startActivity(intent);
    }
}