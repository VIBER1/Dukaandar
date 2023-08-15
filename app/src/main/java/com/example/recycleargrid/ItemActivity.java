package com.example.recycleargrid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    ArrayList<String> itm_id,itm_name,itm_price;

    String cat_id ;
    DataBaseHelper mydb;
    RecylearViewAdapterItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        recyclerView = findViewById(R.id.itemRecylerView);
        floatingActionButton = findViewById(R.id.itemFloatingActionButton);

        mydb = new DataBaseHelper(ItemActivity.this);
        itm_id = new ArrayList<>();
        itm_name = new ArrayList<>();
        itm_price= new ArrayList<>();
        getsItemData(cat_id = getIntent().getStringExtra("cat_id"));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cat_id = getIntent().getStringExtra("cat_id");

                Intent intent = new Intent(ItemActivity.this,Add_item.class);

                intent.putExtra("cat_id",cat_id);
                startActivity(intent);
            }
        });

        adapter = new RecylearViewAdapterItem(this,itm_id,itm_name,itm_price,ItemActivity.this);

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    // menu bar
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.cart_menu){

            Intent intent = new Intent(this,CartActivity2.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }


//to get data from database
    void getsItemData(String cat_id){
        Cursor c = mydb.readItem(cat_id);

        if(c.getCount() == 0){
            Toast.makeText(getApplicationContext(),"NO item",Toast.LENGTH_SHORT).show();
        }else {
            while(c.moveToNext()){
                itm_id.add(c.getString(0));
                itm_name.add(c.getString(1));
                itm_price.add(c.getString(2));

            }
        }

    }
}