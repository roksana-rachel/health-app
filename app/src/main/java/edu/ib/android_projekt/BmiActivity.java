package edu.ib.android_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

/**Manages BMI calculator.
 *
 */
public class BmiActivity extends AppCompatActivity {

    /**Initializes activity responsible for BMI calculator.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
    }

    /**Calculates BMI (from values provided by the user in the activity) after clicking button.
     *
     * @param view
     */
    public void onCalculateClick(View view) {
        EditText height = (EditText) findViewById(R.id.editHeight);
        EditText weight = (EditText) findViewById(R.id.editWeight);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerOp);
        TextView result = (TextView) findViewById(R.id.txtBMI);
        TextView norms = (TextView) findViewById(R.id.tvNorms);
        String jednostkaWzrostu = String.valueOf(spinner.getSelectedItem());

        try{
            double masa = Double.valueOf(weight.getText().toString());
            double wzrost = Double.valueOf(height.getText().toString());

            double BMI=0;

            if(jednostkaWzrostu.equals("cm")){
                wzrost=wzrost/100;
            }
            BMI=masa/(wzrost*wzrost);

            DecimalFormat format = new DecimalFormat("#.##");

            if(Double.isInfinite(BMI) || Double.isNaN(BMI)){
                norms.setText("Dzielenie przez 0?");
            }
            else{
                result.setText(String.valueOf(format.format(BMI)));

                if(BMI<18.5){
                    norms.setText("Masz niedowagę");
                }
                else if(BMI<25){
                    norms.setText("Twoja waga jest prawidłowa");
                }
                else if(BMI<30){
                    norms.setText("Masz nadwagę");
                }
                else{
                    norms.setText("Otyłość");
                }
            }
        }
        catch(NumberFormatException e){

            norms.setText("Co najmniej jedno z pól nie jest liczbą");
        }
    }
}