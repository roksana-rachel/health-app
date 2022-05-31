package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import java.text.DecimalFormat;

/**Allows watching exercise videos in YouTube.
 *
 */
public class ExerciseActivity extends AppCompatActivity {

    static final String EXTRA_MESSAGE = "message";

    /**Initializes activity responsible for exercise videos.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
    }

    /**After clicking button, it sends user to YouTube app to watch video chosen by the user.
     *
     * @param view
     */
    public void onWatchClick(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spExercise);
        String film = String.valueOf(spinner.getSelectedItem());
        String message="";

        if(film.equals("Rozgrzewka, 11 min")){
            message = "https://www.youtube.com/watch?v=XV3g2c9SC-g";
        }
        else if(film.equals("Lekki trening, 31 min")){
            message = "https://www.youtube.com/watch?v=85ZE3meQlno";
        }
        else if(film.equals("Intensywny trening, 16 min")){
            message = "https://www.youtube.com/watch?v=Q3k_FpyrM2M";
        }

        Intent yt_play = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
        startActivity(yt_play);
    }
}