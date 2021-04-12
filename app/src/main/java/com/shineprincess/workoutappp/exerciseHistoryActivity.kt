package com.shineprincess.workoutappp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise_history.*

class ExerciseHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_history)


        setSupportActionBar(history_toolbar)

        val actionbar = supportActionBar

        if(actionbar !=null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "History"
        }

        history_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates() {
        val dbHandler = SqLiteOpenHelper(this,null)
        val allCompletedDates = dbHandler.getAllCompletedDatesList()

//        for(i in allCompletedDates) {
//            Log.i("HISTORY","" + i)
//        }

        if (allCompletedDates.size > 0) {
            tvHistoryExerciseCompleted.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tvNoDataAvailable.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)

            val historyAdapter = HistoryAdapter(this,allCompletedDates)

            rvHistory.adapter = historyAdapter
        } else {
            tvHistoryExerciseCompleted.visibility = View.GONE
            rvHistory.visibility = View.GONE
            tvNoDataAvailable.visibility = View.VISIBLE
        }
    }



}