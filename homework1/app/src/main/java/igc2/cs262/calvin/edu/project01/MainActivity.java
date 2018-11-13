package igc2.cs262.calvin.edu.project01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Button calculate;
    private TextView value1, value2, operator, result;
    private Spinner opSelect;
    private EditText editValue1, editValue2;
    private String opSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = findViewById(R.id.b_calculate);
        value1 = findViewById(R.id.tv_value1);
        value2 = findViewById(R.id.tv_value2);
        operator = findViewById(R.id.tv_operator);
        result = findViewById(R.id.tv_result);
        opSelect = findViewById(R.id.spinner_operator);
        editValue1 = findViewById(R.id.et_value1);
        editValue2 = findViewById(R.id.et_value2);
        calculate.setOnClickListener(this);
    }

    @Override
    protected void onStart() {

        super.onStart();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operators, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opSelect.setAdapter(adapter);
        opSelect.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        opSelected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this, "Calculating!", Toast.LENGTH_SHORT).show();
        String number1 = editValue1.getText().toString();
        String number2 = editValue2.getText().toString();

        switch (opSelected) {
            case "+":
                try {
                    int addition = Integer.parseInt(number1) + Integer.parseInt(number2);
                    result.setText(String.valueOf(addition));
                } catch (Exception except) {
                    result.setText("Please enter valid numbers!");
                }
                break;
            case "-":
                try {
                    int subtraction = Integer.parseInt(number1) - Integer.parseInt(number2);
                    result.setText(String.valueOf(subtraction));
                } catch (Exception excpt) {
                    result.setText("Please enter valid numbers!");
                }
                break;
            case "*":
                try {
                    int multiplication = Integer.parseInt(number1) * Integer.parseInt(number2);
                    result.setText(String.valueOf(multiplication));
                } catch (Exception except) {
                    result.setText("Please enter valid numbers!");
                }
                break;
            case "/":
                try {
                    int division = Integer.parseInt(number1) / Integer.parseInt(number2);
                    result.setText(String.valueOf(division));
                } catch (Exception except) {
                    result.setText("Please enter valid numbers!");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
