package com.example.recycleargrid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_item extends AppCompatActivity {

    EditText item_name,item_price;
    Button add_item;

    String cat_id;
    Activity activity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        item_name = findViewById(R.id.itm_name);
        item_price = findViewById(R.id.itm_price);
        add_item = findViewById(R.id.itm_add_button);

        //onclick add button

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper mydb = new DataBaseHelper(Add_item.this);

                // to get cat_id

                cat_id = getIntent().getStringExtra("cat_id");




                mydb.addItem(item_name.getText().toString().trim(),
                              item_price.getText().toString().trim(),
                                cat_id);


                Intent intent = new Intent(Add_item.this,ItemActivity.class);
                activity.startActivityForResult(intent,0);
            }
        });
    }
}