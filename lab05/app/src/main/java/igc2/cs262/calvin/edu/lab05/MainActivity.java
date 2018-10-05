package igc2.cs262.calvin.edu.lab05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private TextView mAuthorText;
    private TextView mTitleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = findViewById(R.id.bookInputs);
        mAuthorText = findViewById(R.id.authorText);
        mTitleText = findViewById(R.id.titleText);
    }

    public void searchBooks(View aView) {
        String mQueryString = mBookInput.getText().toString();
        new FetchBook(mTitleText, mAuthorText).execute(mQueryString);


    }
}
