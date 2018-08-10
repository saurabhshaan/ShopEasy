package bharat.otouch.www.shopeasy.LoginSignUp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bharat.otouch.www.shopeasy.BackgroundTasks.BackgroundTaskSignUp;
import bharat.otouch.www.shopeasy.R;

import static bharat.otouch.www.shopeasy.R.id.city;

/**
 * Created by root on 23/10/17.
 */

public class SignUp extends AppCompatActivity {
    private EditText mEmail,mPhoneNumber,mFullName,mPassword,mPostalCode,mAddress1,mAddress2,mAddress3,mCity,mState;
    String qEmail,qPhoneNumber,qFullName,qPassword,qPostalcode,qAdd1,qAdd2,qAdd3,qCity,qState;
    private Button mReturn, mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_account);

        mEmail = (EditText) findViewById(R.id.email);
        mPhoneNumber = (EditText) findViewById(R.id.mobilenumber);
        mFullName = (EditText) findViewById(R.id.fullname);
        mPassword = (EditText) findViewById(R.id.password);
        mPostalCode = (EditText) findViewById(R.id.postalcode);
        mAddress1 = (EditText) findViewById(R.id.addline1);
        mAddress2 = (EditText) findViewById(R.id.addline2);
        mAddress3 = (EditText) findViewById(R.id.addline3);
        mCity = (EditText) findViewById(city);
        mState = (EditText) findViewById(R.id.state);

        mAddress3.setText(" ");

        mReturn = (Button) findViewById(R.id.returnback);
        mLogin = (Button) findViewById(R.id.nextforword);
    }

    public void SendData(View view){
        if (mEmail.getText().toString().length() == 0){
            mEmail.setError("fill email");
            return;
        }else if (mPhoneNumber.getText().toString().length() == 0){
            mPhoneNumber.setError("Fill mobile no.");
        }else if (mFullName.getText().toString().length() == 0){
           mFullName.setError("fill name");
        }else if (mPassword.getText().toString().length() == 0){
            mPassword.setError("fill password");
        }else if (mPostalCode.getText().toString().length() == 0){
            mPostalCode.setError("fill postal code");
        }else if (mAddress1.getText().toString().length() == 0){
            mAddress1.setError("Fill address");
        }else if (mAddress2.getText().toString().length() == 0){
            mAddress2.setError("fill address");
        }else if (mCity.getText().toString().length() == 0){
            mCity.setError("fill city");
        }else if (mState.getText().toString().length() == 0){
            mState.setError("fill state");
        }else{
            Toast.makeText(this, "Everything is ok", Toast.LENGTH_SHORT).show();
        }
        qEmail = mEmail.getText().toString().trim();
        qPhoneNumber = mPhoneNumber.getText().toString().trim();
        qFullName = mFullName.getText().toString().trim();
        qPassword = mPassword.getText().toString().trim();
        qPostalcode = mPostalCode.getText().toString().trim();
        qAdd1 = mAddress1.getText().toString().trim();
        qAdd2 = mAddress2.getText().toString().trim();
        qAdd3 = mAddress3.getText().toString().trim();
        qCity = mCity.getText().toString().trim();
        qState = mState.getText().toString().trim();

        Log.d("ASD","String"+qEmail+""+qPhoneNumber+""+qFullName+""+qPassword+""+qPostalcode+""+qAdd1+""+qAdd2+""+qAdd3+""+qCity+""+qState);

        if (isOnline()){
            String method = "SendData";
            Log.d("ASD","bt start");

            BackgroundTaskSignUp backgroundTaskSignUp = new BackgroundTaskSignUp(SignUp.this);
            backgroundTaskSignUp.execute(method,qEmail,qPhoneNumber,qFullName,qPassword,qPostalcode,qAdd1,qAdd2,qAdd3,qCity,qState);
        }

    }
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
