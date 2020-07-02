package com.example.subosh.profile;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.StringReader;

public class logout extends AppCompatActivity implements View.OnClickListener{
FirebaseAuth firebaseAuth;
DatabaseReference databaseReference;

TextView welcome,taxresult;
Button logout,tax;
EditText name,number,salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        databaseReference= FirebaseDatabase.getInstance().getReference("USERS");
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(getApplicationContext(),login.class));
            }

        name=(EditText)findViewById(R.id.etname);
        number=(EditText)findViewById(R.id.etnumber);
        salary=(EditText)findViewById(R.id.etsalary);
    welcome=(TextView)findViewById(R.id.tvwelcome);
        taxresult=(TextView)findViewById(R.id.tvtax);
    logout=(Button)findViewById(R.id.btlogout);
    tax=(Button)findViewById(R.id.btsave);
    logout.setOnClickListener(this);
    tax.setOnClickListener(this);
    FirebaseUser user=firebaseAuth.getCurrentUser();
    welcome.setText("hello user" +user.getEmail());


    }
private void  userInfo(){
        String nameip=name.getText().toString().trim();
        String phoneip=number.getText().toString().trim();
        String salaryip= salary.getText().toString().trim();
        long sal=Long.parseLong(salaryip);
        long range,range1,range2,range3;
        range=0;
        range1=((5/100)*(sal)-(250000));
    range2=((20/100)*(sal)-(500000));
    range3=((30/100)*(sal)-(1000000));
    UserInformation userInformation;
    FirebaseUser user=firebaseAuth.getCurrentUser();
    if(sal>0 && sal<=250000)
    {

        userInformation  = new UserInformation(nameip,phoneip,sal);
        databaseReference.child(user.getUid()).setValue(userInformation);
        taxresult.setText(String.valueOf(range));
        Toast.makeText(this,"Your information is saved",Toast.LENGTH_LONG).show();
    }
 else
    {
        if(sal>250000 && sal<=500000)
        {

            userInformation = new UserInformation(nameip,phoneip,range1);
            databaseReference.child(user.getUid()).setValue(userInformation);
            taxresult.setText(String.valueOf(range1));
            Toast.makeText(this,"Your information is saved",Toast.LENGTH_LONG).show();
        }
        else if(sal>500000 && sal<=1000000)
        {

            userInformation = new UserInformation(nameip,phoneip,range2);
            databaseReference.child(user.getUid()).setValue(userInformation);
            taxresult.setText(String.valueOf(range2));
            Toast.makeText(this,"Your information is saved",Toast.LENGTH_LONG).show();
        }
        else if(sal>1000000)
        {

            userInformation = new UserInformation(nameip,phoneip,range3);
            databaseReference.child(user.getUid()).setValue(userInformation);

            taxresult.setText(String.valueOf(range3));
            Toast.makeText(this,"Your information is saved",Toast.LENGTH_LONG).show();
        }
    }


}

    @Override
    public void onClick(View v) {
    if(v==logout)
    {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),login.class));
    }
    if(v==tax){
       userInfo();

    }
    }
}
