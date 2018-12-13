package com.example.acerpredator.androideatitserver;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acerpredator.androideatitserver.Common.Common;
import com.example.acerpredator.androideatitserver.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword ;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (EditText) findViewById(R.id.editpassword);
        edtPhone = (EditText) findViewById(R.id.editPhone);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        //init firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });
    }
    private void signInUser (String phone, String password){
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Mohon Tunggu...");
        mDialog.show();

        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(localPhone).exists()){
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if(Boolean.parseBoolean(user.getIsStaff())) //if siStaff == true
                    {
                        if(user.getPassword().equals(localPassword))
                        {
                            Intent login = new Intent(SignIn.this, Home.class);
                            Common.currentUser=user;
                            startActivity(login);
                            finish();
                        }
                        else
                            Toast.makeText(SignIn.this, "password salah!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(SignIn.this, "please login dengan akun staff", Toast.LENGTH_SHORT).show();
                }
                else{
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
