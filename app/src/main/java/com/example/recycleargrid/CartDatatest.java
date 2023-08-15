package com.example.recycleargrid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CartDatatest extends AppCompatActivity {

    EditText cart_name, cart_price, cart_quantity;
    Button addToCartButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_datatest);

        cart_name = findViewById(R.id.cart_name);
        cart_price = findViewById(R.id.cart_price);
        cart_quantity = findViewById(R.id.cart_quantity);
        addToCartButton = findViewById(R.id.cart_add_button);


        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper mydb = new DataBaseHelper(CartDatatest.this);

                mydb.addToCart(cart_name.getText().toString().trim(),
                                cart_price.getText().toString().trim(),
                                cart_quantity.getText().toString().trim()

                );


                Intent intent = new Intent(CartDatatest.this,CartActivity2.class);
                startActivityForResult(intent,0);
            }
        });
    }
}