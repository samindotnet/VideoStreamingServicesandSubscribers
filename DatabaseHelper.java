package com.example.videostreamingservicesandsubscribers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_Name ="VideoServiceDB";
    private static final String Table_Name ="VideoService";
    private static final String Column_A ="VSID";
    private static final String Column_B ="Company_Name";
    private static final String Column_C ="Stock_price";
    private static final String Column_D ="Subscribers";
    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table_Name+"(VSID integer primary key autoincrement, Company_Name text,Stock_price text,Subscribers Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists "+Table_Name);
        onCreate(db);
    }
    public boolean InsertService(String Company_Name,String Stock_price,String Subscribers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_B,Company_Name);
        contentValues.put(Column_C,Stock_price);
        contentValues.put(Column_D,Subscribers);
        long result = db.insert(Table_Name,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;
    }
    public boolean UpdateService(String VSID,String Company_Name,String Stock_price,String Subscribers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_A,VSID);
        contentValues.put(Column_B,Company_Name);
        contentValues.put(Column_C,Stock_price);
        contentValues.put(Column_D,Subscribers);
        db.update(Table_Name,contentValues,"VSID =?",new String[] {VSID});
        return true;
    }
    public Integer DeleteService(String VSID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name,"VSID =?",new String[] {VSID});
    }
    public Cursor viewAllServices(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor acura = db.rawQuery("select * from "+Table_Name,null);
        return acura;
    }
}
