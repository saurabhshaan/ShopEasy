package bharat.otouch.www.shopeasy.productDetail;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.BigInteger;

import bharat.otouch.www.shopeasy.BackgroundTasks.BackPlaceOrder;
import bharat.otouch.www.shopeasy.R;

public class ProductDetail extends AppCompatActivity implements View.OnClickListener {
    private ImageView mimageView;
    private TextView mname,mprice,mdescrip,mprotype,mcompany,mwarenty;
    private Button mbutton,mbutton1;
    private EditText meditText;
    String quantity,Pname,Pprice,Pdescription,Pproducttype,Pcompany,Pwarrenty;
   // char Pprice;
    PayPalConfiguration configuration;
    String Client_Id="";
    Intent mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        mimageView = (ImageView) findViewById(R.id.imageView);
        mname = (TextView) findViewById(R.id.prodename);
        mprice = (TextView) findViewById(R.id.prodeprice);
        mdescrip = (TextView) findViewById(R.id.prodedes);
        mprotype = (TextView) findViewById(R.id.prodeproducttype);
        mcompany = (TextView) findViewById(R.id.prodecompany);
        mwarenty = (TextView) findViewById(R.id.prodewarenty);

        configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Client_Id);
        mService = new Intent(ProductDetail.this, PayPalService.class);
        mService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        startService(mService);

        mbutton = (Button) findViewById(R.id.prodebuynow);
        mbutton.setOnClickListener(this);
        mbutton1 = (Button) findViewById(R.id.buy);
        mbutton1.setOnClickListener(this);

        meditText = (EditText) findViewById(R.id.prodeQuanyity);

        Picasso.with(this).load(getIntent().getStringExtra("Image"));
        mname.setText("Product :"+getIntent().getStringExtra("ProductName"));
        mprice.setText("Price :"+getIntent().getStringExtra("ProductPrice"));
        mdescrip.setText("Description :"+getIntent().getStringExtra("ProductDes"));
        mprotype.setText("Catagory :"+getIntent().getStringExtra("ProductType"));
        mcompany.setText("Company :"+getIntent().getStringExtra("ProductCompany"));
        mwarenty.setText("Warrenty :"+getIntent().getStringExtra("ProductWar"));

        Log.d("PD","Pname"+getIntent().getStringExtra("ProductName"));
        Log.d("PD","Pprice"+getIntent().getStringExtra("ProductPrice"));
        Log.d("PD","Description"+getIntent().getStringExtra("ProductDes"));
        Log.d("PD","Catagory"+getIntent().getStringExtra("ProductType"));
        Log.d("PD","Company"+getIntent().getStringExtra("ProductCompany"));
        Log.d("PD","Warrenty"+getIntent().getStringExtra("ProductWar"));
        Log.d("PD","image"+getIntent().getStringExtra("Image"));

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.prodebuynow){
            Buynow();

        }else if (view.getId() == R.id.buy){
            Buynow();
        }
    }
    private void Buynow(){
      //  PayPalPayment cart = new PayPalPayment(new BigInteger(Pprice.getBytes()),"USD","Your Order",PayPalPayment.PAYMENT_INTENT_SALE);

       /*
        if (meditText.getText().toString().length() == 0){
            meditText.setError("Quantity required");
            return;
        }
        else {
        }
        quantity = meditText.getText().toString().trim();
        Pname = getIntent().getStringExtra("ProductName");
        Pprice = getIntent().getStringExtra("ProductPrice");
        Pdescription = getIntent().getStringExtra("ProductDes");
        Pproducttype = getIntent().getStringExtra("ProductType");
        Pcompany = getIntent().getStringExtra("ProductCompany");
        Pwarrenty = getIntent().getStringExtra("ProductWar");

        Log.d("QWE","quantity"+quantity);
        Log.d("QWE","Pname"+Pname);
        Log.d("QWE","Pprice"+Pprice);
        Log.d("QWE","Description"+Pdescription);
        Log.d("QWE","quantity"+Pproducttype);
        Log.d("QWE","quantity"+Pcompany);
        Log.d("QWE","quantity"+Pwarrenty);

        if (isOnline()){
            String method = "PlaceOrder";
            Log.d("CON","Connection is ok");
            Log.d("CON","bt start");
            BackPlaceOrder blackOlaceOrder = new BackPlaceOrder(this);
            blackOlaceOrder.execute(method,quantity,Pname,Pprice,Pdescription,Pproducttype,Pcompany,Pwarrenty);
            Log.d("CON","bt close");
        }else{
            Toast.makeText(this, "cheak Your connection", Toast.LENGTH_SHORT).show();
        }
        */
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }
}
