package com.example.recycleargrid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.RecyclerViewHolder> {


    private Context cart_context;
    private ArrayList cart_name,cart_price,cart_quantity;


    public CartAdapter(Context cart_context, ArrayList cart_name, ArrayList cart_price, ArrayList cart_quantity) {
        this.cart_context = cart_context;
        this.cart_name = cart_name;
        this.cart_price = cart_price;
        this.cart_quantity = cart_quantity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {


        String index_cart_name = String.valueOf(cart_name.get(position));



       holder.crt_name.setText(String.valueOf(cart_name.get(position)));
        holder.crt_price.setText(String.valueOf(cart_price.get(position)));
        holder.crt_quantity.setText(String.valueOf(cart_quantity.get(position)));
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper mydb = new DataBaseHelper(cart_context);
                mydb.deleteCart(index_cart_name);


                Intent intent = new Intent(cart_context,CartActivity2.class);
                cart_context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return cart_name.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView crt_name,crt_price,crt_quantity;
        Button delete_button;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            crt_name= itemView.findViewById(R.id.cartItemName);
            crt_price = itemView.findViewById(R.id.cartPrice);
            crt_quantity = itemView.findViewById(R.id.cart_quantity);
            delete_button = itemView.findViewById(R.id.cart_delete_Button);
        }
    }
}
