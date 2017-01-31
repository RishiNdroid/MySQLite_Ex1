package com.example.rndroid.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rndroid on 27/12/16.
 */
// step 2 - create a separete database file (CONTROLLER)
public class MyDatabase {

    //step - 5 - declare required variables
    public MyHelper myHelper;
    public SQLiteDatabase sqLiteDatabase; //for doing DML operation

    //Step 6- Create object for myHelper variable
    public MyDatabase(Context c){
        myHelper = new MyHelper(c,"mydatabase",null,1);// here database is created
    }

//    Step 7 - Create sqlite object using a method
    public void openDB(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    // Perform DML Operation using ContentValues
    public void insertStudInfo(String name, String subject){
        ContentValues cv = new ContentValues();
        cv.put("sname", name);
        cv.put("ssubject", subject);
        sqLiteDatabase.insert("studentinfo",null, cv);
    }

    public Cursor queryCursorRead(){
//        String [] column = new String[]{" sname " , " ssubject "};
        Cursor cursor = sqLiteDatabase.query("studentinfo",null, null,null, null, null,null );
        return cursor;
    }
    //update name
    public void updateDetails(){
        ContentValues values = new ContentValues();
        values.put("sname", "Jack");
        sqLiteDatabase.update("studentinfo", values, "sname=?", new String[]{"Rishi"});
    }

//    Step
    public void closeDB(){
        sqLiteDatabase.close();
    }

    //Step 3- create innner class and extend SQLiteOpenHelper and write DDL query in onCreate()
    public class MyHelper extends SQLiteOpenHelper{

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
//            sqLiteDatabase.execSQL(" Create Table " +" studentinfo "+" ( "+"_id"+" integer primary key autoincrement, "+" sname " +" text not null, " + " ssubject "+" text not null "+");");
//or
            sqLiteDatabase.execSQL("create table studentinfo(_id integer primary key autoincrement, sname text not null, ssubject text not null);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
