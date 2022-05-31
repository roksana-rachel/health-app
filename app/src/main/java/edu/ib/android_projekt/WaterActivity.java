package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**Manages monitoring fluid intake
 *
 */
public class WaterActivity extends AppCompatActivity {

    SQLiteDatabase database;
    int[] columnIndices = new int[3];
    double percent=0;

    /**Initializes activity responsible for monitoring fluid intake
     * Creates (or opens) database and table with consumed drinks
     * Displays list of consumed drinks, percent of daily fluid intake and progress bar
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        TextView tvPercent = (TextView) findViewById(R.id.tvPercent);
        TextView tvGoal = (TextView) findViewById(R.id.tvGoal);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.getProgressDrawable().setColorFilter(
                Color.argb(100, 33, 150, 243), android.graphics.PorterDuff.Mode.SRC_IN);

        database = openOrCreateDatabase("WaterData",MODE_PRIVATE,null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS WaterData (Volume INTEGER, " +
                "Type VARCHAR, CurrentTime VARCHAR)";
        database.execSQL(sqlDB);

        ArrayList<String> wyniki = new ArrayList<>();
        Cursor c; //tymczasowa struktura do pobierania wyników zapytań
        c=database.rawQuery("SELECT Volume, Type, CurrentTime FROM WaterData",null);

        if(c.moveToFirst()){

            do {
                int volume = c.getInt(c.getColumnIndex("Volume"));
                String type = c.getString(c.getColumnIndex("Type"));
                String currentTime = c.getString(c.getColumnIndex("CurrentTime"));

                wyniki.add(volume + " ml" + "\n" + type + "\n" + "Godzina dodania: " + currentTime);

            } while(c.moveToNext());
        }

        String sqlPercent = "SELECT SUM(Volume) FROM WaterData";
        c = database.rawQuery(sqlPercent,null);
        c.moveToFirst();
        if(c.getString(0)==null){
            percent=0;
            Log.d("EDUIB", "0");
        }
        else {
            percent = (Double.parseDouble(c.getString(0))/2000)*100;
            Log.d("EDUIB", String.valueOf(percent));
        }

            tvPercent.setText((int)percent + "%");

        if(percent>100){
            tvGoal.setText("Osiągnięto dzienny cel!");
            progressBar.setProgress(100);
        }
        else{
            progressBar.setProgress((int)percent);
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,wyniki);
        listView.setAdapter(adapter);
        c.close();
    }

    /**Starts new activity (responsible for adding consumed drinks to database) after clicking button
     *
     * @param view
     */
    public void onAddClick(View view) {
        Intent intent = new Intent(this, AddData.class);
        this.startActivity(intent);
    }

    /**Deletes database, creates new one (so user can monitor new daily fluid intake)
     * Starts activity responsible for daily fluid intake once again
     * @param view
     */
    public void onNewDayClick(View view) {
        database = openOrCreateDatabase("WaterData",MODE_PRIVATE,null);
        String sqlDB = "DROP TABLE IF EXISTS WaterData";
        database.execSQL(sqlDB);

        database = openOrCreateDatabase("WaterData",MODE_PRIVATE,null);
        sqlDB = "CREATE TABLE IF NOT EXISTS WaterData (Volume INTEGER, " +
                "Type VARCHAR, CurrentTime VARCHAR)";
        database.execSQL(sqlDB);

        Intent intent = new Intent(this, WaterActivity.class);
        this.startActivity(intent);
    }
}