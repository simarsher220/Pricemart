package com.example.simasingh.pricemart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class BankActivity extends AppCompatActivity {

    Spinner spinner=null;
    Intent intent;
    int price;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    EditText user, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        addListenerOnSpinnerItemSelection();
        Bundle b = getIntent().getExtras();
        price = b.getInt("price");
        TextView textView = (TextView) findViewById(R.id.display_amount);
        user = (EditText) findViewById(R.id.enter_name);
        address = (EditText) findViewById(R.id.enter_address);
        textView.setText("$"+price);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void addListenerOnSpinnerItemSelection() {
        spinner=(Spinner)findViewById(R.id.payment_method);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String username = user.getText().toString().trim();
                String address1 = address.getText().toString().trim();
                Details details = new Details(username, address1, price);
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                databaseReference.child(firebaseUser.getUid()).setValue(details);
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

                    case "AXIS":
                        intent=new Intent("android.intent.action.VIEW", Uri.parse("https://www.axisbank.com/"));
                        startActivity(intent);
                        break;

                    case "IDBI":
                        intent=new Intent("android.intent.action.VIEW", Uri.parse("http://www.idbi.com/index.asp"));
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
