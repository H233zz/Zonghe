package bawei.com.zonghe.utills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import bawei.com.zonghe.Data;

/**
 * 类描述：
 * 创建人：
 * 创建时间：2017/8/23  9:42
 */

public class Dao {
    private SQLiteDatabase db;

    public Dao(Context context) {
        MyHelper helper=new MyHelper(context);
        db=helper.getWritableDatabase();
    }
    public void insert(String title,String image){
        ContentValues values=new ContentValues();
        values.put("tilte",title);
        values.put("image",image);
//              db.insert("lx",null,values);
//        Cursor lx = db.query("lx", null, null, null, null, null, null);
//        while(lx.moveToNext()){
//            String name = lx.getString(lx.getColumnIndex("title"));
//            String phone = lx.getString(lx.getColumnIndex("image"));
//
//
//
//        }


    }
    }

