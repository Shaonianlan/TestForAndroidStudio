package com.example.a10096.datebasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDateBaseHelper extends SQLiteOpenHelper {

    //定义建表的sql语句
    public static final String CREATE_BOOK_PRE = "DROP TABLE IF EXISTS Book";
    public static final String CREATE_BOOK = "create table Book (" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    public static final String CREATE_CATEGORY_PRE = "DROP TABLE IF EXISTS Category";
    public static final String CREATE_CATEGORY = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";
    private Context mContext;

    public MyDateBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext,"CREATE succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_BOOK_PRE);
        db.execSQL(CREATE_CATEGORY_PRE);
        onCreate(db);
    }
}
