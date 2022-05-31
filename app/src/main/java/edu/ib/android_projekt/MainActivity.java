package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**Manages start screen.
 *
 */
public class MainActivity extends AppCompatActivity {

    /**Initializes activity responsible for start screen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    /**Starts new activity (responsible for menu) after clicking button.
     *
     * @param view
     */
    public void onStartClick(View view) {
        Intent intent = new Intent(this, ChooseActivity.class);
        this.startActivity(intent);
    }
}