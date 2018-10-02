package boo3igc2.cs262.calvin.edu.lab02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         }

    public class MainActivity extends AppCompatActivity {
        private int mCount = 0;
        private TextView mShowCount;
        mShowCount = (TextView) findViewById(R.id.show_count);


        countUp Method:
        public void countUp(View view) {
            mCount++;
            if (mShowCount != null)
                mShowCount.setText(Integer.toString(mCount));
        }



    public void showToast(View view) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, R.string.toast_message, Toast.LENGTH_LONG);
        Toast toast = Toast.makeText(context, R.string.toast_message, duration);
        toast.show();

    }

    public void countUp(View view) {
    }
}
