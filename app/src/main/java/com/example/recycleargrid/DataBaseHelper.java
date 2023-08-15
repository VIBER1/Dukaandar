package com.example.recycleargrid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
   //data base variable
    public static final String DATABASE_NAME = "database.db";
    public static final int  DATABASE_VERSION = 1;



    // Category Table
    public static final String CAT_TABLE_NAME = "my_category";
    public static final String  CAT_ID = "cat_id";
    public static final String  CAT_NAME = "category_name";


    // ITEM TABLE
    public static final String ITM_TABLE_NAME = "my_item";
    public static final String  ITM_ID = "itm_id";
    public static final String  ITM_NAME = "item_name";
    public static final String ITM_PRC = "itm_prc";
    public static final String C_ID = "cat_id";


    // CART TABLE
    public static final String  CART_TABLE_NAME = "my_cart";
    public static final String CART_ID =  "cart_id";
    public static final String CART_NAME = "cart_name";
    public static final String CART_PRICE = "itm_price";
    public static final String CART_QUANTITY = "itm_quantity";




    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

     String queary_c = "CREATE TABLE " + CAT_TABLE_NAME +"("+
                      CAT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                   + CAT_NAME + " TEXT"
                   + ");";


     String queary_i = "CREATE TABLE "+ ITM_TABLE_NAME + "("+
                        ITM_ID + " INTRGER PRIMARY KEY, " //item id
                      + ITM_NAME +" TEXT,"               //item  name
                      +ITM_PRC +" REAL,"                 // item price
                      + C_ID + " INTEGER, " +           // cat id
                       "FOREIGN KEY( "+C_ID+")"+ " REFERENCES " +CAT_TABLE_NAME+ "(" +CAT_ID +"));";

     String queary_cart = "CREATE TABLE "+ CART_TABLE_NAME +"("+
                            CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                          + CART_NAME + " TEXT, "
                          + CART_PRICE +" REAL, "
                          + CART_QUANTITY + " INTEGER,"
                          +"UNIQUE("+CART_NAME +" , "+CART_PRICE + ") );";

     db.execSQL(queary_c);
     db.execSQL(queary_i);
     db.execSQL(queary_cart);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

       db.execSQL("DROP TABLE IF EXISTS "+CAT_TABLE_NAME);
       db.execSQL( "DROP TABLE IF EXISTS "+ ITM_TABLE_NAME);
        db.execSQL( "DROP TABLE IF EXISTS "+ ITM_TABLE_NAME);



    }





    // Methord to add category
   void addCategory(String category_name){
     SQLiteDatabase  db = this.getWritableDatabase();

     ContentValues cv = new ContentValues();

     cv.put(CAT_NAME,category_name);

     long result = db.insert(CAT_TABLE_NAME,null,cv);

     if(result == -1){
      Toast.makeText(context, "Insertion Failed",Toast.LENGTH_SHORT).show();
     }else{

      Toast.makeText(context.getApplicationContext(), "Insertion Successfully",Toast.LENGTH_SHORT).show();

     }

   }





   // access category data

    Cursor readCategory(){

        String queary = "SELECT *FROM " +CAT_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;


        if(db != null){

            c = db.rawQuery(queary,null);
        }
        return c;

    }

    // Insertion in Item table
    void addItem(String itm_Name, String item_price,String cat_number){

        SQLiteDatabase db =  this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(ITM_NAME,itm_Name);
        cv.put(ITM_PRC,item_price);
        cv.put(C_ID,cat_number);

      long result =   db.insert(ITM_TABLE_NAME,null,cv);

        if(result == -1){
            Toast.makeText(context, "Insertion Failed",Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context.getApplicationContext(), "Insertion Successfully",Toast.LENGTH_SHORT).show();

        }


    }

// read item table
    Cursor readItem(String c_ID){

        String queary = "SELECT *FROM " + ITM_TABLE_NAME + " WHERE "+C_ID+ " = " + "\"" +c_ID+ "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = null;

        if(db != null){

            c = db.rawQuery(queary,null);
        }
        return c;


    }


    // to insert into item
    void addToCart(String itm_Name, String item_price,String quantiy){

        SQLiteDatabase db =  this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(CART_NAME,itm_Name);
        cv.put(CART_PRICE,item_price);
        cv.put(CART_QUANTITY,quantiy);

        long result =   db.insert(CART_TABLE_NAME,null,cv);

        if(result == -1){
            Toast.makeText(context, "Insertion Failed",Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context.getApplicationContext(), "Insertion Successfully",Toast.LENGTH_SHORT).show();

        }


    }

// read cart value
    Cursor readCart(){

        String queary = "SELECT * FROM "+ CART_TABLE_NAME;

        SQLiteDatabase  db = this.getReadableDatabase();

        Cursor c = null;

        if(db != null){
            c = db.rawQuery(queary,null);
        }
        return  c;

    }

    // update the list
    void updateCart(String itm_name,String quantity){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CART_QUANTITY,quantity);

        db.update(CART_TABLE_NAME,values,CART_NAME+" = ?",new String[]{itm_name});


    }

    // category delete cmd

    void deleteCategory(String cat_name){

        SQLiteDatabase db = this.getWritableDatabase();
        long result =  db.delete(CAT_TABLE_NAME,CAT_NAME+"= ?",new String[]{cat_name});
        if (result == -1){
            Toast.makeText(context,"Faild to delete",Toast.LENGTH_SHORT ).show();
        }else {
            Toast.makeText(context,"Deleated sucessfully",Toast.LENGTH_SHORT ).show();
        }
    }

    // delete item from cart

    void deleteCart(String cart_name){


        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(CART_TABLE_NAME,CART_NAME+"= ?",new String[]{cart_name});
        if (result == -1){
            Toast.makeText(context,"Faild to delete",Toast.LENGTH_SHORT ).show();
        }else {
            Toast.makeText(context,"Sucessfullyn deleted",Toast.LENGTH_SHORT ).show();
        }
    }



}
