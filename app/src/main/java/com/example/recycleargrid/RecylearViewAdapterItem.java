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
import java.util.HashMap;
import java.util.List;

public class RecylearViewAdapterItem extends RecyclerView.Adapter<RecylearViewAdapterItem.RecylerViewHolder>{

    private Context i_context;
    //for item array
    private ArrayList itm_id,itm_name,itm_price;

    private Activity activity;

    private HashMap clickCount = new HashMap<>();
    private int currentClickcounter=0;




    public RecylearViewAdapterItem(Context i_context, ArrayList itm_id, ArrayList itm_name, ArrayList itm_price,Activity activity) {
        this.i_context = i_context;
        this.itm_id = itm_id;
        this.itm_name = itm_name;
        this.itm_price = itm_price;
    }

    @NonNull
    @Override
    public RecylearViewAdapterItem.RecylerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_layout,parent,false);

        return new RecylerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewHolder holder, int position) {
         int onclickposition = position;
         String list_name = String.valueOf(itm_name.get(position));
        String list_price = String.valueOf(itm_price.get(position));
        //holder.item_id.setText(String.valueOf(itm_id.get(position)));
        holder.item_name.setText(String.valueOf(itm_name.get(position)));
        holder.item_price.setText(String.valueOf(itm_price.get(position)));
        holder.item_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                      currentClickcounter = (int) clickCount.getOrDefault(onclickposition,1);
                    clickCount.put(onclickposition,currentClickcounter+1);

                    if(currentClickcounter == 1){
//                        Toast.makeText(view.getContext(), "Item clicked Only once times", Toast.LENGTH_SHORT).show();

                        DataBaseHelper mydb = new DataBaseHelper(i_context);

                        mydb.addToCart(list_name,list_price,"1");




                    }else{

                        DataBaseHelper mydb = new DataBaseHelper(i_context);
                        mydb.updateCart(list_name,String.valueOf(currentClickcounter));



                        Toast.makeText(view.getContext(), "Item Added " + String.valueOf(currentClickcounter)+ " times", Toast.LENGTH_SHORT).show();
                    }

            }

        });
        holder.item_cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                 currentClickcounter--;

//                Toast.makeText(view.getContext(), "Item clicked "+currentClickcounter,Toast.LENGTH_SHORT).show();

                if(currentClickcounter <= 0){
                    DataBaseHelper mydb = new DataBaseHelper(i_context);
                    mydb.deleteCart(list_name);
                }else {
                    DataBaseHelper mydb = new DataBaseHelper(i_context);
                    mydb.updateCart(list_name,String.valueOf(currentClickcounter));
                    Toast.makeText(view.getContext(), "Queantity is "+currentClickcounter,Toast.LENGTH_SHORT).show();
                }


                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return itm_id.size();
    }

    public class RecylerViewHolder extends RecyclerView.ViewHolder {


        //item card view
        TextView item_id,item_name,item_price;
        CardView item_cardView;


        public RecylerViewHolder(@NonNull View itemView) {
            super(itemView);

            // for item card

            //item_id = itemView.findViewById(R.id.itemIdTextView);
            item_name = itemView.findViewById(R.id.itemNameTextView);
            item_price = itemView.findViewById(R.id.itemPriceTextView);
            item_cardView = itemView.findViewById(R.id.itemCardView);

        }





    }

}
