package bharat.otouch.www.shopeasy.Adapterclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import bharat.otouch.www.shopeasy.AccessoriesData;
import bharat.otouch.www.shopeasy.R;
import bharat.otouch.www.shopeasy.productDetail.ProductDetail;

/**
 * Created by root on 21/10/17.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<AccessoriesData> data = Collections.emptyList();
    int currentPos = 0;

    public Adapter(Context context, List<AccessoriesData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.productlist,parent,false);
        Myholder myholder = new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Myholder myholder = (Myholder) holder;
        final AccessoriesData current= data.get(position);
        myholder.textView.setText(""+current.ProductName);
        myholder.textView1.setText(""+current.ProductDescription);
        Picasso.with(context).load(current.ProductImage).into(myholder.imageView);

        Log.d("TAG2","ProName"+current.ProductName);
        Log.d("TAG2","Des"+current.ProductDescription);
        Log.d("TAG2","IMG"+current.ProductImage);
        Log.d("TAG2","COM"+current.ProductCompany);
        Log.d("TAG2","Price"+current.ProductPrice);
        Log.d("TAG2","Ptype"+current.ProductType);
        Log.d("TAG2","Pwar"+current.Productwarrenty);


        myholder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(view.getContext(),ProductDetail.class);
                intent.putExtra("Image",current.ProductName);
                intent.putExtra("ProductName",current.ProductName);
                intent.putExtra("ProductDes",current.ProductDescription);
                intent.putExtra("ProductCompany",current.ProductCompany);
                intent.putExtra("ProductPrice",current.ProductPrice);
                intent.putExtra("ProductWar",current.Productwarrenty);
                intent.putExtra("ProductImage",current.ProductImage);
                intent.putExtra("ProductType",current.ProductType);
                view.getContext().startActivity(intent);

                Log.d("TAG5","Pname"+current.ProductName);
                Log.d("TAG5","Ptype"+current.ProductType);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView,textView1;

        public Myholder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.productname);
            textView1 = (TextView) itemView.findViewById(R.id.description);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        }
    }
}
