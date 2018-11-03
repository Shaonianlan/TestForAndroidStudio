package com.example.a10096.datebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a10096.datebasetest.mode.Book;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitepalTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal_test);
        Button button_create = (Button) findViewById(R.id.button_createdatabasel);
        Button button_insert = (Button) findViewById(R.id.button_insertl);
        Button button_update = (Button) findViewById(R.id.button_updatel);
        Button button_delete = (Button) findViewById(R.id.button_deletel);
        Button button_select = (Button) findViewById(R.id.button_selectl);

        //点击按钮建表
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        //点击按钮insert数据
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });

        //点击按钮update数据
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(55.22);
                book.setAuthor("xuchenmin");
                book.updateAll("name = ? and pages = ?","The Lost Symbol","510");
            }
        });

        //点击删除数据
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price < ?","17");
            }
        });

        //查询数据
        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.select("name")
                                                .find(Book.class);
                for (Book book: books){
                    Log.d("LitepalResult","book name is "+book.getName());
//                    Log.d("LitepalResult","book author is "+book.getAuthor());
//                    Log.d("LitepalResult","book pages is "+book.getPages());
//                    Log.d("LitepalResult","book price is "+book.getPrice());
//                    Log.d("LitepalResult","book press is "+book.getPress());
                }
            }
        });
    }
}
