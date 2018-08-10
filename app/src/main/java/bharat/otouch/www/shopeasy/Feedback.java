package bharat.otouch.www.shopeasy;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import bharat.otouch.www.shopeasy.BackgroundTasks.BackgroundTask;
import bharat.otouch.www.shopeasy.BackgroundTasks.FeedbackBackgroundTask;
import bharat.otouch.www.shopeasy.LoginSignUp.Login;

public class Feedback extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Toolbar mToolbar;
    private EditText feedback;
    Spinner mspinner;
    private String selected,feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        feedback = (EditText) findViewById(R.id.editText);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Feedback.this, "Maximum 250 charector", Toast.LENGTH_SHORT).show();
            }
        });
        mspinner = (Spinner) findViewById(R.id.spiner);
        mspinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.feedback,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinner.setAdapter(adapter);
    }
    public void Submit(View view){
        if (feedback.getText().toString().length() == 0){
            feedback.setError("Please fill before continew");
            return;
        }else {
            Toast.makeText(this, "Everything is ok", Toast.LENGTH_SHORT).show();
        }
        feed = feedback.getText().toString().trim();

        Log.d("ASD","String"+feed+""+selected);

        if (isOnline()){
            String method = "Feed";
            Log.d("ASD","bt start");

            FeedbackBackgroundTask backgroundTask = new FeedbackBackgroundTask(Feedback.this);
            backgroundTask.execute(method,feed,selected);
        }else {
            Toast.makeText(this, "Not Connected to Network", Toast.LENGTH_LONG).show();
        }

    }
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        selected = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), ""+selected, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}