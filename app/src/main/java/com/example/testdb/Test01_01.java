package com.example.testdb;
import android.app.Activity;

import android.os.Bundle;

import android.widget.LinearLayout;

import android.widget.TextView;

import android.database.*;

import android.database.sqlite.*;



public class Test01_01 extends Activity {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LinearLayout ll = new LinearLayout(this);

        ll.setOrientation(LinearLayout.HORIZONTAL);

        setContentView(ll);



        //データベースを作成

        String dbStr = "data/data/" + getPackageName() + "/Sample.db";

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbStr,  null );



        //テーブル作成用のクエリ文

        String query_table1 = "DROP TABLE IF EXISTS menber";

        String query_table2 = "CREATE TABLE menber" + " ( id INTEGER PRIMARY KEY , name  STRING ) ";



        //テーブルの作成

        db.execSQL(query_table1);

        db.execSQL(query_table2);



        //レコード作成用のクエリ文(INSERT)

        String query_record_set[]   =   {

                "INSERT INTO menber( name ) VALUES ( 'じょにー' ) ",

                "INSERT INTO menber( name ) VALUES ( 'まいける' ) ",

                "INSERT INTO menber( name ) VALUES ( 'のーら' ) ",

                "INSERT INTO menber( name ) VALUES ( 'てりー' ) ",

                "INSERT INTO menber( name ) VALUES ( 'まっけんろー' ) ",

                "INSERT INTO menber( name ) VALUES ( 'じーにょ' ) ",

                "INSERT INTO menber( name ) VALUES ( 'どみにく' ) ",

                "INSERT INTO menber( name ) VALUES ( 'ばろん' ) "};



        //レコード作成

        for( int i=0; i<query_record_set.length; i++){

            db.execSQL(query_record_set[i]);

        }





        //レコード検索用のクエリ文(SELECT)

        String query_select ="SELECT * FROM menber where name like '%ー%'";





        Cursor cursor = db.rawQuery(query_select, null);

        startManagingCursor(cursor);



        String result_str = "";



        while( cursor.moveToNext() ){

            int index_id    =   cursor.getColumnIndex("id");

            int index_name  =   cursor.getColumnIndex("name");

            int id          =   cursor.getInt(index_id);

            String name     =   cursor.getString(index_name);

            result_str      += "ID: " + id + " 名前:" +  name + "\n";

        }



        //内容表示用のTextView



        TextView tv = new TextView(this);

        tv.setText( result_str );

        ll.addView(tv);



    }

}
