package bharat.otouch.www.shopeasy.BackgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by root on 25/10/17.
 */

public class FeedbackBackgroundTask extends AsyncTask<String, Void, String>{
    Context ctx;

    public FeedbackBackgroundTask(Context context){
        this.ctx = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.43.147/shopeasy/feedback.php";
        Log.d("ASD","After url");

        String method = params[0];
        if (method.equals("Feed")){
            String SpinerValue = params[1];
            String Feedback = params[2];

            Log.d("ASD","Str"+SpinerValue+""+Feedback);

            try{
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                Log.d("ASD","open connect");

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String Login = URLEncoder.encode("Responce","UTF-8")+"="+URLEncoder.encode(SpinerValue,"UTF-8")+"&"+
                        URLEncoder.encode("Feedback","UTF-8")+"="+ URLEncoder.encode(Feedback,"UTF-8");

                Log.d("ASD","RES"+URLEncoder.encode(SpinerValue,"UTF-8"));
                Log.d("ASD","RES"+URLEncoder.encode(Feedback,"UTF-8"));

                bufferedWriter.write(Login);
                bufferedWriter.flush();
                bufferedWriter.close();

                os.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                return "Login Success...Welcome";

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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
