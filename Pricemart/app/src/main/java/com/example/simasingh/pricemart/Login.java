package com.example.simasingh.pricemart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button login;
    EditText password;
    EditText email;
    TextView register;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //directly start profile activity
        }

        login = (Button) findViewById(R.id.email_sign_in_button);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void userLogin(){
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        if(email.length() == 0) {
            Toast.makeText(this, "plz enter your name ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() == 0) {
            Toast.makeText(this, "plz enter your pass ", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging In...Pleas Wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(Login.this, MainActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.email_sign_in_button){
            userLogin();
        }else{
            finish();
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        }
    }
}
