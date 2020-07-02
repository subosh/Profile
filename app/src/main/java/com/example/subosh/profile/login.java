package com.example.subosh.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText user, pass;
    TextView newuser;
    Button signin;
    ProgressDialog progressDialog;

     FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),logout.class));
        }

        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.pass);
        signin =(Button) findViewById(R.id.btsignin);
        newuser =(TextView) findViewById(R.id.sigup);
        newuser.setOnClickListener(this);
        signin.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {
        if (v == newuser) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        if (v == signin) {
            userinfo();

        }

    }

    private void userinfo() {
        String userip = user.getText().toString().trim();
        String passip = pass.getText().toString().trim();
        if (TextUtils.isEmpty(userip)) {
        Toast.makeText(login.this,"please enter your valid username",Toast.LENGTH_LONG).show();
        return;
        }
        else if(TextUtils.isEmpty(passip))
        {
            Toast.makeText(login.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("please wait for sigingin");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userip,passip).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete())
                {

                    finish();
                    startActivity(new Intent(getApplicationContext(),logout.class));
                }
                else
                {
                    Toast.makeText(login.this,"you cant sigup now",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}