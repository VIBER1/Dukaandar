package com.example.recycleargrid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
   // ArrayList<RecylerData> recylerDataArrayList;
   RecylerViewAdapter adapter;

    FloatingActionButton floatingActionButton;

    ArrayList<String> cat_id, cat_name;
    DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar;


        recyclerView = findViewById(R.id.recylerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);



        // onclick floating buttuon
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCategory.class);
                startActivity(intent);
            }
        });


        mydb = new DataBaseHelper(MainActivity.this);

        cat_id = new ArrayList<>();
        cat_name = new ArrayList<>();
        getsCatData();




        // recylerDataArrayList = new ArrayList<>();
//        recylerDataArrayList.add(new RecylerData("DSA",R.drawable.baseline_home_24));
//        recylerDataArrayList.add(new RecylerData("C++",R.drawable.baseline_home_24));
//        recylerDataArrayList.add(new RecylerData("Android",R.drawable.baseline_home_24));
//        recylerDataArrayList.add(new RecylerData("Studio",R.drawable.baseline_home_24));
//        recylerDataArrayList.add(new RecylerData("Student",R.drawable.baseline_home_24));


        // added mainActivity for on click listner in adapter class


         adapter = new RecylerViewAdapter(this,cat_id,cat_name,MainActivity.this);

       GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
///to refresh MainActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }



// menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.cart_menu){

            Intent intent = new Intent(MainActivity.this,CartActivity2.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    // gets data cursor from database class

    void getsCatData(){
        Cursor c = mydb.readCategory();

        if(c.getCount() == 0){

        }else {
           while(c.moveToNext()){
               cat_id.add(c.getString(0));
               cat_name.add(c.getString(1));
           }
        }

    }
}