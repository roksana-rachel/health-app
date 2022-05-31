package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**Manages adding new drink to database.
 *
 */
public class AddData extends AppCompatActivity {

    SQLiteDatabase database;
    int[] columnIndices = new int[3];

    /**Initializes activity responsible for adding new drink to database.
     * Opens database.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        database = openOrCreateDatabase("WaterData",MODE_PRIVATE,null);
        String sqlDB = "CREATE TABLE IF NOT EXISTS WaterData (Volume INTEGER, " +
                "Type VARCHAR, CurrentTime VARCHAR)";
        database.execSQL(sqlDB);
    }

    /**Adds new drink (with values provided by the user in the activity) to database.
     *
     * @param view
     */
    public void onAddClick(View view) {

        EditText et1 = (EditText) findViewById(R.id.editMl);
        EditText et2 = (EditText) findViewById(R.id.editType);

        int volume = Integer.valueOf(et1.getText().toString());
        String type = et2.getText().toString();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeW = localTime.format(formatter);

        String waterData = "INSERT INTO WaterData VALUES (?,?,?)";
        SQLiteStatement statement = database.compileStatement(waterData);

        statement.bindLong(1, (long) volume);
        statement.bindString(2, type);
        statement.bindString(3, timeW);
        statement.executeInsert();

        Toast.makeText(this, "Dodano napój", Toast.LENGTH_SHORT).show();
    }

    /**Returns to (starts) activity managing fluid intake.
     *
     * @param view
     */
    public void onBackClick(View view) {
        Intent intent = new Intent(this, WaterActivity.class);
        this.startActivity(intent);
    }
}