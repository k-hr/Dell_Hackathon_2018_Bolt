package com.example.k_hr.e_comm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.k_hr.e_comm.Common.Common;
import com.example.k_hr.e_comm.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText phn, pswrd;
    Button guest_user, sign_in;
    TextView sign_up, forgot;
    String phone, password;
//    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phn = findViewById(R.id.et_phn);
        pswrd = findViewById(R.id.et_password);
        sign_in = findViewById(R.id.btn_signin);
        guest_user = findViewById(R.id.btn_guest);
        sign_up = findViewById(R.id.tv_signup);
        forgot = findViewById(R.id.tv_forgot);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference refData = firebaseDatabase.getReference("User");

        progressDialog = new ProgressDialog(this);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("validating... wait a sec!!");
                progressDialog.show();

                refData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(phn.getText().toString()).exists()) {
                            progressDialog.dismiss();

                            User user = dataSnapshot.child(phn.getText().toString()).getValue(User.class);

                            //System.out.println("main Activity 68" + dataSnapshot.child(phn.getText().toString()).getValue(User.class).getPassword());

                            if (user.getPassword().equals(pswrd.getText().toString()) && validate()) {
//                                checkEmailVerification();
                                Toast.makeText(MainActivity.this, "SignedIn successfully :-)", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));

                                Common.currentUser = user;
                                finish();

                            } else {
                                System.out.println("if1.1");
                                Toast.makeText(MainActivity.this, "Wrong Password!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            progressDialog.dismiss();
                            System.out.println("else1.0");
                            Toast.makeText(MainActivity.this, "Mobile Number not registered with us!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordActivity.class));
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(sign_up);
            }
        });

        guest_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guest = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(guest);
            }
        });
    }

    private Boolean validate(){
        Boolean result = false;

        password = pswrd.getText().toString();
        phone = phn.getText().toString();


        if(password.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }

//    private void checkEmailVerification() {
//        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
//        Boolean emailflag = firebaseUser.isEmailVerified();
//
//        startActivity(new Intent(MainActivity.this, HomeActivity.class));
//
//        if(emailflag){
//            finish();
//
//        }else{
//            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
//
//    }

}
