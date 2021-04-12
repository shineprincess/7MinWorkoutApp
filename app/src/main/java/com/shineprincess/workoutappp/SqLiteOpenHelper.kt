package com.shineprincess.workoutappp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.CREATE

open class SqLiteOpenHelper(context:Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DB_NAME,factory,
    DB_VERSION) {

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "SevenMinutes.db"
        private val TABLE_NAME = "history"
        private val COLUMN_ID = "_id"
        private val COLUMN_COMPLETED_DATE = "COMPLETED_DATE"
    }

    override fun onCreate(db: SQLiteDatabase?) {
       val CREATE_EXERCISE_TABLE = ("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_COMPLETED_DATE TEXT)")
//        val CREATE_EXERCISE_TABLE = ("CREATE TABLE " +
//                TABLE_NAME + "("
//                + COLUMN_ID + " INTEGER PRIMARY KEY," +
//                COLUMN_COMPLETED_DATE
//                + " TEXT" + ")")

//        val CREATE_EXERCISE_TABLE = ("CREATE TABLE history(_id INTEGER PRIMARY KEY, COMPLETED_DATE TEXT)")
        db?.execSQL(CREATE_EXERCISE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addDate(date:String) {
        val values = ContentValues()

        values.put(COLUMN_COMPLETED_DATE , date)
        val db = this.writableDatabase

        db.insert("history",null,values)

        db.close()
    }

    fun getAllCompletedDatesList() : ArrayList<String> {
        val list = ArrayList<String>()

        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)

        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE)))
        }
        cursor.close()

        return list
    }
}