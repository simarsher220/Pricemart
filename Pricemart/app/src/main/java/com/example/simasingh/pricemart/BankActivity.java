package com.example.simasingh.pricemart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class BankActivity extends AppCompatActivity {

    Spinner spinner=null;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        addListenerOnSpinnerItemSelection();
    }

    private void addListenerOnSpinnerItemSelection() {
        spinner=(Spinner)findViewById(R.id.payment_method);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String uri = parent.getItemAtPosition(position).toString();
                switch(uri)
                {
                    case "HDFC":
                        intent=new Intent("android.intent.action.VIEW", Uri.parse("http://www.hdfcbank.co.in/"));
                        startActivity(intent);
                        break;

                    case "ICICI":
                        intent=new Intent("android.intent.action.VIEW", Uri.parse("http://www.icicibank.com/"));
                        startActivity(intent);
                        break;

                    case "SBI":
                        intent=new Intent("android.intent.action.VIEW", Uri.parse("http://www.onlinesbi.com/"));
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
