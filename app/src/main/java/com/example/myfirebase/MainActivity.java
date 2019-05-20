package com.example.myfirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText etemail, etpassword;
    Button btnregister;
    FirebaseAuth myaut;
    ProgressDialog mydialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydialog= new ProgressDialog(this);

        myaut= FirebaseAuth.getInstance();

        etemail= (EditText)findViewById(R.id.etemail);
        etpassword= (EditText)findViewById(R.id.etpassword);
        btnregister= (Button)findViewById(R.id.btnregister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void validate(){

      String email= etemail.getText().toString().trim();
      String password= etpassword.getText().toString().trim();

      if(email.isEmpty()){
          Toast.makeText(MainActivity.this, "would you enter your email plz",Toast.LENGTH_SHORT).show();

      }
        if (password.isEmpty()) {
            Toast.makeText(MainActivity.this,"u forgot ur password",Toast.LENGTH_SHORT).show();

        }

        else {
            //we will display a success message

            mydialog.setMessage("user is registering pls wait .....");
            mydialog.show();

            myaut.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    mydialog.dismiss();

                    if (task.isSuccessful()){

                        // user registered successfully
                        Toast.makeText(MainActivity.this," registered successfully",Toast.LENGTH_LONG).show();
                    }

                }
            });


        }

    }
}
