package bharat.otouch.www.shopeasy.BackgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class BackPlaceOrder extends AsyncTask<String,Void,String> {
    Context ctx;

    public BackPlaceOrder(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://172.28.172.2/shopeasy/placeorder.php";
        Log.d("TAG","open url");

        String method = params[0];
        if (method.equals("PlaceOrder")){
            String Quantity=params[1];
            String Pro_Name=params[2];
            String Pro_Price=params[3];
            String Pro_Description=params[4];
            String Pro_Type=params[5];
            String Pro_Company=params[6];
            String Pro_Warrenty=params[7];
            try{
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String PlacedProduct = URLEncoder.encode("Quantity", "UTF-8") + "=" + URLEncoder.encode(Quantity, "UTF-8")+"&"+
                        URLEncoder.encode("Pro_Name","UTF-8")+"="+ URLEncoder.encode(Pro_Name,"UTF-8")+"&"+
                        URLEncoder.encode("Pro_Price","UTF-8")+"="+ URLEncoder.encode(Pro_Price,"UTF-8")+"&"+
                        URLEncoder.encode("Pro_Description","UTF-8")+"="+ URLEncoder.encode(Pro_Description,"UTF-8")+"&"+
                        URLEncoder.encode("Pro_Type","UTF-8")+"="+ URLEncoder.encode(Pro_Type,"UTF-8")+"&"+
                        URLEncoder.encode("Pro_Company","UTF-8")+"="+ URLEncoder.encode(Pro_Company,"UTF-8")+"&"+
                        URLEncoder.encode("Pro_Warrenty","UTF-8")+"="+ URLEncoder.encode(Pro_Warrenty,"UTF-8");

                Log.d("BK","WAR"+URLEncoder.encode(Pro_Warrenty,"UTF-8"));

                bufferedWriter.write(PlacedProduct);
                bufferedWriter.flush();
                bufferedWriter.close();

                os.close();

                InputStream is = httpURLConnection.getInputStream();
                is.close();

                return "Order Placed";

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
