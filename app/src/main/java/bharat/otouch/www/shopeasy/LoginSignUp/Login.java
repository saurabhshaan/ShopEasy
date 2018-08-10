package bharat.otouch.www.shopeasy.LoginSignUp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bharat.otouch.www.shopeasy.BackgroundTasks.BackgroundTask;
import bharat.otouch.www.shopeasy.R;

public class Login extends AppCompatActivity{

    private EditText Mmail, passowrd;
    String mmail,mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mmail = (EditText) findViewById(R.id.email);
        passowrd = (EditText) findViewById(R.id.loginpass);
    }

    public void SendData(View view){
        if (Mmail.getText().toString().length() == 0){
            Mmail.setError("Please fill before continew");
            return;
        }else if (passowrd.getText().toString().length() == 0){
            passowrd.setError("Fill your password");
        }else {
            Toast.makeText(this, "Everything is ok", Toast.LENGTH_SHORT).show();
        }
        mmail = Mmail.getText().toString().trim();
        mpassword = passowrd.getText().toString().trim();

        Log.d("ASD","String"+mmail+""+mpassword);

        if (isOnline()){
            String method = "SendData";
            Log.d("ASD","bt start");

            BackgroundTask backgroundTask = new BackgroundTask(Login.this);
            backgroundTask.execute(method,mmail,mpassword);
        }

    }
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

}
