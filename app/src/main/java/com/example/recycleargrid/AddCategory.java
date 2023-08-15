package com.example.recycleargrid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCategory extends AppCompatActivity {


    Activity activity;
    EditText cat_name;
    Button cat_add_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        cat_name = findViewById(R.id.cat_name);
        cat_add_button = findViewById(R.id.cat_add_button);

        cat_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBaseHelper mydb = new DataBaseHelper(AddCategory.this);

                mydb.addCategory(cat_name.getText().toString().trim());

                Intent intent = new Intent(AddCategory.this,MainActivity.class);
                activity.startActivityForResult(intent,0);

            }
        });


    }
}