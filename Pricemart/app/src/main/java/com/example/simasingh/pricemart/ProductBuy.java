package com.example.simasingh.pricemart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ProductBuy extends AppCompatActivity implements View.OnClickListener{

    ImageView product;
    TextView pname, pprice;
    Button cart;
    private String image;
    private String name;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_buy);
        product = (ImageView) findViewById(R.id.product_image);
        pname = (TextView) findViewById(R.id.product_name);
        pprice = (TextView) findViewById(R.id.product_price);
        cart = (Button) findViewById(R.id.add_to_cart);
        Bundle b = getIntent().getExtras();
        image = b.getString("url");
        name = b.getString("name");
        price = b.getInt("price");

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);

        ImageLoader.getInstance().displayImage(image, product);
        pname.setText(name);
        pprice.setText(price +"");
        cart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(ProductBuy.this, BankActivity.class);
        i.putExtra("price", price);
        startActivity(i);
    }
}
