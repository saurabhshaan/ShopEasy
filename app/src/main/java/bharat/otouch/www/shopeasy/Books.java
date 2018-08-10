package bharat.otouch.www.shopeasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bharat.otouch.www.shopeasy.Adapterclass.Adapter;
import bharat.otouch.www.shopeasy.LoginSignUp.Login;

public class Books extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private Adapter mAdapter;
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        new Books.AsyncFetch().execute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if (id == R.id.menfashion){
            Intent intent = new Intent(this,MensFashion.class);
            startActivity(intent);
        }else if (id == R.id.womenfashion){
            Intent intent = new Intent(this,WomenFashion.class);
            startActivity(intent);
        }else if (id == R.id.kidsfashion){
            Intent intent = new Intent(this,KidsFashion.class);
            startActivity(intent);
        }else if (id == R.id.accessories){
            Intent intent = new Intent(this,Accessories.class);
            startActivity(intent);
        }else if (id == R.id.home){
            Intent intent = new Intent(this,HomeAppliences.class);
            startActivity(intent);
        }else if (id == R.id.mobile){
            Intent intent = new Intent(this,Phones.class);
            startActivity(intent);
        }else if (id == R.id.phoneaccessories){
            Intent intent = new Intent(this,PhoneAccessories.class);
            startActivity(intent);
        }else if (id == R.id.computer){
            Intent intent = new Intent(this,Computer.class);
            startActivity(intent);
        }else if (id == R.id.computeraccessories){
            Intent intent = new Intent(this,ComputerAccessories.class);
            startActivity(intent);
        }else if (id == R.id.kitchen){
            Intent intent = new Intent(this,Kitchen.class);
            startActivity(intent);
        }else if (id == R.id.health){
            Intent intent = new Intent(this,Health.class);
            startActivity(intent);
        }else if (id == R.id.homedecoration){
            Intent intent = new Intent(this,HomeDecoration.class);
            startActivity(intent);
        }else if (id == R.id.books){
            Intent intent = new Intent(this,Books.class);
            startActivity(intent);
        }else if (id == R.id.bikeandcar){
            Intent intent = new Intent(this,BikeandCar.class);
            startActivity(intent);
        }else if (id == R.id.userlogin){
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        }else if (id == R.id.feedback){
            Intent intent = new Intent(this,Feedback.class);
            startActivity(intent);
        }
        return false;
    }

    class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(Books.this);
        HttpURLConnection connection;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            progressDialog.setMessage("\tLoading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL("http://192.168.43.147/shopeasy/booksjson.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                connection.setDoOutput(true);
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();

            }

            try {

                int response_code = connection.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                connection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG1",result);
            progressDialog.dismiss();
            List<AccessoriesData> data = new ArrayList<>();
            progressDialog.dismiss();
            try {
                Log.d("TAG1", result);
                JSONArray jarray = new JSONArray(result);

                for (int i=0; i<jarray.length();i++){
                    JSONObject jobject = jarray.getJSONObject(i);
                    AccessoriesData accessdata = new AccessoriesData();

                    Log.d("TAG","Name"+jobject.getString("Product Name"));
                    Log.d("TAG","Company"+jobject.getString("Product Company"));
                    Log.d("TAG","warrenty"+jobject.getString("Product warrenty"));
                    Log.d("TAG","Price"+jobject.getString("Product Price"));
                    Log.d("TAG","Description"+jobject.getString("Product Description"));
                    Log.d("TAG","Image"+jobject.getString("Product Image"));

                    accessdata.ProductName= jobject.getString("Product Name");
                    accessdata.ProductType= jobject.getString("Product Type");
                    accessdata.ProductCompany = jobject.getString("Product Company");
                    accessdata.Productwarrenty = jobject.getString("Product warrenty");
                    accessdata.ProductPrice = jobject.getString("Product Price");
                    accessdata.ProductDescription = jobject.getString("Product Description");
                    accessdata.ProductImage = jobject.getString("Product Image");

                    data.add(accessdata);
                }
                recyclerView = (RecyclerView) findViewById(R.id.recycleview);
                Log.d("TAG","recycle!!");
                mAdapter = new Adapter(Books.this,data);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Books.this));
                Log.d("TAG","DATAPASS");

            } catch (JSONException e) {
                Toast.makeText(Books.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
