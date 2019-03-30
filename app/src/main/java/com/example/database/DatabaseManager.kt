package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,1) {



    override fun onCreate(db: SQLiteDatabase?) {
      db!!.execSQL("CREATE TABLE $TABALE_NAME($COLUMN_1 TEXT,$COLUMN_2 TEXT,$COLUMN_3 INTEGER,$COLUMN_4 INTEGER PRIMARY KEY AUTOINCREMENT)")



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABALE_NAME ")
        onCreate(db)

    }

    //insertData
    //updateData
    //viewData
    //deleteData

    fun insertData(FNAME:String, SNAME:String, AGE: String){
        val db =this.writableDatabase
        val contentValues =ContentValues()
        contentValues.put(COLUMN_1,FNAME)
        contentValues.put(COLUMN_2,SNAME)
        contentValues.put(COLUMN_3,AGE)
        db.insert(TABALE_NAME,null,contentValues)
    }
    fun updateData(FNAME: String,SNAME: String,AGE: String,ID: String):Boolean{
        val db=this.writableDatabase
        val contentValues =ContentValues()
        contentValues.put(COLUMN_1,FNAME)
        contentValues.put(COLUMN_4,ID)
        contentValues.put(COLUMN_2,SNAME)
        contentValues.put(COLUMN_3,AGE)
        db.update(TABALE_NAME,contentValues,"ID =?", arrayOf(ID))
        return true
    }
    fun deleteData(ID:String):Int{
        val db=this.writableDatabase
        return db.delete(TABALE_NAME,"ID=?", arrayOf(ID))

    }

   val allData:Cursor get() {

        val db=this.writableDatabase
       val res=db.rawQuery("SELECT *FROM $TABALE_NAME",null)
       return res
    }



    companion object {
        val DATABASE_NAME="STUDENTS"
        var TABALE_NAME ="FRIENDS"
        var COLUMN_1 ="FNAME"
        var COLUMN_2="SNAME"
        var COLUMN_3="AGE"
        var COLUMN_4="ID"






    }




}