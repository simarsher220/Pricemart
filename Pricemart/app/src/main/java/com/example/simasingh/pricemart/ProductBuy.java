package com.example.simasingh.pricemart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ProductBuy extends AppCompatActivity {

    ImageView product;
    TextView pname, pprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_buy);
        product = (ImageView) findViewById(R.id.product_image);
        pname = (TextView) findViewById(R.id.product_name);
        pprice = (TextView) findViewById(R.id.product_price);

        Bundle b = getIntent().getExtras();
        String image = b.getString("url");
        String name = b.getString("name");
        int price = b.getInt("price");

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);

        ImageLoader.getInstance().displayImage(image, product);
        pname.setText(name);
        pprice.setText(price+"");
    }
}
