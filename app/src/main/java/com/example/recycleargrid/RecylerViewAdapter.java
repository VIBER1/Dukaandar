package com.example.recycleargrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.RecylerViewHolder> {

    private ArrayList cat_id, cat_name;


    private Activity activity;

    private Context mcontext;





// for category
    public RecylerViewAdapter(Context mcontext, ArrayList cat_id, ArrayList cat_name, Activity activity) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.mcontext = mcontext;
        this.activity = activity;
    }


    @NonNull
    @Override
    public RecylerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new RecylerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewHolder holder,final int position) {
//        RecylerData recylerData = mrecylerData.get(position);
        //to get cat_id
        String index_cat_id = String.valueOf(cat_id.get(position));
        String index_cat_name = String.valueOf(cat_name.get(position));
        holder.tectTV.setText(String.valueOf(cat_name.get(position)));
        holder.textID.setText(String.valueOf(cat_id.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,ItemActivity.class);

                intent.putExtra("cat_id",index_cat_id);
                activity.startActivity(intent);

                }
            });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DataBaseHelper mydb =new DataBaseHelper(mcontext);
                mydb.deleteCategory(index_cat_name);

//                Toast.makeText(activity.getApplicationContext(), "Item clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext,MainActivity.class);
                activity.startActivityForResult(intent,0);

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cat_id.size();
    }

    public class RecylerViewHolder extends RecyclerView.ViewHolder {

        TextView tectTV,textID;
        CardView cardView;
//        ImageView imgTv;





        public RecylerViewHolder(@NonNull View itemView) {
            super(itemView);

            tectTV = itemView.findViewById(R.id.idText);
            textID = itemView.findViewById(R.id.textId);
            cardView = itemView.findViewById(R.id.cardId);



        }
    }
}
