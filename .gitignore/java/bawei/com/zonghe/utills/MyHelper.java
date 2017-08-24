package bawei.com.zonghe.utills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类描述：
 * 创建人：
 * 创建时间：2017/8/23  9:13
 */

public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context) {
        super(context, "hhh.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table lx(id Integer primary key,title varchar(200),image varchar(200) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
