package com.example.k_hr.e_comm.Models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.k_hr.e_comm.ProdDescActivity;
import com.example.k_hr.e_comm.R;
import com.example.k_hr.e_comm.ViewHolder.MenuViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    ArrayList<Product> products;
    String key;

    public RecyclerAdapter(ArrayList<Product> products/*, String key*/){
        this.products = products;
//        this.key = key;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater li = (LayoutInflater) viewGroup.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = li.inflate(R.layout.menu_item, viewGroup, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder menuViewHolder, final int i) {
//        menuViewHolder.txtMenuName.setText(products.get(i).getName());
//        System.out.println(products.get(i).getImage() + "RA : 39");
        Picasso.get().load(products.get(i).getImage()).into(menuViewHolder.imageView);
        menuViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProdDescActivity.class);
//                intent.putExtra("CategoryId", key);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
