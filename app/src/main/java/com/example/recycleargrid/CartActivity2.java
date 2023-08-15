package com.example.recycleargrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CartActivity2 extends AppCompatActivity {

   private String itm_name;
   private String itm_price;

ArrayList<String> cart_name,cart_price,cart_quantity;
    RecyclerView recyclerView;
    FloatingActionButton instantAdd;

    DataBaseHelper mydb;
    CartAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        instantAdd = findViewById(R.id.float_instant_add);
        recyclerView = findViewById(R.id.cartRecyclerView);


        //arrray list from db

        mydb = new DataBaseHelper(this);
        cart_name = new ArrayList<>();
        cart_price =new ArrayList<>();
        cart_quantity = new ArrayList<>();
         getsDataCart();



        adapter = new CartAdapter(CartActivity2.this,cart_name,cart_price,cart_quantity);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity2.this));


        instantAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity2.this,CartDatatest.class);
                startActivity(intent);
            }
        });

    }
// get data from data base
    void getsDataCart(){

        Cursor c = mydb.readCart();

        if(c.getCount() == 0){
            Toast.makeText(this,"NO item",Toast.LENGTH_SHORT).show();
        }else{
            while (c.moveToNext()){
                cart_name.add(c.getString(1));
                cart_price.add(c.getString(2));
                cart_quantity.add(c.getString(3));
            }


        }


    }



}
