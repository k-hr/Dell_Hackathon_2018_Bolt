package com.example.k_hr.e_comm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.k_hr.e_comm.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.AbstractList;

public class ProdDescActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference prodDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_desc);

        database = FirebaseDatabase.getInstance();
        prodDesc = database.getReference("Product");

        ImageView img1 = findViewById(R.id.menu_image);
        ImageView img2 = findViewById(R.id.menu_image1);

        prodDesc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Product p = dataSnapshot.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Picasso.get().load("https://2.bp.blogspot.com/-oRS4M9YvdHQ/Wjy_n1cenZI/AAAAAAAAa60/SSmzTHYo6Xkkpw4uMb0ru_xfMbkzdrzCwCLcBGAs/s1600/IMG_20171221_125328.jpg").into(img1);

        Picasso.get().load("https://i0.wp.com/onlineunlocks.com/wp-content/uploads/2018/01/nokia_lumia_520-c.jpg?w=2600&ssl=1").into(img2);

    }


}
