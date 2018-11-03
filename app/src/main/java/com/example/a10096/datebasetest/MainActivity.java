package com.example.a10096.datebasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDateBaseHelper dateBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateBaseHelper = new MyDateBaseHelper(this,"BookStore.db",null,2);
        Button button = findViewById(R.id.button_createdatabasel);
        Button button_insert = findViewById(R.id.button_insertl);
        Button button_update = findViewById(R.id.button_updatel);
        Button button_delete = findViewById(R.id.button_deletel);
        Button button_select = findViewById(R.id.button_select);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateBaseHelper.getWritableDatabase();
            }
        });

        //插入数据
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dateBaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //第一条数据
                values.put("name","The Da Yinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.95);
                db.insert("Book",null,values);
            }
        });

        //修改数据
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dateBaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name= ? ", new String[]{"The Da Yinci Code"});
            }
        });

        //删除数据
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQL_DELETE = "delete from Book where name = 'The Da Yinci Code' ";
                SQLiteDatabase db = dateBaseHelper.getWritableDatabase();
                db.execSQL(SQL_DELETE);
                Toast.makeText(getBaseContext(),"DELETE succeeded", Toast.LENGTH_SHORT).show();
            }
        });

        //查询数据
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dateBaseHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from Book where pages > ?",new String[]{"500"});
                if (cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，去除数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("DataTestResult","Book name is " + name);
                        Log.d("DataTestResult","Book author is " + author);
                        Log.d("DataTestResult","Book pages is " + pages);
                        Log.d("DataTestResult","Book price is " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
