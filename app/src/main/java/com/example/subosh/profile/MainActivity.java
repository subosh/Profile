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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText username,password;
    Button login;
    TextView signin;
     FirebaseAuth firebaseAuth;
    //FirebaseAuth.AuthStateListener mAuthListener;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {
            startActivity(new Intent(getApplicationContext(), logout.class));
        }
    username=(EditText)findViewById(R.id.user);
    password=(EditText)findViewById(R.id.pass);
    login=(Button) findViewById(R.id.login);
    signin=(TextView)findViewById(R.id.signin);
    signin.setOnClickListener(this);

    progressDialog=new ProgressDialog(this);

login.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {
        if(v==login){

            register();
        }
    if(v==signin)
    {
        finish();
        startActivity(new Intent(getApplicationContext(),login.class));
    }

    }

    private void register() {
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(TextUtils.isEmpty(user))
        {
            Toast.makeText(MainActivity.this,"enter username",Toast.LENGTH_LONG).show();
        return;
        }
        else if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(MainActivity.this,"enter password",Toast.LENGTH_LONG).show();
        return;
        }
        progressDialog.setMessage("registering please wait");
        progressDialog.show();
firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
   if(task.isComplete()){
    Toast.makeText(MainActivity.this,"registeration success",Toast.LENGTH_LONG).show();
    finish();
    startActivity(new Intent( getApplicationContext(), login.class));
   }
   else
   {
       Toast.makeText(MainActivity.this,"registration failed",Toast.LENGTH_LONG).show();

   }
   progressDialog.dismiss();
    }
});
    }



/*
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(mAuthListener);
    }*/
}
