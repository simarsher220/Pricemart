package com.example.simasingh.pricemart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DisplayMenShoe extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_men_shoe);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
                .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);
        listView = (ListView) findViewById(R.id.menshoe_listview);
        Bundle b = getIntent().getExtras();
        String link = b.getString("key");
        new MSBackgroundTask().execute(link);
    }

    public class MSBackgroundTask extends AsyncTask<String, MenShoe, List<MenShoe>> {

        @Override
        protected List<MenShoe> doInBackground(String... params) {
            HttpURLConnection httpURLConnection;
            try{
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();;
                StringBuilder stringBuilder = new StringBuilder("");
                String line = "";
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                httpURLConnection.disconnect();
                List<MenShoe> menShoeList = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                MenShoe menShoe = null;
                while(count < jsonArray.length()){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                    count++;
                    menShoe = new MenShoe();
                    menShoe.setImage(jsonObject1.getString("image"));
                    menShoe.setName(jsonObject1.getString("name"));
                    menShoe.setPrice(jsonObject1.getInt("price"));
                    menShoeList.add(menShoe);
                }
                return menShoeList;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<MenShoe> result) {
            super.onPostExecute(result);
            MenShoeAdapter menShoeAdapter = new MenShoeAdapter(getApplicationContext(), R.layout.menshoe_layout, result);
            listView.setAdapter(menShoeAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MenShoe menShoe  = (MenShoe) listView.getItemAtPosition(position);
                    Intent i = new Intent(DisplayMenShoe.this, ProductBuy.class);
                    i.putExtra("url", menShoe.getImage());
                    i.putExtra("name", menShoe.getName());
                    i.putExtra("price", menShoe.getPrice());
                    startActivity(i);
                }
            });
        }
    }

    public class MenShoeAdapter extends ArrayAdapter{

        int resource;
        LayoutInflater layoutInflater;
        List<MenShoe> menShoeList;
        public MenShoeAdapter(Context context, int resource, List<MenShoe> objects) {
            super(context, resource, objects);
            this.resource = resource;
            menShoeList = objects;
            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = layoutInflater.inflate(resource, null);
            }
            ImageView image = (ImageView) convertView.findViewById(R.id.menshoe_image);
            TextView name = (TextView) convertView.findViewById(R.id.menshoe_name);
            TextView price = (TextView) convertView.findViewById(R.id.menshoe_price);

            name.setText(menShoeList.get(position).getName());
            price.setText("$ "+menShoeList.get(position).getPrice());
            ImageLoader.getInstance().displayImage(menShoeList.get(position).getImage(), image);
            return convertView;
        }
    }
}



