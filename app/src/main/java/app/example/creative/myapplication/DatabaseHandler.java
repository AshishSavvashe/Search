package app.example.creative.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhavesh on 11/3/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "androidVersion";
    private static final String TABLE_NAME = "version";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_VNAME = "v_name";
    private static final String KEY_API_NAME = "api_name";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,v_name TEXT,api_name TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(AndroidVersion androidVersion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,androidVersion.getName());
        contentValues.put(KEY_VNAME,androidVersion.getVer());
        contentValues.put(KEY_API_NAME,androidVersion.getApi());
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public ArrayList<AndroidVersion> getdata(){
        // DataModel dataModel = new DataModel();
        ArrayList<AndroidVersion> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME +" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        AndroidVersion dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new AndroidVersion();
            String id = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
            String country = cursor.getString(cursor.getColumnIndexOrThrow("v_name"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("api_name"));
            dataModel.setName(name);
            dataModel.setVer(city);
            dataModel.setApi(country);
            stringBuffer.append(dataModel);
           
            data.add(dataModel);
        }


        return data;
    }
}
