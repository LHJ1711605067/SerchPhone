package cn.edu.gdpt.numberphone.view;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
public static final String  CREATE_PHONE="create table Phone("+"id integer primary key autoincrement,"+"number intege)";
private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_PHONE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
