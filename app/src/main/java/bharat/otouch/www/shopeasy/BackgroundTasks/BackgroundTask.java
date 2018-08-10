package bharat.otouch.www.shopeasy.BackgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by root on 23/10/17.
 */

public class BackgroundTask extends AsyncTask<String ,Void,String> {
    Context ctx;

    public BackgroundTask(Context context){
        this.ctx = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.43.147/shopeasy/loginattempt.php";
        Log.d("ASD","After url");

        String method = params[0];
        if (method.equals("SendData")){
            String MailNumber = params[1];
            String Password = params[2];

            Log.d("ASD","Str"+MailNumber+""+Password);

            try{
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                Log.d("ASD","open connect");

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String Login = URLEncoder.encode("MailNumber","UTF-8")+"="+URLEncoder.encode(MailNumber,"UTF-8")+"&"+
                        URLEncoder.encode("Password","UTF-8")+"="+ URLEncoder.encode(Password,"UTF-8");

                bufferedWriter.write(Login);
                bufferedWriter.flush();
                bufferedWriter.close();

                os.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
