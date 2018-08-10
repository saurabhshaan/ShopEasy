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
 * Created by root on 23/10/17.
 */

    public class BackgroundTaskSignUp extends AsyncTask<String ,Void,String> {
        Context ctx;

    public BackgroundTaskSignUp(Context context){
        this.ctx = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://172.28.172.2/shopeasy/peoplesignupaccount.php";
        Log.d("ASD","After url");

        String method = params[0];
        if (method.equals("SendData")){
        String Email = params[1];
        String PhoneNumber = params[2];
            String FullName = params[3];
            String Password = params[4];
            String PostalCode = params[5];
            String Add1 = params[6];
            String Add2 = params[7];
            String Add3 = params[8];
            String City = params[9];
            String State = params[10];

        Log.d("ASD","Str"+Email+""+PhoneNumber+""+FullName+""+Password+""+PostalCode+""+Add1+""+Add2+""+Add3+""+City+""+State);

        try{
        URL url = new URL(reg_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        Log.d("ASD","open connect");

        OutputStream os = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

        String Login = URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8")+"&"+
        URLEncoder.encode("PhoneNumber","UTF-8")+"="+ URLEncoder.encode(PhoneNumber,"UTF-8")+"&"+
                URLEncoder.encode("FullName","UTF-8")+"="+ URLEncoder.encode(FullName,"UTF-8")+"&"+
                URLEncoder.encode("Password","UTF-8")+"="+ URLEncoder.encode(Password,"UTF-8")+"&"+
                URLEncoder.encode("PostalCode","UTF-8")+"="+ URLEncoder.encode(PostalCode,"UTF-8")+"&"+
                URLEncoder.encode("Add1","UTF-8")+"="+ URLEncoder.encode(Add1,"UTF-8")+"&"+
                URLEncoder.encode("Add2","UTF-8")+"="+ URLEncoder.encode(Add2,"UTF-8")+"&"+
                URLEncoder.encode("Add3","UTF-8")+"="+ URLEncoder.encode(Add3,"UTF-8")+"&"+
                URLEncoder.encode("City","UTF-8")+"="+ URLEncoder.encode(City,"UTF-8")+"&"+
                URLEncoder.encode("State","UTF-8")+"="+ URLEncoder.encode(State,"UTF-8");

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